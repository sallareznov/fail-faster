package fil.iagl.opl.failfaster

import fil.iagl.opl.failfaster.constants.{ ConstantsKeys, ConstantsHandler }
import org.apache.commons.cli
import org.apache.commons.cli.Options

class OptionsBuilder {

  def buildOptions(args: Array[String], constantsHandler: ConstantsHandler): Options = {
    val options = new Options()
    // sources path option
    val inputSourcesPathOption = new cli.Option(constantsHandler.getProperty(ConstantsKeys.INPUT_SOURCES_PATH_ARGUMENT_KEY), true, OptionsBuilder.INPUT_SOURCES_PATH_OPTION_DESCRIPTION)
    inputSourcesPathOption.setRequired(true)
    // tests path option
    val inputTestsPathOption = new cli.Option(constantsHandler.getProperty(ConstantsKeys.INPUT_TESTS_PATH_ARGUMENT_KEY), true, OptionsBuilder.INPUT_TESTS_PATH_OPTION_DESCRIPTION)
    inputTestsPathOption.setRequired(true)
    options.addOption(inputSourcesPathOption)
    options.addOption(inputTestsPathOption)
    options
  }

}

object OptionsBuilder {

  val INPUT_SOURCES_PATH_OPTION_DESCRIPTION = "the path to the source folder of the project to transform"
  val INPUT_TESTS_PATH_OPTION_DESCRIPTION = "the path to the tests folder of the project to transform"

}