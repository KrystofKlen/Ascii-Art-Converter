package filtersTest

import core.Image
import filters.Rotate
import org.scalatest.funsuite.AnyFunSuite
import testUtils.TestUtils

import java.io.File
import javax.imageio.ImageIO

class RotateTest extends AnyFunSuite{

  ignore("Rotation test.") {
    // Load the image using ImageIO
    val originalImage = new Image(Image.bufferedImageToPixels(
      ImageIO.read(new java.io.File(TestUtils.TEST_IMG_SRC))))

    // Apply Greyscale Saturation
    val filter = new Rotate(90)
    val filtered1 = filter.apply(originalImage);
    TestUtils.saveToFile(filtered1,TestUtils.TEST_IMG_SRC + TestUtils.ROTATE_90_RESULT)
  }

}
