package filtersTest

import core.Image
import filters.Rotate
import org.scalatest.funsuite.AnyFunSuite

import java.io.File
import javax.imageio.ImageIO

class RotateTest extends AnyFunSuite{
  test("Rotation test.") {
    val imagePath = "src/test/assets/sunflower.jpg" // Replace with the actual path to your image

    // Load the image using ImageIO
    val originalImage = new Image(ImageIO.read(new java.io.File(imagePath)))

    // Apply Greyscale Saturation
    val filter = new Rotate(90)
    val filtered1 = filter.apply(originalImage);
    val filtered2 = filter.apply(filtered1)
    val filtered3 = filter.apply(filtered2)
    val filtered4 = filter.apply(filtered3)
    val writer = ImageIO.getImageWritersByFormatName("jpg").next()
    val output = new File("src/test/assets/sunflower_grey.jpg")
    writer.setOutput(ImageIO.createImageOutputStream(output))
    writer.write(filtered4.getBufferedImage)

  }

}
