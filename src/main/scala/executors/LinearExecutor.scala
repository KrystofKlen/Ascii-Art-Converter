package executors

import asciiConvertion.{AsciiTableProvider, LinearConverter}
import commands._
import core.{AsciiImage, AsciiTable, Image}
import filters._
import imageGenerators.RandomImageGenerator
import loader.ImageFileLoader
import output.{AsciiFileOutputWriter, ConsoleAsciiOutputWriter}

import java.io.IOException

/**
 * Executes given commands in strict, linear, predefined order.
 * @param cmds commands to execute
 */
class LinearExecutor(cmds:List[Command[_]]) extends Executor {

  /**
   * Commands are executed in topological equivalent groups ->
   * Load - Apply filters - Set ascii table - Output
   */
  override def executeCommands(): Unit = {
    var imgProduct: Option[Image] = Option.empty
    imgProduct = loadData();
    imgProduct = applyFiltes(imgProduct)
    var asciiTable = setAsciiTable()
    output(imgProduct,asciiTable)
  }

  /**
   * Iterates commands and executed those which read the image from source.
   * @return Image or Option.empty if Image command was not found. Throws IO
   */
  private def loadData(): Option[Image] = {
    var imgProduct: Option[Image] = Option.empty
    cmds.foreach {
      case cmd: Command[_] => {
        cmd match {
          case command: LoadFromFileCmd =>
            var loader = new ImageFileLoader(command.arg.get)
            imgProduct = loader.load()

          case command: RandomImgCmd =>
            val imageGenerator = new RandomImageGenerator
            imgProduct = Option(imageGenerator.generateImage())

          case _ => // continue iteration
        }
      }
    }
    imgProduct
  }

  private def applyFiltes(product: Option[Image]): Option[Image]  = {
    var imgProduct: Option[Image] = product
    cmds.foreach {
      case cmd: Command[_] => {
        cmd match {
          case command: BrightnessCmd =>
            imgProduct = applyFilter(new Brightness(command.arg.get), imgProduct)

          case command: FlipCmd =>
            imgProduct = applyFilter(new Flip(command.arg.get), imgProduct)

          case command: InvertCmd =>
            imgProduct = applyFilter(new Invert(), imgProduct)

          case _ => // continue iteration
        }
      }
    }
    imgProduct
  }

  // If no requirement for AsciiTable then the DEFAULT_TABLE is used.
  private def setAsciiTable(): AsciiTable = {
    var asciiTable: AsciiTable = AsciiTableProvider.DEFAULT_TABLE
        cmds.foreach {
          case command: CustomTableCmd =>
            asciiTable = AsciiTableProvider.customTable(command.arg.get.chars)

          case command: PickAsciiTableCmd =>
            asciiTable = command.arg.get

          case _ => // continue iteration
        }
    asciiTable
  }

  private def output(product: Option[Image], asciiTable: AsciiTable): Unit = {
    var imgProduct = product
    cmds.foreach {
      case cmd: Command[_] => {
        cmd match {
          case command: OutputFileCmd =>
            imgProduct = applyFilter(new Greyscale, imgProduct)
            val outputCmd = new AsciiFileOutputWriter(command.arg.get)
            outputCmd.output(getAsciiArt(imgProduct, asciiTable))
          case command: OutputConsoleCmd =>
            imgProduct = applyFilter(new Greyscale, imgProduct)
            val outputCmd = new ConsoleAsciiOutputWriter
            outputCmd.output(getAsciiArt(imgProduct, asciiTable))
          case _ => // continue iteration
        }
      }
    }
  }

  private def applyFilter(filter: Filter,imgProduct: Option[Image]): Option[Image] = {
    Option(filter.apply(imgProduct.getOrElse(throw new Exception)))
  }

  private def getAsciiArt(imgProduct: Option[Image], asciiTable: AsciiTable): AsciiImage = {
    val converter = new LinearConverter(asciiTable)
    converter.convert(imgProduct.getOrElse(throw new Exception()))
  }

}
