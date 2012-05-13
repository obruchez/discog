name := "Discog"

version := "0.1"

organization := "KeithJarrett.org"

scalaVersion := "2.9.1"

seq(webSettings :_*)
 
resolvers ++= Seq(
  "Scala Tools Releases" at "http://scala-tools.org/repo-releases/",
  "Java.net Maven2 Repository" at "http://download.java.net/maven/2/"
)

libraryDependencies ++= {
  val liftVersion = "2.4"
  Seq(
    "net.liftweb" %% "lift-webkit" % liftVersion % "compile->default",
    "net.liftweb" %% "lift-mapper" % liftVersion % "compile->default",
    "net.liftweb" %% "lift-wizard" % liftVersion % "compile->default")
}

libraryDependencies ++= Seq(
  "org.eclipse.jetty" % "jetty-webapp" % "8.0.4.v20111024" % "container",
  "org.mortbay.jetty" % "jetty" % "6.1.26" % "test",
  "org.scala-tools.testing" % "specs_2.9.0" % "1.6.8" % "test",
  "junit" % "junit" % "4.8" % "test"
)
