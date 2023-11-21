package commands

import core.Image

trait LoadCommand {
  def execute():Image
}
