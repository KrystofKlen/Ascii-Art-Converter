package core

class AsciiImage(private val chars: Array[Array[Char]]) {
  private val frame = Frame(chars.size, if (chars.size > 0) chars(0).length else 0 )
  def getWidth : Int = frame.width
  def getHeight :  Int = frame.height

  def getCharAt(x: Int, y: Int): Option[Char] = {
    if (isValidPosition(x, y)) {
      Some(chars(x)(y))
    } else {
      None
    }
  }

  private def isValidPosition(x: Int, y: Int): Boolean = {
    x >= 0 && x < frame.width && y >= 0 && y < frame.height
  }

  override def equals(obj: Any): Boolean = {
    obj match {
      case ascii: AsciiImage =>
        this.frame == ascii.frame && charsAreSame(ascii)
      case _ => false
    }
  }

  private def charsAreSame(ascii:AsciiImage): Boolean = {
    val width = ascii.frame.width
    val height = ascii.frame.height
    for (x <- 0 until width) {
      for (y <- 0 until height) {
        if (this.chars(x)(y)
          != ascii.chars(x)(y)) return false
      }
    }
    true
  }
}
