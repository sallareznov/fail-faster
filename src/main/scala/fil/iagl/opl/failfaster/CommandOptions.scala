package fil.iagl.opl.failfaster

import org.backuity.clist._

object CommandOptions extends Command(name = "failfaster", description = "Failfaster transforms a fail-safe Java software to a fail-fast one") {

  var inputSourcePath = opt[String](name = "inputSourcePath", description = "the path to the source folder of the project to transform")
  var inputTestsPath = opt[String](name = "inputTestsPath", description = "the path to the tests folder of the project to transform")
  var outputPath = opt[String](name = "outputPath", default = "spooned", description = "the path to the folder of the transformed project")

}
