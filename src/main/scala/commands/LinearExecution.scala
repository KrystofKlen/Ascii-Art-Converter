package commands

import asciiConvertion.{AsciiTableProvider, LinearConverter}
import core.Image
import filters.{Brightness, Flip, Greyscale, Invert}
import loader.ImageFileLoader
import output.{AsciiFileOutputWriter, ConsoleAsciiOutputWriter}

class LinearExecution(cmds:List[Command[_]]) extends Executor {
  override def executeCommands(): Unit = {

    var imgProduct: Option[Image] = Option.empty
    cmds.foreach {
      case cmd: Command[_] => {
        cmd match {
          case command: LoadFromFile =>
            var loader = new ImageFileLoader(command.arg.get)
            imgProduct = loader.load()
          case _ =>
        }
      }
    }

    cmds.foreach{
      case cmd: Command[_] => {
        cmd match {
          case command: BrightnessCmd =>
            val filter = new Brightness(command.arg.get)
            imgProduct = Option( filter.apply( imgProduct.getOrElse(throw new Exception )))

          case command: FlipCmd =>
            val filter = new Flip(command.arg.get)
            imgProduct = Option(filter.apply(imgProduct.getOrElse(throw new Exception)))

          case command: InvertCmd =>
            val filter = new Invert()
            imgProduct = Option(filter.apply(imgProduct.getOrElse(throw new Exception)))

          case _ =>
        }
      }
    }

    cmds.foreach{
      case cmd: Command[_] => {
            cmd match {
              case command: OutputFileCmd =>
                val filter = new Greyscale
                imgProduct = Option(filter.apply(imgProduct.getOrElse(throw new Exception())))
                val converter = new LinearConverter(AsciiTableProvider.DEFAULT_TABLE)
                val ascii = converter.convert(imgProduct.getOrElse(throw new Exception()))
                val outputCmd = new AsciiFileOutputWriter(command.arg.get)
                outputCmd.output(ascii)
              case command: OutputConsoleCmd =>
                val filter = new Greyscale
                imgProduct = Option(filter.apply(imgProduct.getOrElse(throw new Exception())))
                val converter = new LinearConverter(AsciiTableProvider.DEFAULT_TABLE)
                val ascii = converter.convert(imgProduct.getOrElse(throw new Exception()))
                val outputCmd = new ConsoleAsciiOutputWriter
                outputCmd.output(ascii)
              case _ =>
            }
      }
    }
  }
}
