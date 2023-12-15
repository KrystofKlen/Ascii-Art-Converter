package commands

import core.Image
import filters.Brightness

/**
 *
 * @param brightness accepted only integers dividable by 90
 */
class BrightnessCmd(brightness: Int) extends Command[Int] {
  override val name: String = "brightness"
  override val arg: Option[Int] = Option(brightness)

  override def checkArgs(): Boolean = {
    if(arg.isEmpty) false
    arg.get % 90 == 0
  }
}
