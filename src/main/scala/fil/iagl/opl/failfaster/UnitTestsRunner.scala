package fil.iagl.opl.failfaster

import java.io.File
import java.net.URLClassLoader
import java.nio.file.{Files, Path, Paths}
import java.util.stream.Collectors

import org.junit.runner.JUnitCore

import scala.collection.JavaConverters._
import scala.collection.mutable

class UnitTestsRunner {

  private def getClassLoader(sourcesFile: File, testsFile: File): ClassLoader = URLClassLoader.newInstance(Array(sourcesFile.toURI.toURL, testsFile.toURI.toURL))

  private def getClassForFile(file: Path, sourceFilePath: File, classLoader: ClassLoader): java.lang.Class[_] = {
    val tokens = file.toString.replace(File.separator, UnitTestsRunner.CLASS_NAME_TOKENS_SEPARATOR).split("\\.")
    Class.forName(tokens.dropRight(1).mkString(UnitTestsRunner.CLASS_NAME_TOKENS_SEPARATOR), true, classLoader)
  }

  private def getTestFiles(testsFile: File): mutable.Buffer[Path] = {
    Files.walk(Paths.get(testsFile.getPath)).collect(Collectors.toList()).asScala.filter(file => {
      Files.isRegularFile(file) && file.toFile.getPath.endsWith(UnitTestsRunner.TEST_FILES_SUFFIX)
    }).map(file => testsFile.toPath.relativize(file))
  }

  def runTests(testsFile: File, sourcesFile: File): Unit = {
    val testFiles = getTestFiles(testsFile)
    val classLoader = getClassLoader(testsFile, sourcesFile)
    val testClasses = testFiles.map(getClassForFile(_, sourcesFile, classLoader))

    testClasses.foreach(println)
    val result = JUnitCore.runClasses(testClasses: _*)
    result.getFailures.asScala.foreach(f => println(f.getTrace))
    println("Tests run: " + result.getRunCount + ", Failures: " + result.getFailureCount + ", Skipped: " + result.getIgnoreCount)
  }

}

object UnitTestsRunner {

  val CLASS_NAME_TOKENS_SEPARATOR = "."
  val SOURCE_FILES_SUFFIX = ".java"
  val CLASSPATH_ARGUMENT = "-cp"
  val TEST_CLASS_FILES_SUFFIX = "Test.class"
  val TEST_FILES_SUFFIX = "Test.java"

}
