name := "fail-faster"
version := "1.0"
scalaVersion := "2.11.7"
organization := "fil.iagl.opl.failfaster"
mainClass in assembly := Some("fil.iagl.opl.Main")
assemblyJarName in assembly := "failfaster.jar"

libraryDependencies ++= Seq(
  "fr.inria.gforge.spoon" % "spoon-core" % "5.0.0",
  "org.scalactic" %% "scalactic" % "2.2.6",
  "org.scalatest" %% "scalatest" % "2.2.6" % "test",
  "org.backuity.clist" %% "clist-core"   % "2.0.1",
  "org.backuity.clist" %% "clist-macros" % "2.0.1" % "provided"
)
resolvers := Seq("Maven Repository for JDT Compiler release" at "https://repo.eclipse.org/content/repositories/eclipse-staging/")