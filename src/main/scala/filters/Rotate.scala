package filters
import core.Image

import java.awt.Graphics2D
import java.awt.image.BufferedImage
import java.awt.geom.AffineTransform
import java.awt.image.{AffineTransformOp, BufferedImage}

//TODO
class Rotate(angle: Int) extends Filter {
  override def apply(originalImage: Image): Image = {
    val width = originalImage.getWidth
    val height = originalImage.getHeight
    val newImg = new BufferedImage(width,height,BufferedImage.TYPE_INT_RGB)

    val g2: Graphics2D = newImg.createGraphics()
    g2.rotate(Math.toRadians(angle), width/2, height/2)
    g2.drawImage(originalImage.getBufferedImage,null,0,0)

    new Image(Image.bufferedImageToPixels(newImg))
  }
}
