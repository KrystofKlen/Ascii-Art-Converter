package filters

import core.Image

import java.awt.image.BufferedImage

class Invert extends Filter {
  override def apply(originalImage: Image): Image = {
    val width = originalImage.frame.width
    val height = originalImage.frame.height

    val invertedImage = new BufferedImage(width, height, originalImage.bufferedImage.getType)

    for (y <- 0 until height) {
      for (x <- 0 until width) {
        val rgb: Int = originalImage.bufferedImage.getRGB(x, y)

        // Extract individual color components
        val alpha: Int = (rgb >> 24) & 0xFF
        var red: Int = (rgb >> 16) & 0xFF
        var green: Int = (rgb >> 8) & 0xFF
        var blue: Int = rgb & 0xFF

        // Invert greyscale value
        red = 255 - red
        green = 255 - green
        blue = 255 - blue

        // Recombine color components
        val invertedRGB: Int = (alpha << 24) | (red << 16) | (green << 8) | blue

        // Set the pixel in the new image
        invertedImage.setRGB(x, y, invertedRGB)
      }
    }

    Image(invertedImage)
  }
}

