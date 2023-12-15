package parser

import commands.Command

/**
 * Parses commands. Derived classes parse commands coming from various sources (console, GUI, HTML,...)
 */
trait Parser {
  def parse():List[Command[_]]
}
