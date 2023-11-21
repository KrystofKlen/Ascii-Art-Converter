package imageGenerators
import core.Image

import java.awt.{Color, Graphics2D}
import java.awt.image.BufferedImage
import java.util.Random

class RandomImageGenerator extends ImageGenerator {

  override def generateImage(): Image = {
    val random = new Random()
    val width = 300 + random.nextInt(500)
    val height = 300 + random.nextInt(500)

    val bufferedImage = new BufferedImage(width, height, BufferedImage.TYPE_INT_RGB)
    val graphics: Graphics2D = bufferedImage.createGraphics()

    for (x <- 0 until width) {
      for (y <- 0 until height) {
        val randomColor = new Color(random.nextInt(256), random.nextInt(256), random.nextInt(256))
        bufferedImage.setRGB(x, y, randomColor.getRGB)
      }
    }

    graphics.dispose()
    new Image(bufferedImage)
  }
}
