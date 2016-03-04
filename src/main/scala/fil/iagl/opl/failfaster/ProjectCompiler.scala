package fil.iagl.opl.failfaster

import java.io.File

import fil.iagl.opl.failfaster.constants.{ ConstantsKeys, ConstantsHandler }
import spoon.Launcher

class ProjectCompiler {

  def compileSourcesFiles(sourcesFiles: File, constantsHandler: ConstantsHandler): Unit = {
    val compiler = new Launcher().createCompiler()
    compiler.getFactory.getEnvironment.setComplianceLevel(ProjectCompiler.JAVA_COMPLIANCE_LEVEL)
    compiler.addInputSource(sourcesFiles)
    compiler.getFactory.getEnvironment.setAutoImports(true)
    compiler.compile()
  }

  def compileTestsFiles(testsFiles: File, sourcesFiles: File, constantsHandler: ConstantsHandler): Unit = {
    val compiler = new Launcher().createCompiler()
    compiler.getFactory.getEnvironment.setComplianceLevel(ProjectCompiler.JAVA_COMPLIANCE_LEVEL)
    compiler.addInputSource(testsFiles)
    val librairiesDirectory = constantsHandler.getProperty(ConstantsKeys.LIBRAIRES_DIRECTORY_KEY)
    val dependencies = constantsHandler.getProperty(ConstantsKeys.DEPENDENCIES_KEY).split(ProjectCompiler.DEPENDENCIES_SEPARATOR)
    val sourceClasspath = List(sourcesFiles.getCanonicalPath, testsFiles.getCanonicalPath) ++
    dependencies.map(dependency => librairiesDirectory + File.separator + dependency)
    compiler.setSourceClasspath(sourceClasspath: _*)
    compiler.compile()
  }

}

object ProjectCompiler {

  val DEPENDENCIES_SEPARATOR = ","
  val JAVA_COMPLIANCE_LEVEL = 8

}
