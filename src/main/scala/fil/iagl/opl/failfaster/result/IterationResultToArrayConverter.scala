package fil.iagl.opl.failfaster.result

class IterationResultToArrayConverter {

  def convertIterationResultToArray(iterationResult: IterationResult): Array[String] = Array(iterationResult.iterationCounter, iterationResult.nbOfModifications, iterationResult.nbOfFailingTest).map(_.toString)

}
