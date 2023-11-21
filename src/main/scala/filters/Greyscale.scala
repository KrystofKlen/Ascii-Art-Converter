package filters
import core.Image

import java.awt.Color
import java.awt.image.BufferedImage

class Greyscale extends Filter {
  override def apply(originalImage: Image): Image = {
    val width: Int = originalImage.getWidth
    val height: Int = originalImage.getHeight
    val greyscaleImage = new BufferedImage(width, height, BufferedImage.TYPE_BYTE_GRAY)

    for (y <- 0 until height) {
      for (x <- 0 until width) {
        val rgb = originalImage.getRGB(x, y)

        // Extract the red, green, and blue components
        val red = (rgb >> 16) & 0xFF
        val green = (rgb >> 8) & 0xFF
        val blue = rgb & 0xFF

        // Calculate the grayscale value
        val grayscaleValue = (red + green + blue) / 3

        // Create a new Color object with the grayscale value
        val grayscaleColor = new Color(grayscaleValue, grayscaleValue, grayscaleValue)

        // Set the grayscale color to the new image
        greyscaleImage.setRGB(x, y, grayscaleColor.getRGB)
      }
    }

    new Image(Image.bufferedImageToPixels(greyscaleImage))
  }
}
