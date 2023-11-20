package core

class AsciiImage(val chars: Array[Array[Char]]) {
  val frame = Frame(chars.size, if (chars.size > 0) chars(0).length else 0 )

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
