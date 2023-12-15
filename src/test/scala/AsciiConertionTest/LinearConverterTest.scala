package AsciiConertionTest

import asciiConvertion.{AsciiConverter, AsciiTableProvider, LinearConverter}
import core.AsciiTable
import org.scalatest.funsuite.AnyFunSuite
import testUtils.TestUtils

import java.io.{BufferedWriter, FileWriter, IOException}

class LinearConverterTest extends AnyFunSuite{

  test("ascii linear"){
    val img = TestUtils.loadImg(TestUtils.TEST_IMG_SRC)
    val converter: AsciiConverter =
      new LinearConverter(
      AsciiTableProvider.DEFAULT_TABLE
      )
    val result = converter.convert(img)
    try {
      val writer = new BufferedWriter(new FileWriter(TestUtils.PATH_TEST_RESULTS + TestUtils.TEST_ASCII_RESULT))
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
