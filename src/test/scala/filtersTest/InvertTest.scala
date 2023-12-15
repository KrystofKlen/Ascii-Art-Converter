package filtersTest

import core.{Image, Pixel}
import filters.Invert
import org.scalatest.funsuite.AnyFunSuite
import testUtils.TestUtils

class InvertTest extends AnyFunSuite{

  test("Twice inverted image is the same"){
    val originalImage = TestUtils.loadImg(TestUtils.TEST_IMG_SRC)
    // Apply Invert filter
    val invertFilter = new Invert()
    val invertedImage = invertFilter.apply(originalImage)
    val invertedImage2 = invertFilter.apply(invertedImage)
    if(! originalImage.equals(invertedImage2))
        fail()
  }

  test("Inverts black Image into white Image"){
    val pixelsBlack = Array(
      Array(Pixel(0, 0, 0), Pixel(0, 0, 0), Pixel(0, 0, 0)),
      Array(Pixel(0, 0, 0), Pixel(0, 0, 0), Pixel(0, 0, 0))
    )
    val imgBlack = new Image(pixelsBlack)
    val invertFilter = new Invert
    val imgWhite = invertFilter.apply(imgBlack)
    imgWhite.getPixels.foreach( arr => {
      arr.foreach(pixel => {
        assert(pixel.blue == 255 && pixel.red == 255 && pixel.green == 255)
      })
    })

  }

  test("Invert test") {
    val originalImage = TestUtils.loadImg(TestUtils.TEST_IMG_SRC)
    // Apply Invert filter
    val invertFilter = new Invert()
    val invertedImage = invertFilter.apply(originalImage)
    TestUtils.saveToFile(invertedImage,TestUtils.PATH_TEST_RESULTS + TestUtils.INVERT_RESULT)
  }
}
