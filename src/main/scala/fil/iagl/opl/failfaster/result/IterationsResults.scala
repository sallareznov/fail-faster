package fil.iagl.opl.failfaster.result

import scala.collection.mutable.ListBuffer

class IterationsResults {

  val results = ListBuffer[IterationResult]()

  def addResult(result: IterationResult): Unit = results += result

}
