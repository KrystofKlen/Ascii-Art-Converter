package core

import java.awt.image.BufferedImage

case class Image(val bufferedImage: BufferedImage){
  val frame: Frame = Frame(bufferedImage.getWidth, bufferedImage.getHeight)
}
