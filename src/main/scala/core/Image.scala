package core

import java.awt.Color
import java.awt.image.BufferedImage

class Image(private val bufferedImage: BufferedImage){
  private val frame: Frame = Frame(bufferedImage.getWidth, bufferedImage.getHeight)
  private val pixels: Array[Pixel] = setPixels()

  def getWidth : Int = frame.width
  def getHeight : Int = frame.height
  def getType : Int = bufferedImage.getType
  def getBufferedImage : BufferedImage = {
    // Create a new BufferedImage with the same dimensions and type as the original
    val newBufferedImage = new BufferedImage(
      this.bufferedImage.getWidth,
      this.bufferedImage.getHeight,
      this.bufferedImage.getType
    )

    // Copy the data from the original BufferedImage to the new one
    val graphics = newBufferedImage.getGraphics
    graphics.drawImage(this.bufferedImage, 0, 0, null)
    graphics.dispose()

    newBufferedImage
  }
  def getRGB(x: Int, y: Int): Int = {
    if (x >= 0 && x < frame.width && y >= 0 && y < frame.height) {
      pixels(x + y * frame.width).getRGB
    } else {
      // Return a default color if coordinates are out of bounds
      Color.BLACK.getRGB
    }
  }
  override def equals(obj: Any): Boolean = {
    obj match {
      case img: Image =>
        this.frame == img.frame && this.samePixels(img)
      case _ => false
    }
  }

  private def samePixels(image: Image):Boolean = {
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

  private def setPixels(): Array[Pixel] = {
    val width = bufferedImage.getWidth
    val height = bufferedImage.getHeight
    val pixelsArray = new Array[Pixel](width * height)

    for (x <- 0 until width) {
      for (y <- 0 until height) {
        val color = new Color(bufferedImage.getRGB(x, y))
        val pixel = Pixel(color.getRed, color.getGreen, color.getBlue)
        pixelsArray(x + y * width) = pixel
      }
    }

    pixelsArray
  }
}
