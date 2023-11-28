package outputTests

import asciiConvertion.{AsciiConverter, AsciiTableProvider, LinearConverter}
import core.{AsciiImage, AsciiTable}
import loader.ImageFileLoader
import org.scalatest.funsuite.AnyFunSuite
import output.{ConsoleAsciiOutputWriter, OutputWriter}
import testUtils.TestUtils

class OutputConsoleTest extends AnyFunSuite{
  test("Ascii output console"){
    // load
    val result = TestUtils.loadImg(TestUtils.TEST_IMG_SRC)

    val output: OutputWriter[AsciiImage] = new ConsoleAsciiOutputWriter
    val converter: AsciiConverter = new LinearConverter(
      AsciiTableProvider.DEFAULT_TABLE
    )
    val asciiArt = converter.convert(result)
    output.output(asciiArt)
  }
}
