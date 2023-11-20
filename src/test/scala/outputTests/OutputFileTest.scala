package outputTests

import asciiConvertion.{AsciiConverter, LinearConverter}
import core.{AsciiImage, AsciiTable, Image}
import loader.ImageFileLoader
import org.scalatest.funsuite.AnyFunSuite
import output.{AsciiFileOutputWriter, ConsoleAsciiOutputWriter, ImageFileOutputWriter, OutputWriter}

class OutputFileTest extends AnyFunSuite{

  test("should create a file and write image in the file"){
    val loader = new ImageFileLoader("src/test/assets/parrot.jpg")
    val result = loader.load()
    if(result.isEmpty) fail("could not load")
    val output:OutputWriter[Image] = new ImageFileOutputWriter("src/test/assets/test_output.jpg","jpg")
    val success = output.output(result.get)
    assert(success)
  }

  test("should create file with ascii art"){
    val loader = new ImageFileLoader("src/test/assets/parrot.jpg")
    val result = loader.load()
    if (result.isEmpty) fail("could not load")


    val output: OutputWriter[AsciiImage] = new AsciiFileOutputWriter("src/test/assets/test_output_2");
    val converter: AsciiConverter = new LinearConverter(
      AsciiTable(Array('$', '@', 'B', '%', '8', '&', 'W', 'M', '#', '*', 'o', 'a', 'h', 'k', 'b', 'd', 'p', 'q', 'w', 'm', 'Z', 'O', '0', 'Q', 'L', 'C', 'J', 'U', 'Y', 'X', 'z', 'c', 'v', 'u', 'n', 'x', 'r', 'j', 'f', 't', '/', '\\', '|', '(', ')', '1', '{', '}', '[', ']',
        '?', '-', '_', '+', '~', '<', '>', 'i', '!', 'l', 'I', ';', ':', ',', '"', '^', '`', ' ', ' '))
    )
    val asciiArt = converter.convert(result.get)
    val success = output.output(asciiArt)
    assert(success)
  }

}
