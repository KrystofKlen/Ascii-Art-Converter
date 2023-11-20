package commands

import core.Image
import filters.Brightness

class BrightnessCmd(brightness: Int,image: Image) extends Command[Image] {

  override def executeCommand(): Image = {
    val filter = new Brightness(brightness)
    filter.apply(image)
  }
}
