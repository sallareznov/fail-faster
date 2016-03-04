package fil.iagl.opl.failfaster.constants

import java.io.InputStream
import java.util.Properties

class ConstantsHandler(properties: Properties) {

  def loadConstantsFile(inputStream: InputStream): Unit = {
    properties.clear()
    properties.load(inputStream)
    inputStream.close()
  }

  def getProperty(propertyKey: String): String = properties.getProperty(propertyKey, ConstantsHandler.DEFAULT_VALUE)

}

object ConstantsHandler {

  val DEFAULT_VALUE = "NULL"

}
