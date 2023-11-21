package outputTests

import asciiConvertion.{AsciiConverter, LinearConverter}
import core.{AsciiImage, AsciiTable}
import loader.ImageFileLoader
import org.scalatest.funsuite.AnyFunSuite
import output.{ConsoleAsciiOutputWriter, OutputWriter}

class OutputConsoleTest extends AnyFunSuite{
  test("Ascii output console"){
    // load
    val loader = new ImageFileLoader("src/test/assets/bmw.jpg")
    val result = loader.load()
    if (result.isEmpty) fail("could not load")

    val output: OutputWriter[AsciiImage] = new ConsoleAsciiOutputWriter
    val converter: AsciiConverter = new LinearConverter(
      AsciiTable(Array('$', '@', 'B', '%', '8', '&', 'W', 'M', '#', '*', 'o', 'a', 'h', 'k', 'b', 'd', 'p', 'q', 'w', 'm', 'Z', 'O', '0', 'Q', 'L', 'C', 'J', 'U', 'Y', 'X', 'z', 'c', 'v', 'u', 'n', 'x', 'r', 'j', 'f', 't', '/', '\\', '|', '(', ')', '1', '{', '}', '[', ']',
        '?', '-', '_', '+', '~', '<', '>', 'i', '!', 'l', 'I', ';', ':', ',', '"', '^', '`', ' ', ' '))
    )
    val asciiArt = converter.convert(result.get)
    output.output(asciiArt)
  }
}
