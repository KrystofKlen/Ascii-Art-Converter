package commands

import core.Image

class InvertCmd extends Command[Any] {
  override val name: String = "invert"
  override val arg: Option[Any] = Option.empty

  override def checkArgs(): Boolean = {
    true
  }
}
