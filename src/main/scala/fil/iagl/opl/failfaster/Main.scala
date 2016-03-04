package fil.iagl.opl.failfaster

import java.io.File
import java.util.Properties

import fil.iagl.opl.failfaster.constants.{ ConstantsKeys, ConstantsHandler }
import org.apache.commons.cli._

import scala.util.control.NonFatal

object Main {

  private def usage(options: Options): Unit = {
    val formatter = new HelpFormatter()
    formatter.printHelp("transforms a fail-safe project to a fail-fast one", options)
  }

  def main(args: Array[String]) {
    val properties = new Properties()
    val constantsHandler = new ConstantsHandler(properties)

    val PROPERTIES_FILE_PATH = "/fil/iagl/opl/failfaster/constants/constants.properties"
    constantsHandler.loadConstantsFile(getClass.getResourceAsStream(PROPERTIES_FILE_PATH))
    val INPUT_SOURCES_PATH_ARGUMENT = constantsHandler.getProperty(ConstantsKeys.INPUT_SOURCES_PATH_ARGUMENT_KEY)
    val INPUT_TESTS_PATH_ARGUMENT = constantsHandler.getProperty(ConstantsKeys.INPUT_TESTS_PATH_ARGUMENT_KEY)

    val optionsHandler = new OptionsBuilder()
    val options = optionsHandler.buildOptions(args, constantsHandler)
    val commandLineParser = new DefaultParser()
    try {
      val commandLine = commandLineParser.parse(options, args)

      val sourceFiles = new File(commandLine.getOptionValue(INPUT_SOURCES_PATH_ARGUMENT))
      val testFiles = new File(commandLine.getOptionValue(INPUT_TESTS_PATH_ARGUMENT))

      val projectCompiler = new ProjectCompiler()

      projectCompiler.compileSourcesFiles(sourceFiles, constantsHandler)
      projectCompiler.compileTestsFiles(testFiles, sourceFiles, constantsHandler)

      val unitTestsRunner = new UnitTestsRunner()
      unitTestsRunner.runTests(new File("spooned-classes"))
    } catch {
      case NonFatal(_) => usage(options)
    }
  }

}
