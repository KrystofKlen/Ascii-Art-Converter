package filtersTest

import filters.Invert
import org.scalatest.funsuite.AnyFunSuite
import testUtils.TestUtils

class InvertTest extends AnyFunSuite{

  test("Image is inverted"){
    val originalImage = TestUtils.loadImg(TestUtils.TEST_IMG_SRC)
    // Apply Invert filter
    val invertFilter = new Invert()
    val invertedImage = invertFilter.apply(originalImage)
    val invertedImage2 = invertFilter.apply(invertedImage)
    if(! originalImage.equals(invertedImage2))
        fail()
  }

  test("Invert test") {
    val originalImage = TestUtils.loadImg(TestUtils.TEST_IMG_SRC)
    // Apply Invert filter
    val invertFilter = new Invert()
    val invertedImage = invertFilter.apply(originalImage)
    TestUtils.saveToFile(invertedImage,TestUtils.PATH_TEST_RESULTS + TestUtils.INVERT_RESULT)
  }
}
