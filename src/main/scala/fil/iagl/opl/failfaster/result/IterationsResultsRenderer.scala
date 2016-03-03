package fil.iagl.opl.failfaster.result

import de.vandermeer.asciitable.v2.render.{ WidthLongestLine, V2_AsciiTableRenderer }
import de.vandermeer.asciitable.v2.themes.V2_E_TableThemes
import de.vandermeer.asciitable.v2.{ V2_AsciiTable, RenderedTable }

class IterationsResultsRenderer {

  def renderResults(iterationsResults: IterationsResults): RenderedTable = {
    val asciiTable = new V2_AsciiTable()

    val renderer = new V2_AsciiTableRenderer()
    renderer.setTheme(V2_E_TableThemes.PLAIN_7BIT_STRONG.get())
    renderer.setWidth(new WidthLongestLine())

    val columnsAlignments = Array('c', 'c', 'c')

    asciiTable.addRule()
    val firstRow = asciiTable.addRow(IterationsResultsRenderer.ITERATION_LABEL, IterationsResultsRenderer.NUMBER_OF_MODIFICATIONS_LABEL, IterationsResultsRenderer.NUMBER_OF_FAILING_TESTS_LABEL)
    firstRow.setAlignment(columnsAlignments)
    asciiTable.addRule()

    iterationsResults.results.foreach(result => {
      val row = asciiTable.addRow(result.iterationCounter.toString, result.nbOfModifications.toString, result.nbOfFailingTests.toString)
      row.setAlignment(columnsAlignments)
      asciiTable.addRule()
    })

    renderer.render(asciiTable)
  }

}

object IterationsResultsRenderer {

  val ITERATION_LABEL = "Iteration"
  val NUMBER_OF_MODIFICATIONS_LABEL = "Number of modifications"
  val NUMBER_OF_FAILING_TESTS_LABEL = "Number of failing tests"

}
