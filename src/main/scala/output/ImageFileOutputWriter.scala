package output

import core.Image

import java.io.{File, IOException}
import javax.imageio.ImageIO

class ImageFileOutputWriter(filePath: String, imageFormat: String) extends OutputWriter[Image] {
  override def output(data: Image): Boolean = {
    try {
      val writer = ImageIO.getImageWritersByFormatName(imageFormat).next()
      val output = new File(filePath)
      writer.setOutput(ImageIO.createImageOutputStream(output))
      writer.write(data.getBufferedImage)
      // Close all streams
      writer.dispose()
      true
    } catch {
      case e: IOException => false
    }
  }
}
