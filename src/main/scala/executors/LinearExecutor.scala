package executors

import asciiConvertion.{AsciiTableProvider, LinearConverter}
import commands._
import core.{AsciiImage, Image}
import filters._
import imageGenerators.RandomImageGenerator
import loader.ImageFileLoader
import output.{AsciiFileOutputWriter, ConsoleAsciiOutputWriter}

class LinearExecutor(cmds:List[Command[_]]) extends Executor {
  override def executeCommands(): Unit = {

    var imgProduct: Option[Image] = Option.empty

    cmds.foreach {
      case cmd: Command[_] => {
        cmd match {
          case command: LoadFromFileCmd =>
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
            imgProduct = applyFilter(new Brightness(command.arg.get),imgProduct)

          case command: FlipCmd =>
            imgProduct = applyFilter( new Flip(command.arg.get) ,imgProduct)

          case command: InvertCmd =>
            imgProduct = applyFilter( new Invert() ,imgProduct)

          case command: RandomImgCmd =>
            val imageGenerator = new RandomImageGenerator
            imgProduct = Option(imageGenerator.generateImage())

          case _ =>
        }
      }
    }

    cmds.foreach{
      case cmd: Command[_] => {
            cmd match {
              case command: OutputFileCmd =>
                imgProduct = applyFilter(new Greyscale, imgProduct)
                val outputCmd = new AsciiFileOutputWriter(command.arg.get)
                outputCmd.output(getAsciiArt(imgProduct))
              case command: OutputConsoleCmd =>
                imgProduct = applyFilter(new Greyscale, imgProduct)
                val outputCmd = new ConsoleAsciiOutputWriter
                outputCmd.output(getAsciiArt(imgProduct))
              case _ =>
            }
      }
    }
  }

  private def loadData(): Option[Image]  = {
    var imgProduct: Option[Image] = Option.empty
    cmds.foreach {
      case cmd: Command[_] => {
        cmd match {
          case command: LoadFromFileCmd =>
            var loader = new ImageFileLoader(command.arg.get)
            imgProduct = loader.load()
          case _ =>
        }
      }
    }
    imgProduct
  }

  private def applyFilter(filter: Filter,imgProduct: Option[Image]): Option[Image] = {
    Option(filter.apply(imgProduct.getOrElse(throw new Exception)))
  }

  private def getAsciiArt(imgProduct: Option[Image]): AsciiImage = {
    val converter = new LinearConverter(AsciiTableProvider.DEFAULT_TABLE)
    converter.convert(imgProduct.getOrElse(throw new Exception()))
  }
}
