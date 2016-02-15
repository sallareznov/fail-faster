package fil.iagl.opl.failfaster

import java.io.File

import org.backuity.clist.Cli

/**
  * Entry point
  */
object Main {

  def main(args: Array[String]) {
    Cli.parse(args).withProgramName("failfaster").withCommand(ProcessingCommand) {
      case command => command.run(Array(ProcessingCommand.inputSourcePath) ++ new File("lib").listFiles().map(_.getAbsolutePath))
    }
  }

}
