package fil.iagl.opl.failfaster

import java.io.File
import java.util

import fil.iagl.opl.failfaster.constants.ConstantsHandler
import org.alcibiade.asciiart.raster.{RasterContext, ExtensibleCharacterRaster, CharacterRaster}
import org.alcibiade.asciiart.widget.{LabelWidget, TableWidget}
import org.alcibiade.asciiart.widget.model.{ArrayTableModel, TableModelMapAdapter}
import org.apache.commons.cli.{HelpFormatter, MissingOptionException, DefaultParser}
import org.klaus31.aat.AsciiArtTable

import scala.collection.JavaConverters._

object Main {

  val constantsHandler = new ConstantsHandler()

  val PROPERTIES_FILE_PATH = "/fil/iagl/opl/failfaster/constants/constants.properties"
  constantsHandler.loadConstantsFile(getClass.getResourceAsStream(PROPERTIES_FILE_PATH))
  val INPUT_SOURCES_PATH_ARGUMENT = constantsHandler.getProperty("inputSourcesPathArgument").get
  val INPUT_TESTS_PATH_ARGUMENT = constantsHandler.getProperty("inputTestsPathArgument").get
  val HELP_ARGUMENT = constantsHandler.getProperty("helpArgument").get

  /*def main(args: Array[String]) {
    val optionsHandler = new OptionsHandler()
    val options = optionsHandler.handleOptions(args, constantsHandler)
    val commandLineParser = new DefaultParser()
    try {
      val commandLine = commandLineParser.parse(options, args)

      val sourceFiles = new File(commandLine.getOptionValue(INPUT_SOURCES_PATH_ARGUMENT))
      val testFiles = new File(commandLine.getOptionValue(INPUT_TESTS_PATH_ARGUMENT))

      val projectCompiler = new ProjectCompiler()

      projectCompiler.compileSourcesFiles(sourceFiles, constantsHandler)
      projectCompiler.compileTestsFiles(testFiles, sourceFiles, constantsHandler)

      val unitTestsRunner = new UnitTestsRunner()
      unitTestsRunner.runTests(new File("spooned-classes"))
    }
    catch {
      case e: MissingOptionException =>
        val formatter = new HelpFormatter()
        formatter.printHelp("transforms a fail-safe project to a fail-fast one", options)
    }
  }*/

  /*def main(args: Array[String]): Unit = {
    val items = new util.TreeMap[String, Double]();
    items.put("Paris", 42.0);
    items.put("London", 2012.0);
    items.put("Amsterdam", 3.1415);

    val converter = new IterationResultToArrayConverter()
    val result1 = new IterationResult(0, 0, 0)
    val result2 = new IterationResult(1, 52, 6)
    val result3 = new IterationResult(2, 47, 4)
    val result4 = new IterationResult(3, 24, 1)

    val array = Array.ofDim[String](4, 3)
    //array.foreach(i => array(1))
    array(0)(0) = 0.toString
    array(1)(0) = 1.toString
   /* array(0) = converter.convertIterationResultToArray(result1)
    array(1) = converter.convertIterationResultToArray(result2)
    array(2) = converter.convertIterationResultToArray(result3)
    array(3) = converter.convertIterationResultToArray(result4)*/


    val tableModel = new ArrayTableModel(array)
    val tableWidget = new TableWidget(tableModel)

    val raster = new ExtensibleCharacterRaster(' ')
    tableWidget.render(new RasterContext(raster))

    println(raster)
  }*/

}
