package commands

import core.Image

trait Command[A] {
  val name: String
  val arg: Option[A]
}
