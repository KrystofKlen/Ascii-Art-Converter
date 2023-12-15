package commands

import core.Image
import filters.Brightness

class BrightnessCmd(brightness: Int) extends Command[Int] {
  override val name: String = "brightness"
  override val arg: Option[Int] = Option(brightness)

  override def checkArgs(): Boolean = {
    true
  }
}
