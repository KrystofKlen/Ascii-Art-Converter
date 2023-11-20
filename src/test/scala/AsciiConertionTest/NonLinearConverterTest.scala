package AsciiConertionTest

import asciiConvertion.{AsciiConverter, LinearConverter, NonLinearConverter}
import core.AsciiTable
import org.scalatest.funsuite.AnyFunSuite
import testUtils.TestUtils

import java.io.{BufferedWriter, FileWriter, IOException}

class NonLinearConverterTest extends AnyFunSuite{

  test("ascii non-linear") {
    val utils = new TestUtils
    val img = utils.loadImg("src/test/assets/parrot.jpg")
    val converter: AsciiConverter = new NonLinearConverter(
      AsciiTable(Array('$', '@', 'B', '%', '8', '&', 'W', 'M', '#', '*', 'o', 'a', 'h', 'k', 'b', 'd', 'p', 'q', 'w', 'm', 'Z', 'O', '0', 'Q', 'L', 'C', 'J', 'U', 'Y', 'X', 'z', 'c', 'v', 'u', 'n', 'x', 'r', 'j', 'f', 't', '/', '\\', '|', '(', ')', '1', '{', '}', '[', ']',
        '?', '-', '_', '+', '~', '<', '>', 'i', '!', 'l', 'I', ';', ':', ',', '"', '^', '`', ' ', ' '))
    )
    val result = converter.convert(img)
    try {
      val writer = new BufferedWriter(new FileWriter("src/test/assets/test_ascii"))
      for (y <- 0 until result.frame.height) {
        for (x <- 0 until result.frame.width) {
          writer.write(result.chars(x)(y))
        }
        writer.newLine() // Move to the next line after writing each row
      }
      writer.close()
    } catch {
      case e: IOException => e.printStackTrace()
    }
  }
}
