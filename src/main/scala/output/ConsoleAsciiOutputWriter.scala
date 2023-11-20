package output

import core.AsciiImage

class ConsoleAsciiOutputWriter extends OutputWriter [AsciiImage]{
  override def output(data: AsciiImage): Boolean = {
    for (col <- 0 until data.getWidth) {
      for (row <- 0 until data.getHeight) {
        print(data.getCharAt(row,col))
      }
      println() // Move to the next line after each column
    }
    true
  }
}
