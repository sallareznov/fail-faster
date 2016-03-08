package fil.iagl.opl.failfaster

import java.io.File
import java.nio.file.{Files, Paths}
import java.util.stream.Collectors
import javax.tools.JavaCompiler

import fil.iagl.opl.failfaster.constants.{ConstantsHandler, ConstantsKeys}

import scala.collection.JavaConverters._

class ProjectCompiler(compiler: JavaCompiler) {

  def compileSourceFiles(sourceFilesPath: File): Unit = {
    val sourceFiles = Files.walk(Paths.get(sourceFilesPath.getPath)).collect(Collectors.toList()).asScala.filter(file => {
      Files.isRegularFile(file) && file.toFile.getPath.endsWith(ProjectCompiler.SOURCE_FILES_SUFFIX)
    })
    sourceFiles.foreach(file => {
      val arguments = List(file.toString, ProjectCompiler.CLASSPATH_ARGUMENT, sourceFilesPath.toString)
      // TODO handle the case where a file doesn't compile
      compiler.run(null, null, null, arguments: _*)
    })
  }

  def compileTestFiles(testFilesPath: File, sourceFilesPath: File, constantsHandler: ConstantsHandler): Unit = {
    val testFiles = Files.walk(Paths.get(testFilesPath.getPath)).collect(Collectors.toList()).asScala.filter(file => {
      Files.isRegularFile(file) && file.toFile.getPath.endsWith(ProjectCompiler.TEST_FILES_SUFFIX)
    })
    val librairiesDirectory = constantsHandler.getProperty(ConstantsKeys.LIBRAIRES_DIRECTORY_KEY)
    val dependencies = constantsHandler.getProperty(ConstantsKeys.DEPENDENCIES_KEY).split(ProjectCompiler.DEPENDENCIES_SEPARATOR)
    testFiles.foreach(file => {
      val arguments = List(file.toString, ProjectCompiler.CLASSPATH_ARGUMENT,
        List(sourceFilesPath.toString, testFilesPath.toString, dependencies.map(dependency => librairiesDirectory + File.separator + dependency).mkString(File.pathSeparator))
          .mkString(File.pathSeparator))
      // TODO handle the case where a file doesn't compile
      compiler.run(null, null, null, arguments: _*)
    })
  }

}

object ProjectCompiler {

  val SOURCE_FILES_SUFFIX = ".java"
  val TEST_FILES_SUFFIX = "Test.java"
  val CLASSPATH_ARGUMENT = "-cp"
  val DEPENDENCIES_SEPARATOR = ","
  val JAVA_COMPLIANCE_LEVEL = 8

}
