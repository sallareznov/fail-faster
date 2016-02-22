package fil.iagl.opl.failfaster

import java.io.File

import fil.iagl.opl.failfaster.constants.ConstantsHandler
import spoon.Launcher

class ProjectCompiler {

  def compileSourcesFiles(sourcesFiles: File, constantsHandler: ConstantsHandler): Unit = {
    val compiler = new Launcher().createCompiler()
    compiler.getFactory.getEnvironment.setComplianceLevel(8)
    compiler.addInputSource(sourcesFiles)
    compiler.getFactory.getEnvironment.setAutoImports(true)
    compiler.compile()
  }

  def compileTestsFiles(testsFiles: File, sourcesFiles: File, constantsHandler: ConstantsHandler): Unit = {
    val compiler = new Launcher().createCompiler()
    compiler.getFactory.getEnvironment.setComplianceLevel(8)
    compiler.addInputSource(testsFiles)
    val librairiesDirectory = constantsHandler.getProperty("librairiesDirectory").get
    val sourceClasspath = Array(
      sourcesFiles.getCanonicalPath, testsFiles.getCanonicalPath,
      librairiesDirectory + File.separator + constantsHandler.getProperty("assertJJarName").get,
      librairiesDirectory + File.separator + constantsHandler.getProperty("junitJarName").get,
      librairiesDirectory + File.separator + constantsHandler.getProperty("mockitoJarName").get,
      librairiesDirectory + File.separator + constantsHandler.getProperty("powermockitoJarName").get,
      librairiesDirectory + File.separator + constantsHandler.getProperty("powermockJarName").get
    )
    compiler.setSourceClasspath(sourceClasspath: _*)
    compiler.compile()
  }

}
