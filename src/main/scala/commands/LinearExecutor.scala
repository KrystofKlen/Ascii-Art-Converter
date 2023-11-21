package commands

import asciiConvertion.LinearConverter
import core.{AsciiTable, Image}
import filters.{Brightness, FLIP_AXIS, Flip, Invert}
import loader.ImageFileLoader
import output.{AsciiFileOutputWriter, ImageFileOutputWriter}
import parser.CommandToken

class LinearExecutor(cmdTokens: List[CommandToken]) extends Executor {
  private val order = List("image","flip","brightness","invert", "output-file","output-console")

  var ordered = sortCommands
  // read load
  println("ORDERED: " + ordered)
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
  for(token<-ordered){
    token match {
      case CommandToken("invert", _) =>
        println("invert apply")
        val filter = new Invert()
        img = filter.apply(img)

      case CommandToken("brightness",_) =>
          val filter = new Brightness(80)
          img = filter.apply(img)

      case CommandToken("flip",_) =>
        val filter = new Flip(FLIP_AXIS.X)
        img = filter.apply(img)



      case CommandToken("output-file",_)=>
        println("saved")
        println(token)
        val s = new ImageFileOutputWriter("src/test/assets/xxxxxxx.jpg","jpg")
        s.output(img)
        val converter = new LinearConverter(AsciiTable(Array('$', '@', 'B', '%', '8', '&', 'W', 'M', '#', '*', 'o', 'a', 'h', 'k', 'b', 'd', 'p', 'q', 'w', 'm', 'Z', 'O', '0', 'Q', 'L', 'C', 'J', 'U', 'Y', 'X', 'z', 'c', 'v', 'u', 'n', 'x', 'r', 'j', 'f', 't', '/', '\\', '|', '(', ')', '1', '{', '}', '[', ']',
          '?', '-', '_', '+', '~', '<', '>', 'i', '!', 'l', 'I', ';', ':', ',', '"', '^', '`', ' ', ' ')))
        val ascii = converter.convert(img)
        val saver = new AsciiFileOutputWriter("src/test/assets/xxxxx")
        saver.output(ascii)

    }
  }





  private def sortCommands: List[CommandToken] = {
    cmdTokens.sortBy(token => order.indexOf(token.option))
  }


}
