package parser

import commands.Command

trait Parser {
  def parse():List[Command[_]]
}
