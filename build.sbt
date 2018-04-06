import Dependencies._

lazy val root = (project in file(".")).
  settings(
    inThisBuild(List(
      organization := "com.example",
      scalaVersion := "2.12.5",
      version      := "0.1.0-SNAPSHOT"
    )),
    name := "ftp-evented",
    libraryDependencies := Seq(scalaTest % Test,
    "org.apache.ftpserver" % "ftpserver-core" % "1.1.1",
    "org.apache.ftpserver" % "ftplet-api" % "1.1.1" ,
    "org.apache.ftpserver" % "ftpserver" % "1.1.1" pomOnly()
   )
  )
