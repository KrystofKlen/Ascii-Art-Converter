package core

import java.awt.image.BufferedImage

case class Image(val bufferedImage: BufferedImage){
  val frame: Frame = Frame(bufferedImage.getWidth, bufferedImage.getHeight)

  override def equals(obj: Any): Boolean = {
    obj match {
      case img: Image =>
        this.frame == img.frame && this.samePixels(img)
      case _ => false
    }
  }

  def samePixels(image: Image):Boolean = {
    val width = image.frame.width
    val height = image.frame.height
    for (x <- 0 until width) {
      for (y <- 0 until height) {
        if (this.bufferedImage.getRGB(x, y)
          != image.bufferedImage.getRGB(x, y)) return false
      }
    }
    true
  }
}
