package commands

import core.Image
import filters.FLIP_AXIS.FLIP_AXIS

class FlipCmd(axis: FLIP_AXIS) extends Command [FLIP_AXIS]{
  override val name: String = "flip"
  override val arg: Option[FLIP_AXIS] = Option(axis)
}
