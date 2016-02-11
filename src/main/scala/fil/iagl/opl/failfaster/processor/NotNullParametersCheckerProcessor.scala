package fil.iagl.opl.failfaster.processor

import spoon.processing.AbstractProcessor
import spoon.reflect.code.CtCodeSnippetExpression
import spoon.reflect.declaration.CtExecutable

import scala.collection.JavaConverters._

/**
  * A processor that inspects class executables (constructors and methods) and inserts an if statement
  * at the beginning of those executables to check the non nullity of the non primitive parameters.
  */
class NotNullParametersCheckerProcessor extends AbstractProcessor[CtExecutable[_]] {

  override def isToBeProcessed(candidate: CtExecutable[_]) = candidate.getBody != null

  override def process(element: CtExecutable[_]): Unit = {
    val nonPrimitiveParameters = element.getParameters.asScala.filter(!_.getType.isPrimitive)

    // if there isn't any primitive parameters, there won't be any if statement
    if (nonPrimitiveParameters.nonEmpty) {
      val nonNullityOfParametersIfStatement = getFactory.Core().createIf()
      val nonNullityOfParametersCondition: CtCodeSnippetExpression[java.lang.Boolean] = getFactory.Core().createCodeSnippetExpression()
      val nonNullityOfParametersExpressionValueTokens = nonPrimitiveParameters.map(_.getSimpleName + " == null")
      val nonNullityOfParametersExpressionValue = nonNullityOfParametersExpressionValueTokens.mkString(" || ")
      val thrownException = getFactory.Core().createThrow()
      val thrownExceptionStatement = getFactory.Core().createCodeSnippetExpression()
      thrownExceptionStatement.setValue("new IllegalArgumentException()")
      thrownException.setThrownExpression(thrownExceptionStatement)
      nonNullityOfParametersCondition.setValue(nonNullityOfParametersExpressionValue)
      nonNullityOfParametersIfStatement.setCondition(nonNullityOfParametersCondition)
      nonNullityOfParametersIfStatement.setThenStatement(thrownException)
      element.getBody.insertBegin(nonNullityOfParametersIfStatement)
      // the code above should produce : if (param1 != null || ... || paramN != null) { throw new IllegalArgumentException(); }
    }
  }

}
