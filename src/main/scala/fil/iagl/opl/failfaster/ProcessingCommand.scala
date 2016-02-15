package fil.iagl.opl.failfaster

import java.io.File

import fil.iagl.opl.failfaster.processor.NotNullParametersCheckerProcessor
import org.backuity.clist._
import spoon.Launcher



/**
  * Main command
  */
object ProcessingCommand extends Command(name = "failfaster", description = "Failfaster transforms a fail-safe Java software to a fail-fast one") {

  var inputSourcePath = opt[String](name = "inputSourcePath", default = "none", description = "the path to the source folder of the project to transform")
  var inputTestsPath = opt[String](name = "inputTestsPath", default = "none", description = "the path to the tests folder of the project to transform")
  var outputPath = opt[String](name = "outputPath", default = "spooned", description = "the path to the folder of the transformed project")

  /**
    * main method
    */
  def run(sourceClasspath: Array[String]): Unit = {
    println(ProcessingCommand.inputSourcePath)
    val launcher = new Launcher()

    // compilation of the tested project
    val inputProjectCompiler = launcher.createCompiler()
    inputProjectCompiler.getFactory.getEnvironment.setComplianceLevel(8)
    inputProjectCompiler.addInputSource(new File(ProcessingCommand.inputSourcePath))
    inputProjectCompiler.setSourceClasspath()
    inputProjectCompiler.addInputSource(new File(ProcessingCommand.inputTestsPath))
    sourceClasspath.foreach(println)
    inputProjectCompiler.setSourceClasspath(sourceClasspath: _*)
    inputProjectCompiler.getFactory.getEnvironment.setAutoImports(true)
    //inputProjectCompiler.getFactory.getEnvironment.setLevel("ERROR")
    inputProjectCompiler.compile()

    val outputFile = new File(ProcessingCommand.outputPath)
    outputFile.createNewFile

    launcher.addInputResource(ProcessingCommand.inputSourcePath)
    launcher.setSourceOutputDirectory(ProcessingCommand.outputPath)

    // transformation of the sources
    launcher.addProcessor(new NotNullParametersCheckerProcessor)
    launcher.run()

    // compilation of the generated project
    val outputProjectCompiler = launcher.createCompiler()
    //outputProjectCompiler.getFactory.getEnvironment.setComplianceLevel(8)
    outputProjectCompiler.addInputSource(new File(ProcessingCommand.outputPath))
   // outputProjectCompiler.compile()
    outputProjectCompiler.getFactory.getEnvironment.reportEnd()
  }

}