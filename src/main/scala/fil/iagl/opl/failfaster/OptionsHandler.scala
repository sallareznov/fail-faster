package fil.iagl.opl.failfaster

import fil.iagl.opl.failfaster.constants.{ ConstantsKeys, ConstantsHandler }
import org.apache.commons.cli
import org.apache.commons.cli.Options

class OptionsHandler {

  def handleOptions(args: Array[String], constantsHandler: ConstantsHandler): Options = {
    val options = new Options()

    val inputSourcesPathOption = new cli.Option(constantsHandler.getProperty(ConstantsKeys.INPUT_SOURCES_PATH_ARGUMENT_KEY), true, "the path to the source folder of the project to transform")
    inputSourcesPathOption.setRequired(true)

    val inputTestsPathOption = new cli.Option(constantsHandler.getProperty(ConstantsKeys.INPUT_TESTS_PATH_ARGUMENT_KEY), true, "the path to the tests folder of the project to transform")
    inputTestsPathOption.setRequired(true)

    val helpOption = new cli.Option(constantsHandler.getProperty(ConstantsKeys.HELP_ARGUMENT_KEY), false, "display the usage of the program")

    options.addOption(inputSourcesPathOption)
    options.addOption(inputTestsPathOption)
    options.addOption(helpOption)

    options
  }

}
