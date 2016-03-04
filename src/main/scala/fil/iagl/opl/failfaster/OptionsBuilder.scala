package fil.iagl.opl.failfaster

import fil.iagl.opl.failfaster.constants.{ ConstantsKeys, ConstantsHandler }
import org.apache.commons.cli
import org.apache.commons.cli.Options

class OptionsBuilder {

  def buildOptions(args: Array[String], constantsHandler: ConstantsHandler): Options = {
    val options = new Options()

    val inputSourcesPathOption = new cli.Option(constantsHandler.getProperty(ConstantsKeys.INPUT_SOURCES_PATH_ARGUMENT_KEY), true, OptionsBuilder.INPUT_SOURCES_PATH_OPTION_DESCRIPTION)
    inputSourcesPathOption.setRequired(true)

    val inputTestsPathOption = new cli.Option(constantsHandler.getProperty(ConstantsKeys.INPUT_TESTS_PATH_ARGUMENT_KEY), true, OptionsBuilder.INPUT_TESTS_PATH_OPTION_DESCRIPTION)
    inputTestsPathOption.setRequired(true)

    val helpOption = new cli.Option(constantsHandler.getProperty(ConstantsKeys.HELP_ARGUMENT_KEY), false, OptionsBuilder.HELP_OPTION_DESCRIPTION)

    options.addOption(inputSourcesPathOption)
    options.addOption(inputTestsPathOption)
    options.addOption(helpOption)

    options
  }

}

object OptionsBuilder {

  val INPUT_SOURCES_PATH_OPTION_DESCRIPTION = "the path to the source folder of the project to transform"
  val INPUT_TESTS_PATH_OPTION_DESCRIPTION = "the path to the tests folder of the project to transform"
  val HELP_OPTION_DESCRIPTION = "display the usage of the program"

}