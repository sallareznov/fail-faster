package fil.iagl.opl.failfaster

import java.io.File

import fil.iagl.opl.failfaster.processor.NotNullParametersCheckerProcessor
import spoon.Launcher

object Main {

  def main(args: Array[String]) {
    val launcher = new Launcher()
    launcher.addProcessor(new NotNullParametersCheckerProcessor)
    launcher.addInputResource("../m1s2/CAR/tp1/src")
    launcher.run()

    // we verify that the code produced compiles
    val compiler = launcher.createCompiler()
    compiler.addInputSource(new File("spooned"))
    compiler.compile()
  }

}
