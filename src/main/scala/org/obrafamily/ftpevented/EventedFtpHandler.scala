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
    logger.info(s"------------- beforeCommand session=${session.getUser} request=${request.getArgument}")
    FtpletResult.DEFAULT

  }

  override def afterCommand(session: FtpSession, request: FtpRequest, reply: FtpReply): FtpletResult = {
    if (!request.hasArgument) return FtpletResult.SKIP

      logger.info(s"afterCommand session=$session")
      logger.info(s"afterCommand arg=${request.getArgument}")
      val fs = session.getFileSystemView
      logger.info(s"afterCommand fs=${fs}")
      if ((fs != null) && (fs.getWorkingDirectory != null)) {
        logger.info(s"fs.workingDir=${fs.getWorkingDirectory.getAbsolutePath}")

        val thing = s"${session.getFileSystemView.getHomeDirectory.getAbsolutePath}/${session.getFileSystemView.getWorkingDirectory.getAbsolutePath}/${request.getArgument}"
        logger.info(s"path=$thing")
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
