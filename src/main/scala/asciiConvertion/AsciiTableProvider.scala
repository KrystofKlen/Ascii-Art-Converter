package asciiConvertion

import asciiConvertion.AsciiTableProvider.{DEFAULT_TABLE, DENSE_TABLE, SPARCE_TABLE, customTable}
import core.AsciiTable

case object AsciiTableProvider{
  val DEFAULT_TABLE = AsciiTable(
    Array('$', '@', 'B', '%', '8', '&', 'W', 'M', '#', '*', 'o', 'a', 'h', 'k', 'b', 'd', 'p', 'q', 'w', 'm', 'Z', 'O', '0', 'Q', 'L', 'C', 'J', 'U', 'Y', 'X', 'z', 'c', 'v', 'u', 'n', 'x', 'r', 'j', 'f', 't', '/', '\\', '|', '(', ')', '1', '{', '}', '[', ']',
      '?', '-', '_', '+', '~', '<', '>', 'i', '!', 'l', 'I', ';', ':', ',', '"', '^', '`', ' ', ' ')
  )

  val DENSE_TABLE = AsciiTable(
    Array('M', 'W', 'B', '8', '%', '*', '#', '+', '=', '-', ':', '.')
  )

  val SPARCE_TABLE = AsciiTable(Array('@', 'O', 'C', 'X', 'y', 'l', 'i', '!', ';', ',', '^', '`'))

  val NON_LINEAR_TABLE = AsciiTable(Array('#','#','#','#','X','X','(','(',':',':',':',':',':',':',':',':',':',':',':','.','.','.',' ', ' ', ' '))
  def customTable(chars : Array[Char]) = AsciiTable(chars)

  def getTable(name: String): AsciiTable = {
    name match {
      case "DENSE_TABLE" => DENSE_TABLE
      case "SPARCE_TABLE" => SPARCE_TABLE
      case "NON_LINEAR_TABLE" => NON_LINEAR_TABLE
      case _ =>  DEFAULT_TABLE
    }
  }

}
