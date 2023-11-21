package filters
import core.{Image, Pixel}

import java.awt.Color
import java.awt.image.BufferedImage

class Greyscale extends Filter {
  override def apply(originalImage: Image): Image = {
    val pixels: Array[Array[Pixel]] = originalImage.getPixels
    val width: Int = pixels.length
    val height: Int = if (width > 0) pixels(0).length else 0

    // Create a 2D array to store grayscale pixels
    val greyscalePixels: Array[Array[Pixel]] = Array.ofDim[Pixel](width, height)

    for (y <- 0 until height) {
      for (x <- 0 until width) {
        val originalPixel = pixels(x)(y)

        // Calculate the grayscale value
        val grayscaleValue = (originalPixel.red + originalPixel.green + originalPixel.blue) / 3

        // Create a new Pixel with the grayscale value
        val grayscalePixel = Pixel(grayscaleValue, grayscaleValue, grayscaleValue)

        // Set the grayscale pixel to the new array
        greyscalePixels(x)(y) = grayscalePixel
      }
    }

    new Image(greyscalePixels)
  }
}
