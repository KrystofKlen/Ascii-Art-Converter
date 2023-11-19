import core.{Frame, Image}
import org.scalatest.funsuite.AnyFunSuite
import saturation.GreyscaleSaturation

import java.io.File
import javax.imageio.ImageIO
class SaturationTest extends AnyFunSuite{

  test("Image dimensions are same."){
    val imagePath = "src/test/assets/sunflower.jpg" // Replace with the actual path to your image

    // Load the image using ImageIO
    val originalImage = Image( ImageIO.read(new java.io.File(imagePath)) )

    // Apply Greyscale Saturation
    val greyscaleSaturation = new GreyscaleSaturation()
    val saturatedImage = greyscaleSaturation.saturate(originalImage)
    assert(originalImage.frame.width == saturatedImage.frame.width && originalImage.frame.height == saturatedImage.frame.height)
  }

  test("Greyscale Saturation test."){
    val imagePath = "src/test/assets/sunflower.jpg" // Replace with the actual path to your image

    // Load the image using ImageIO
    val originalImage = Image(ImageIO.read(new java.io.File(imagePath)))

    // Apply Greyscale Saturation
    val greyscaleSaturation = new GreyscaleSaturation()
    val saturatedImage = greyscaleSaturation.saturate(originalImage)

    // Assert that all pixels in the saturated image are greyscale
    for (y <- 0 until saturatedImage.frame.height) {
      for (x <- 0 until saturatedImage.frame.width) {
        val pixel = saturatedImage.bufferedImage.getRGB(x, y)

        // Extract red, green, and blue components
        val red = (pixel >> 16) & 0xFF
        val green = (pixel >> 8) & 0xFF
        val blue = pixel & 0xFF

        // Assert that red, green, and blue are the same, indicating greyscale
        assert(red == green && red == blue)
      }
    }
//    // Save the greyscale image
//    val writer = ImageIO.getImageWritersByFormatName("jpg").next()
//    val output = new File("src/test/assets/sunflower_grey.jpg")
//    writer.setOutput(ImageIO.createImageOutputStream(output))
//    writer.write(saturatedImage.bufferedImage)
  }
}
