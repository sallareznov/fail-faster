package fil.iagl.opl.failfaster

class ElementsModificationCounter {

  var nbOfModifiedElements = 0

  def incrementNbOfModifiedElements(): Unit = nbOfModifiedElements += 1

  def resetNbOfModifiedElements(): Unit = nbOfModifiedElements = 0

}
