package fil.iagl.opl.failfaster

class ElementsModificationCounter {

  // TODO find a way to avoid the mutable field
  private var nbOfModifiedElements = 0

  def incrementNbOfModifiedElements(): Unit = nbOfModifiedElements += 1

  def resetNbOfModifiedElements(): Unit = nbOfModifiedElements = 0

}
