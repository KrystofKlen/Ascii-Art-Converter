package filters
import core.Image

import java.awt.image.BufferedImage

class Brightness(val brightness: Int) extends Filter {

  override def apply(originalImage: Image): Image = {
    val brightenedImage = new BufferedImage(
      originalImage.frame.width, originalImage.frame.height, originalImage.bufferedImage.getType
    )
    val brightnessAdjusted = adjustBrightness(brightness)
    for (y <- 0 until originalImage.frame.height) {
      for (x <- 0 until originalImage.frame.width) {
        val rgb: Int = originalImage.bufferedImage.getRGB(x, y)

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

        brightenedImage.setRGB(x, y, adjustedRGB)
      }
    }

    Image(brightenedImage)

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
