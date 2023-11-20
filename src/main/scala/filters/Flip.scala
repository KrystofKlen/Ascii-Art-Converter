package filters
import core.Image
import filters.FLIP_AXIS._

import java.awt.geom.AffineTransform
import java.awt.image.{AffineTransformOp, BufferedImage}

class Flip(axis: FLIP_AXIS) extends Filter {
  override def apply(originalImage: Image): Image = {
    val width = originalImage.frame.width
    val height = originalImage.frame.height

    val tx = new AffineTransform()
    if (axis == Y) {
      tx.scale(-1, 1)
      tx.translate(-width, 0) // Reposition after scaling
    } else {
      tx.scale(1, -1)
      tx.translate(0, -height) // Reposition after scaling
    }

    val op = new AffineTransformOp(tx, AffineTransformOp.TYPE_NEAREST_NEIGHBOR)
    val flipped = new BufferedImage(width, height, originalImage.bufferedImage.getType)
    op.filter(originalImage.bufferedImage, flipped)
    new Image(flipped)
  }
}
