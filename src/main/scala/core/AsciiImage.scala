package core

/**
 *
 * @param chars ascii image represented as 2d char array
 */
class AsciiImage(private val chars: Array[Array[Char]]) {
  private val width = chars.size
  private val height = if (chars.size > 0) chars(0).length else 0

  def getWidth : Int = width
  def getHeight :  Int = height

  def getCharAt(x: Int, y: Int): Option[Char] = {
    if (isValidPosition(x, y)) {
      Some(chars(x)(y))
    } else {
      None
    }
  }

  private def isValidPosition(x: Int, y: Int): Boolean = {
    x >= 0 && x < width && y >= 0 && y < height
  }

  override def equals(obj: Any): Boolean = {
    obj match {
      case ascii: AsciiImage =>
         width == ascii.width && height == ascii.height && charsAreSame(ascii)
      case _ => false
    }
  }

  /**
   * @param ascii
   * @return true if image in param has the same chars at the same positions.
   */
  private def charsAreSame(ascii:AsciiImage): Boolean = {
    val width = ascii.width
    val height = ascii.height
    for (x <- 0 until width) {
      for (y <- 0 until height) {
        if (this.chars(x)(y)
          != ascii.chars(x)(y)) return false
      }
    }
    true
  }
}
