package output

import core.AsciiImage

class ConsoleAsciiOutputWriter extends OutputWriter [AsciiImage]{
  override def output(data: AsciiImage): Boolean = {
    for (col <- 0 until data.frame.width) {
      for (row <- 0 until data.frame.height) {
        print(data.chars(row)(col))
      }
      println() // Move to the next line after each column
    }
    true
  }
}
