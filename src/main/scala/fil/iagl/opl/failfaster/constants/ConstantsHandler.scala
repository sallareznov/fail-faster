package fil.iagl.opl.failfaster.constants

import java.io.InputStream
import java.util.Properties

class ConstantsHandler {

  val properties = new Properties()

  def loadConstantsFile(inputStream: InputStream): Unit = {
    properties.clear()
    properties.load(inputStream)
    inputStream.close()
  }

  def getProperty(propertyKey: String): Option[String] = {
    if (properties.isEmpty) {
      None
    }
    else {
      Some(properties.getProperty(propertyKey))
    }
  }

}
