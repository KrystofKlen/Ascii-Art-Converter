package commands

import asciiConvertion.{AsciiTableProvider, LinearConverter}
import core.{AsciiTable, Image}
import filters.{Brightness, FLIP_AXIS, Filter, Flip, Invert}
import loader.ImageFileLoader
import output.{AsciiFileOutputWriter, ConsoleAsciiOutputWriter, ImageFileOutputWriter}
import parser.CommandToken

class LinearExecutor(cmdTokens: List[CommandToken]) extends Executor {
  private val order = List("image","flip","brightness","invert", "output-file","output-console")
  println(cmdTokens)
  var ordered = sortCommands
  // read load
  val option = ordered.head.option
  if( !option.equals("image") || ordered.head.args.size != 1){
    println(option)
    //throw IllegalThreadStateException
  }
  val loader = new ImageFileLoader(ordered.head.args.head);

  val imgOpt = loader.load()
  if(imgOpt.isEmpty){
    //throw Exception
  }

  ordered = ordered.drop(1);
  var img = imgOpt.get
  println(ordered)

  var asciiTableSelected = AsciiTableProvider.DEFAULT_TABLE

  for(token<-ordered){
    token.option match {

      case "invert" =>
        if(token.args.isEmpty){
          val filter = new Invert()
          img = filter.apply(img)
        }

      case "brightness" =>
        if(token.args.size==1){
          val filter = new Brightness(token.args.head.toInt)
          img = filter.apply(img)
        }else{
          throw new IllegalArgumentException
        }

      case "flip" =>
        if(token.args.size != 1){
          throw new IllegalArgumentException
        }
        if(token.args.head.equals("x")){
          val filter = new Flip(FLIP_AXIS.X)
          img = filter.apply(img)
        }
        if(token.args.head.equals("y")){
          val filter = new Flip(FLIP_AXIS.Y)
          img = filter.apply(img)
        }

      case "table" =>
        if(token.args.size != 1){
          throw new IllegalArgumentException
        }
        asciiTableSelected = AsciiTableProvider.customTable(token.args.head.toCharArray)

      case "output-console" =>
        val converter = new LinearConverter(asciiTableSelected)
        val ascii = converter.convert(img)
        val saver = new ConsoleAsciiOutputWriter
        saver.output(ascii)

      case "output-file" =>
        if(token.args.size != 1){
          throw new IllegalArgumentException
        }
        val converter = new LinearConverter(asciiTableSelected)
        val ascii = converter.convert(img)
        val saver = new AsciiFileOutputWriter(token.args.head)
        saver.output(ascii)
    }
  }

  private def sortCommands: List[CommandToken] = {
    cmdTokens.sortBy(token => order.indexOf(token.option))
  }


}
