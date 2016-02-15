package fil.iagl.opl.failfaster

import java.io.File
import java.nio.file.{Files, Paths}
import java.util.stream.Collectors
import javax.tools.ToolProvider

import scala.collection.JavaConverters._

object Test {

  /*def main(args: Array[String]) {
    val compiler = ToolProvider.getSystemJavaCompiler
    val sourceFiles = Files.walk(Paths.get(new File("../jsoup/src/main/java").getPath)).collect(Collectors.toList()).asScala.filter(file => {
      Files.isRegularFile(file) && file.toFile.getPath.endsWith(".java")
    })

    val testFiles = Files.walk(Paths.get(new File("../jsoup/src/test/java").getPath)).collect(Collectors.toList()).asScala.filter(file => {
      Files.isRegularFile(file) && file.toFile.getPath.endsWith(".java")
    })
    sourceFiles.foreach(file => compiler.run(null, null, null, file.toString, "-d", "spooned-classes", "-cp", "../jsoup/src/main/java"))
    testFiles.foreach(file => compiler.run(null, null, null, file.toString, "-d", "spooned-classes", "-cp", "../jsoup/src/main/java:../jsoup/src/test/java:jsoup/src/test/resources:lib/junit-4.12.jar:lib/mockito-all-1.10.19.jar:lib/powermock-mockito-1.6.1-full.jar"))
  }*/

  def main(args: Array[String]) {
    val compiler = ToolProvider.getSystemJavaCompiler
    val sourceFiles = Files.walk(Paths.get(new File("../m1s2/CAR/tp1/src").getPath)).collect(Collectors.toList()).asScala.filter(file => {
      Files.isRegularFile(file) && file.toFile.getPath.endsWith(".java")
    })

    val testFiles = Files.walk(Paths.get(new File("../m1s2/CAR/tp1/test").getPath)).collect(Collectors.toList()).asScala.filter(file => {
      Files.isRegularFile(file) && file.toFile.getPath.endsWith(".java")
    })
    sourceFiles.foreach(file => compiler.run(null, null, null, file.toString, "-d", "spooned-classes", "-cp", "../m1s2/CAR/tp1/src"))
    testFiles.foreach(file => compiler.run(null, null, null, file.toString, "-d", "spooned-classes", "-cp", "../m1s2/CAR/tp1/test" + File.pathSeparator + "../m1s2/CAR/tp1/src" + File.pathSeparator + "lib" + File.separator + "junit-4.12.jar" + File.pathSeparator + "lib" + File.separator + "mockito-all-1.10.19.jar" + File.pathSeparator + "lib" + File.separator + "powermock-mockito-1.6.1-full.jar" + File.pathSeparator + "lib" + File.separator + "powermock-module-junit4-1.6.4.jar"))
  }

  /*def main(args: Array[String]) {
    val inputProjectCompiler = new Launcher().createCompiler()
    inputProjectCompiler.getFactory.getEnvironment.setComplianceLevel(8)
    inputProjectCompiler.addInputSource(new File(ProcessingCommand.inputSourcePath))
    inputProjectCompiler.setSourceClasspath("lib/mockito-all-1.10.19.jar", "powermock-mockito-1.6.1-full.jar")
    inputProjectCompiler.addInputSource(new File(ProcessingCommand.inputTestsPath))
    sourceClasspath.foreach(println)
    inputProjectCompiler.setSourceClasspath(sourceClasspath: _*)
    inputProjectCompiler.getFactory.getEnvironment.setAutoImports(true)
  }*/

}
