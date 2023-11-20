package filtersTest

import filters.Invert
import org.scalatest.funsuite.AnyFunSuite

class InvertTest extends AnyFunSuite{
  val utils = new FilterUtils

  test("Image is inverted"){
    val originalImage = utils.loadImg("src/test/assets/sunflower.jpg")
    // Apply Invert filter
    val invertFilter = new Invert()
    val invertedImage = invertFilter.apply(originalImage)
    val invertedImage2 = invertFilter.apply(invertedImage)
    if(! originalImage.equals(invertedImage2))
        fail()
  }
}
