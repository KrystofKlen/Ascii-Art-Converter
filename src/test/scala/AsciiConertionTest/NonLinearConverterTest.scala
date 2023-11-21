package AsciiConertionTest

import asciiConvertion.{AsciiConverter, AsciiTableProvider, LinearConverter, NonLinearConverter}
import core.AsciiTable
import org.scalatest.funsuite.AnyFunSuite
import testUtils.TestUtils

import java.io.{BufferedWriter, FileWriter, IOException}

class NonLinearConverterTest extends AnyFunSuite{

  test("ascii non-linear") {
    val utils = new TestUtils
    val img = utils.loadImg("src/test/assets/parrot.jpg")
    val converter: AsciiConverter = new NonLinearConverter(
      AsciiTableProvider.DEFAULT_TABLE
    )
    val result = converter.convert(img)
    try {
      val writer = new BufferedWriter(new FileWriter("src/test/assets/test_ascii"))
      for (y <- 0 until result.getHeight) {
        for (x <- 0 until result.getWidth) {
          writer.write(result.getCharAt(x,y).get)
        }
        writer.newLine() // Move to the next line after writing each row
      }
      writer.close()
    } catch {
      case e: IOException => e.printStackTrace()
    }
  }
}
