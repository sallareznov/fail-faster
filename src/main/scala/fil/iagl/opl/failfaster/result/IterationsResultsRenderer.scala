package fil.iagl.opl.failfaster.result

import de.vandermeer.asciitable.v2.render.{WidthLongestLine, V2_AsciiTableRenderer}
import de.vandermeer.asciitable.v2.themes.V2_E_TableThemes
import de.vandermeer.asciitable.v2.{V2_AsciiTable, RenderedTable}

class IterationsResultsRenderer {

  def renderResults(iterationsResults: IterationsResults): RenderedTable = {
    val at = new V2_AsciiTable()

    val renderer = new V2_AsciiTableRenderer()
    renderer.setTheme(V2_E_TableThemes.PLAIN_7BIT_STRONG.get())
    renderer.setWidth(new WidthLongestLine())

    val columnsAlignments = Array('c', 'c', 'c')

    at.addRule()
    at.addRow("Iteration", "Number of modifications", "Number of failing tests").setAlignment(columnsAlignments)
    at.addRule()

    iterationsResults.results.foreach(result => {
      at.addRow(result.iterationCounter.toString, result.nbOfModifications.toString, result.nbOfFailingTests.toString).setAlignment(columnsAlignments)
      at.addRule()
    })

    renderer.render(at)
  }

}
