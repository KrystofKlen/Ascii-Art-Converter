package loader

import core.Image

import java.io.{File, IOException}
import javax.imageio.ImageIO

class ImageFileLoader(fileLocation: String) extends ImageLoader {
  override def load(): Option[Image] = {
    try {
      val imageBuf = ImageIO.read(new File(fileLocation))
      // Return true if the image is successfully loaded
      Option(new Image(imageBuf))
    } catch {
      case e: IOException =>
        Option(null)
      case _: Exception =>
        // Handle other exceptions
        Option(null)
    }
  }
}
