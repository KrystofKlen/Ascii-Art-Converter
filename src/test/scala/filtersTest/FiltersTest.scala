package filtersTest

import core.Image
import filters.Brightness
import org.scalatest.funsuite.AnyFunSuite
import java.io.File
import javax.imageio.ImageIO

class FiltersTest extends AnyFunSuite {

  val utils = new FilterUtils

  test("Brightness test.") {
    val originalImage = utils.loadImg("src/test/assets/sunflower.jpg")

    // Apply Greyscale Saturation
    val filter = new Brightness(-50)
    val filtered = filter.apply(originalImage);
    val writer = ImageIO.getImageWritersByFormatName("jpg").next()
    val output = new File("src/test/assets/sunflower_grey.jpg")
    writer.setOutput(ImageIO.createImageOutputStream(output))
    writer.write(filtered.bufferedImage)
  }
}
