package filtersTest

import filters.Greyscale
import org.scalatest.funsuite.AnyFunSuite
import testUtils.TestUtils

class GreyscaleTest extends AnyFunSuite{
  val utils = new TestUtils

  test("Greyscale Saturation test.") {
    val originalImage = utils.loadImg("src/test/assets/sunflower.jpg")
    // Apply Greyscale Saturation
    val filter = new Greyscale()
    val filtered = filter.apply(originalImage)

    // Assert that all pixels in the saturated image are greyscale
    for (y <- 0 until filtered.getHeight) {
      for (x <- 0 until filtered.getWidth) {
        val pixel = filtered.getRGB(x, y)

        // Extract red, green, and blue components
        val red = (pixel >> 16) & 0xFF
        val green = (pixel >> 8) & 0xFF
        val blue = pixel & 0xFF

        // Assert that red, green, and blue are the same, indicating greyscale
        assert(red == green && red == blue)
      }
    }
    utils.saveToFile(filtered)
  }

}
