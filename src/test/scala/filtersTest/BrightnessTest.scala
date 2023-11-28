package filtersTest

import core.Image
import filters.Brightness
import org.scalatest.funsuite.AnyFunSuite
import java.io.File
import javax.imageio.ImageIO
import testUtils.TestUtils

class BrightnessTest extends AnyFunSuite {

  test("Brightness -50 test.") {
    testFilter(-50,TestUtils.PATH_TEST_RESULTS + TestUtils.BRIGHTNESS_MINUS_50_RESULT)
  }

  test("Brightness +50 test"){
    testFilter(50,TestUtils.PATH_TEST_RESULTS + TestUtils.BRIGHTNESS_PLUS_50_RESULT)
  }

  private def testFilter(deg: Int, dst: String): Unit = {
    val originalImage = TestUtils.loadImg(TestUtils.TEST_IMG_SRC)

    // Apply Greyscale Saturation
    val filter = new Brightness(deg)
    val filtered = filter.apply(originalImage);
    TestUtils.saveToFile(filtered, dst)
  }
}
