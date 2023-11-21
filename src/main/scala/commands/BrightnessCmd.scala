package commands

import core.Image
import filters.Brightness

class BrightnessCmd(brightness: Int) extends Command[Image] {

  override def executeCommand(image:Image): Image = {
    val filter = new Brightness(brightness)
    filter.apply(image)
  }
}
