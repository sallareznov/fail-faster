package fil.iagl.opl.failfaster

import fil.iagl.opl.failfaster.constants.ConstantsHandler
import org.apache.commons.cli
import org.apache.commons.cli.Options

class OptionsHandler {

  def handleOptions(args: Array[String], constantsHandler: ConstantsHandler): Options = {
    val options = new Options()

    val inputSourcesPathOption = new cli.Option(constantsHandler.getProperty("inputSourcesPathArgument").get, true, "the path to the source folder of the project to transform")
    inputSourcesPathOption.setRequired(true)

    val inputTestsPathOption = new cli.Option(constantsHandler.getProperty("inputTestsPathArgument").get, true, "the path to the tests folder of the project to transform")
    inputTestsPathOption.setRequired(true)

    val helpOption = new cli.Option(constantsHandler.getProperty("helpArgument").get, false, "display the usage of the program")

    options.addOption(inputSourcesPathOption)
    options.addOption(inputTestsPathOption)
    options.addOption(helpOption)

    options
  }

}
