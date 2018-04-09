import Dependencies._
scalaVersion := "2.12.2"
lazy val root = (project in file(".")).
  settings(
    inThisBuild(List(
      organization := "com.example",
      scalaVersion := "2.12.5",
      version      := "0.1.0-SNAPSHOT"
    )),
    name := "ftp-evented",
    libraryDependencies := Seq(scalaTest % Test,
    "org.scala-lang" % "scala-library" % "2.12.2",
      "com.typesafe" % "config" % "1.3.2",
      "ch.qos.logback" % "logback-classic" % "1.2.3",
      "com.typesafe.scala-logging" %% "scala-logging" % "3.8.0",
    "org.apache.ftpserver" % "ftpserver-core" % "1.1.1",
    "org.apache.ftpserver" % "ftplet-api" % "1.1.1" ,
    "org.apache.ftpserver" % "ftpserver" % "1.1.1" pomOnly()
   )
  )
