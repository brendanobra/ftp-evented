package org.obrafamily.ftpevented
import java.io.File
import java.util

import org.apache.ftpserver._
import com.typesafe.config._

import scala.collection.JavaConverters._
import org.apache.ftpserver.FtpServerFactory
import org.apache.ftpserver.ftplet.{Ftplet, User, UserManager}
import org.apache.ftpserver.listener.ListenerFactory
import com.typesafe.scalalogging
import com.typesafe.scalalogging.Logger
import org.apache.ftpserver.usermanager.{ClearTextPasswordEncryptor, PropertiesUserManagerFactory, SaltedPasswordEncryptor, UserManagerFactory}
import org.apache.ftpserver.usermanager.impl.{BaseUser, PropertiesUserManager}

class FtpEvented(config:Config ) {


  val serverFactory = new FtpServerFactory
  val listenerFactory = new ListenerFactory

  def start() = {
    val listenerConfig = config.getConfig("listener")
    val usersConfig = config.getConfig("users")
    val handler:Ftplet = new EventedFtpHandler()



    val ftpLets:java.util.Map[String,Ftplet] = new util.HashMap()
    ftpLets.put("default",handler)

    serverFactory.setFtplets(ftpLets)
    listenerFactory.setPort(listenerConfig.getInt("port"))
    listenerFactory.setServerAddress(listenerConfig.getString("interface"))
   /*
   configure auth
    */
    val userManagerFactory = new PropertiesUserManagerFactory();
    userManagerFactory.setFile(new File(usersConfig.getString("file")))
    userManagerFactory.setPasswordEncryptor(new ClearTextPasswordEncryptor())
    serverFactory.addListener("default", listenerFactory.createListener)
    userManagerFactory.setAdminName("gawd")

    val userManager = userManagerFactory.createUserManager()



   serverFactory.setUserManager( userManager )

    val server = serverFactory.createServer

    server.start()
    Thread.sleep(40000)
    server.stop()

  }


}

object  FtpEvented extends  App {
  val config = ConfigFactory.load().getConfig("FtpEvented")
  val ftpEvented = new FtpEvented( config )
  ftpEvented.start();
  Logger("FtpEvented").info("started")


}
