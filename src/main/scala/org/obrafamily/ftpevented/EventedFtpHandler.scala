package org.obrafamily.ftpevented

import com.typesafe.scalalogging.Logger
import org.apache.ftpserver.ftplet._

class EventedFtpHandler extends  Ftplet {
  val logger = Logger(getClass)
  override def init(ftpletContext: FtpletContext): Unit = {
    logger.info("init")
  }

  override def destroy(): Unit = {
    logger.info("destroy")
  }

  override def beforeCommand(session: FtpSession, request: FtpRequest): FtpletResult = {
    logger.debug(s"------------- beforeCommand session=${session.getUser} request=${request.getArgument}")
    FtpletResult.DEFAULT

  }

  override def afterCommand(session: FtpSession, request: FtpRequest, reply: FtpReply): FtpletResult = {
    if (!request.hasArgument) return FtpletResult.SKIP

    if (! (request.getCommand.toUpperCase == "STOR") ) return FtpletResult.SKIP

      val fs = session.getFileSystemView
      logger.info(s"afterCommand fs=${fs}")
      if ((fs != null) && (fs.getWorkingDirectory != null)) {
        logger.info(s"fs.workingDir=${fs.getWorkingDirectory.getAbsolutePath}")

        val thing = s"${session.getUser.getHomeDirectory}${session.getFileSystemView.getWorkingDirectory.getAbsolutePath}/${request.getArgument}"
        val event = Uploaded( thing )
        logger.info(s"path=$event")
      }


    return FtpletResult.DEFAULT

  }

  override def onConnect(session: FtpSession): FtpletResult = {
    logger.debug(s"onConnect session=$session")
    FtpletResult.DEFAULT
  }

  override def onDisconnect(session: FtpSession): FtpletResult = {
    logger.debug(s"onDisconnect session=$session")
    FtpletResult.DEFAULT

  }

}
