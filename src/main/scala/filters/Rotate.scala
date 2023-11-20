package filters
import core.Image

import java.awt.Graphics2D
import java.awt.image.BufferedImage
import java.awt.geom.AffineTransform
import java.awt.image.{AffineTransformOp, BufferedImage}

class Rotate(angle: Int) extends Filter {
  override def apply(originalImage: Image): Image = {
    val width = originalImage.frame.width
    val height = originalImage.frame.height
    val newImg = new BufferedImage(width,height,originalImage.bufferedImage.getType)
    
    val g2: Graphics2D = newImg.createGraphics()
    g2.rotate(Math.toRadians(angle), width/2, height/2)
    g2.drawImage(originalImage.bufferedImage,null,0,0)

    Image(newImg)

  }
}
