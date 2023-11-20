package testUtils

import core.Image

import java.io.File
import javax.imageio.ImageIO

class TestUtils {
  def loadImg(src: String): Image = {
    val imagePath = src
    // Load the image using ImageIO
    new Image(ImageIO.read(new java.io.File(imagePath)))
  }

  def saveToFile(filtered: Image): Unit = {
    val writer = ImageIO.getImageWritersByFormatName("jpg").next()
    val output = new File("src/test/assets/test_result.jpg")
    writer.setOutput(ImageIO.createImageOutputStream(output))
    writer.write(filtered.bufferedImage)
  }
}
