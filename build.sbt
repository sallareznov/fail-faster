name := "fail-faster"
version := "1.0"
scalaVersion := "2.11.7"
organization := "fil.iagl.opl.failfaster"
mainClass in assembly := Some("fil.iagl.opl.failfaster.Main")
assemblyJarName in assembly := "failfaster.jar"

libraryDependencies := Seq(
  "fr.inria.gforge.spoon" % "spoon-core" % "5.0.0",
  "commons-cli" % "commons-cli" % "1.3.1",
  "org.apache.commons" % "commons-lang3" % "3.4",
  "org.scalactic" %% "scalactic" % "2.2.6",
  "org.scalatest" %% "scalatest" % "2.2.6" % "test",
  "org.scalamock" % "scalamock-scalatest-support_2.11" % "3.2.2",
  "junit" % "junit" % "4.12",
  "org.mockito" % "mockito-all" % "1.10.19",
  "org.hamcrest" % "hamcrest-all" % "1.3",
  "org.assertj" % "assertj-core" % "3.3.0",
  "org.powermock" % "powermock-api-mockito" % "1.6.4",
  "org.powermock" % "powermock-module-junit4" % "1.6.4",
  "cglib" % "cglib-nodep" % "3.2.0",
  "de.vandermeer" % "asciitable" % "0.2.5"
)

resolvers := Seq(
"Maven Repository for JDT Compiler release" at "https://repo.eclipse.org/content/repositories/eclipse-staging/",
"Sonatype OSS Releases" at "https://oss.sonatype.org/service/local/staging/deploy/maven2"
)

assemblyMergeStrategy in assembly := {
  case PathList("org", "powermock", xs@_*) => MergeStrategy.last
  case PathList("org", "powermockito", xs@_*) => MergeStrategy.last
  case PathList("org", "mockito", xs@_*) => MergeStrategy.last
  case PathList("org", "hamcrest", xs@_*) => MergeStrategy.last
  case x =>
    val oldStrategy = (assemblyMergeStrategy in assembly).value
    oldStrategy(x)
}
