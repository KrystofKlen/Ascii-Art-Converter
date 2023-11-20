package asciiConvertion
import core.{AsciiImage, AsciiTable, Image}

class NonLinearConverter(val asciiTable:AsciiTable) extends AsciiConverter {
  override def convert(image: Image): AsciiImage = {
    val width = image.frame.width
    val height = image.frame.height
    val asciiChars = Array.ofDim[Char](width, height)

    for (x <- 0 until width) {
      for (y <- 0 until height) {
        val rgb = image.bufferedImage.getRGB(x, y)
        val charIndex = mapRgbToAsciiIndex(rgb)
        asciiChars(x)(y) = asciiTable.chars(charIndex)
      }
    }

    new AsciiImage(asciiChars)
  }

  private def mapRgbToAsciiIndex(rgb: Int): Int = {
    // Extracting individual color components (assuming RGB)
    val red = (rgb >> 16) & 0xFF
    val green = (rgb >> 8) & 0xFF
    val blue = rgb & 0xFF

    // Convert RGB to grayscale (assuming linear weights)
    val gray = (0.299 * red + 0.587 * green + 0.114 * blue).toInt

    // Apply a non-linear mapping function (piecewise function as an example)
    val nonLinearGray = nonLinearMap(gray)

    // Map the transformed grayscale value to an index in the transformation table
    // Adjust the range based on the transformation table length
    val tableLength = asciiTable.chars.length
    ((nonLinearGray.toDouble / 255) * (tableLength - 1)).toInt
  }

  private def nonLinearMap(value: Int): Int = {
    // Example piecewise non-linear mapping function
    if (value <= 200) {
      value / 3// Map values 0-200 linearly
    } else {
      // Map values above 200 to a non-linear range
      ((value - 200) / 5) + 100
    }
  }
}
