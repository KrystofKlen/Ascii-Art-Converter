package parser

import java.util

class ConsoleParser (cmdArgs : Array[String]) extends Parser {

  override def parse(): List[CommandToken] = {
    cmdArgs.foldLeft(List[CommandToken]()) { (result, arg) =>
      if (arg.startsWith("--")) {
        val optionName = arg.substring(2)
        CommandToken(optionName, List()) :: result
      } else {
        result match {
          case Nil => result // No option found yet, skip this argument
          case head :: tail => head.copy(args = head.args :+ arg) :: tail
        }
      }
    }.reverse
  }
}
