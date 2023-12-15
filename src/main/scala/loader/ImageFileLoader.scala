package loader

import core.Image

import java.io.{File, IOException}
import javax.imageio.ImageIO

class ImageFileLoader(fileLocation: String) extends ImageLoader {

  /**
   *
   * @return Image if everything succeeds. As of now when encountering error during file loading
   *         the method throws exception. Feel free to return Optional(empty) if it is more convenient.
   */
  override def load(): Option[Image] = {
    try {
      val imageBuf = ImageIO.read(new File(fileLocation))
      // Return true if the image is successfully loaded
      Option(new Image(Image.bufferedImageToPixels(imageBuf)))
    } catch {
      case io: IOException =>
        throw new IOException("Error when loading file.")
      case illArg: IllegalArgumentException =>
        throw new IllegalArgumentException("File does not exist.")
    }
  }
}
