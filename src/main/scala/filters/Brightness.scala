package filters
import core.{Image, Pixel}

import java.awt.Color
import java.awt.image.BufferedImage

/**
 *
 * @param brightness range = (-100, +100) - for less bright, + for more bright, 0 for no change
 */
class Brightness(val brightness: Int) extends Filter {

  override def apply(originalImage: Image): Image = {

    val pixels: Array[Array[Pixel]] = Array.ofDim[Pixel](originalImage.getWidth, originalImage.getHeight)

    val brightnessAdjusted = adjustBrightness(brightness)
    for (y <- 0 until originalImage.getHeight) {
      for (x <- 0 until originalImage.getWidth) {
        val rgb: Int = originalImage.getRGB(x, y)

        val alpha: Int = (rgb >> 24) & 0xFF
        var red: Int = (rgb >> 16) & 0xFF
        var green: Int = (rgb >> 8) & 0xFF
        var blue: Int = rgb & 0xFF

        // Adjust brightness for each color component
        red = clamp((red + (red * brightnessAdjusted / 100.0)).toInt, 0, 255)
        green = clamp((green + (green * brightnessAdjusted / 100.0)).toInt, 0, 255)
        blue = clamp((blue + (blue * brightnessAdjusted/ 100.0)).toInt, 0, 255)

        // Clamp values to be in the valid range (0-255)
        red = Math.min(Math.max(red, 0), 255)
        green = Math.min(Math.max(green, 0), 255)
        blue = Math.min(Math.max(blue, 0), 255)

        // Recombine color components
        val adjustedRGB: Int = (alpha << 24) | (red << 16) | (green << 8) | blue

        pixels(x)(y) = new Pixel(adjustedRGB)
      }
    }

    new Image(pixels)

  }

  private def clamp(value: Int, min: Int, max: Int): Int = {
    if (value < min) min
    else if (value > max) max
    else value
  }

  private def adjustBrightness(brightness:Int): Int = {
    if(brightness>0){
      return brightness*2
    }
    brightness
  }
}
