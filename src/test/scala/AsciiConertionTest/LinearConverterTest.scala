package AsciiConertionTest

import asciiConvertion.{AsciiConverter, LinearConverter}
import core.AsciiTable
import org.scalatest.funsuite.AnyFunSuite
import testUtils.TestUtils

import java.io.{BufferedWriter, FileWriter, IOException}

class LinearConverterTest extends AnyFunSuite{

  test("ascii linear"){
    val utils = new TestUtils
    val img = utils.loadImg("src/test/assets/parrot.jpg")
    val converter: AsciiConverter = new LinearConverter(
      AsciiTable(Array('#', '@', 'M', '$', '8', 'O', 'o', ':', '_', ' ', ' '))
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
