package core

import java.awt.{Color, Graphics2D, Image}
import java.awt.image.BufferedImage

class Image(private val pixels: Array[Array[Pixel]]){
  private val width: Int = pixels.length
  private val height: Int = if (width > 0) pixels(0).length else 0

  def getWidth : Int = width
  def getHeight : Int = height

  def getBufferedImage: BufferedImage= {
    val bufferedImage = new BufferedImage(width, height, BufferedImage.TYPE_INT_RGB)
    val graphics: Graphics2D = bufferedImage.createGraphics()

    for (x <- 0 until width) {
      for (y <- 0 until height) {
        graphics.setColor(new Color(pixels(x)(y).getRGB))
        graphics.fillRect(x, y, 1, 1)
      }
    }

    graphics.dispose()
    bufferedImage
  }

  def getRGB(x: Int, y: Int): Int = {
    if (x >= 0 && x < width && y >= 0 && y < height) {
      pixels(x)(y).getRGB
    } else {
      // Return a default color if coordinates are out of bounds
      Color.BLACK.getRGB
    }
  }

  override def equals(obj: Any): Boolean = {
    obj match {
      case img: Image =>
        this.height == img.height && this.width == img.width && this.samePixels(img)
      case _ => false
    }
  }

  private def samePixels(image: Image):Boolean = {
    if(width != image.width || height != image.height) return false

    for (x <- 0 until width) {
      for (y <- 0 until height) {
        if (this.getRGB(x, y)
          != image.getRGB(x, y)) return false
      }
    }
    true
  }

}

object Image{
  def bufferedImageToPixels(image: BufferedImage): Array[Array[Pixel]] = {
    val width = image.getWidth
    val height = image.getHeight

    // Create a 2D array to store pixels
    val pixels: Array[Array[Pixel]] = Array.ofDim[Pixel](width, height)

    for {
      x <- 0 until width
      y <- 0 until height
    } {
      val rgb = image.getRGB(x, y)
      val red = (rgb >> 16) & 0xFF
      val green = (rgb >> 8) & 0xFF
      val blue = rgb & 0xFF

      pixels(x)(y) = Pixel(red, green, blue)
    }

    pixels
  }
}

