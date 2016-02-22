package fil.iagl.opl.failfaster

import java.io.File
import java.lang.Class
import java.net.URLClassLoader
import java.nio.file.{Files, Path, Paths}
import java.util.stream.Collectors

import org.junit.runner.JUnitCore

import scala.collection.JavaConverters._
import scala.collection.mutable

class UnitTestsRunner {

  def getClassLoader(outputPath: File): ClassLoader = {
    URLClassLoader.newInstance(Array(outputPath.toURI.toURL), getClass.getClassLoader)
  }

  def getClassForFile(file: Path, classLoader: ClassLoader): java.lang.Class[_] = {
    val tokens = file.toString.replace(File.separator, ".").split("\\.")
    Class.forName(tokens.drop(1).dropRight(1).mkString("."), true, classLoader)
  }

  def getTestFiles(binaryOutputDirectory: File): mutable.Buffer[Path] = {
    Files.walk(Paths.get(binaryOutputDirectory.getPath)).collect(Collectors.toList()).asScala.filter(file => {
      Files.isRegularFile(file) && file.toFile.getPath.endsWith(UnitTestsRunner.TEST_CLASS_FILES_SUFFIX)
    })
  }

  def runTests(binaryOutputDirectory: File): Unit = {
    val testFiles = getTestFiles(binaryOutputDirectory)
    val classLoader = getClassLoader(binaryOutputDirectory)
    val classes = testFiles.map(getClassForFile(_, classLoader))
    classes.foreach(println)
    val runner = new JUnitCore()
    val result = runner.run(classes: _*)
    //println(result.getFailures.asScala.head.getTrace)
    result.getFailures.asScala.foreach(f => println(f.getTrace))
    println("Tests run: " + result.getRunCount + ", Failures: " + result.getFailureCount + ", Skipped: " + result.getIgnoreCount)
  }

}

object UnitTestsRunner {

  val SOURCE_FILES_SUFFIX = ".java"
  val CLASSPATH_ARGUMENT = "-cp"
  val TEST_CLASS_FILES_SUFFIX = "Test.class"
  val TEST_FILES_SUFFIX = "Test.java"

}
