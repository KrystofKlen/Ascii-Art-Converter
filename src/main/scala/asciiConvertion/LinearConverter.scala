package asciiConvertion
import core.{AsciiImage, AsciiTable, Image}

/**
 * The range of 255 greyscale values is equally divied between a set of ASCII characters
 * @param asciiTable = AsciiTable to be used for conversion. Start with dense chars.
 *                     E.g. #,&,*,!,.
 *
 */
class LinearConverter(val asciiTable:AsciiTable) extends AsciiConverter {

  override def convert(image: Image): AsciiImage = {
    val width = image.getWidth
    val height = image.getHeight
    val asciiChars = Array.ofDim[Char](width, height)

    for (x <- 0 until width) {
      for (y <- 0 until height) {
        val rgb = image.getRGB(x, y)
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

    // Map the grayscale value to an index in the ASCII table
    val asciiTableLength = asciiTable.chars.length
    ((gray.toDouble / 255) * (asciiTableLength - 1)).toInt
  }
}
