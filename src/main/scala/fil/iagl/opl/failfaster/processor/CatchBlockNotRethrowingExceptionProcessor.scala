package fil.iagl.opl.failfaster.processor

import fil.iagl.opl.failfaster.ElementsModificationCounter
import spoon.processing.AbstractProcessor
import spoon.reflect.code.{CtCatch, CtThrow}
import spoon.reflect.visitor.filter.TypeFilter

class CatchBlockNotRethrowingExceptionProcessor(elementsModificationCounter: ElementsModificationCounter) extends AbstractProcessor[CtCatch] {

  override def isToBeProcessed(candidate: CtCatch): Boolean = candidate.getBody.getElements(new TypeFilter[CtThrow](classOf[CtThrow])).isEmpty

  override def process(element: CtCatch): Unit = {
    val exceptionToBeThrown = getFactory.Core().createCodeSnippetExpression[Throwable]()
    exceptionToBeThrown.setValue("new RuntimeException()")
    val throwExpression = getFactory.Core().createThrow()
    throwExpression.setThrownExpression(exceptionToBeThrown)
    element.getBody.insertEnd(throwExpression)
  }

}
