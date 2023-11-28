package AsciiConertionTest

import asciiConvertion.{AsciiConverter, AsciiTableProvider, LinearConverter}
import core.AsciiTable
import org.scalatest.funsuite.AnyFunSuite
import testUtils.TestUtils

import java.io.{BufferedWriter, FileWriter, IOException}

class LinearConverterTest extends AnyFunSuite{

  test("ascii linear"){
    val img = TestUtils.loadImg("src/test/assets/parrot.jpg")
    val converter: AsciiConverter =
      new LinearConverter(
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
