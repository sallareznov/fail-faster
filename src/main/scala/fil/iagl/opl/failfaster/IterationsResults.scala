package fil.iagl.opl.failfaster

import scala.collection.mutable.ListBuffer

class IterationsResults {

  val results = ListBuffer[IterationResult]()

  def addResult(result: IterationResult): Unit = results += result

}
