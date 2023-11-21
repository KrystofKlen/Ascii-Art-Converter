package commands

import core.Image

trait Command[T] {
  def executeCommand(image: Image):T
}
