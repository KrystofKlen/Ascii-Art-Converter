package output

import core.AsciiImage

class ConsoleAsciiOutputWriter extends OutputWriter [AsciiImage]{
  override def output(data: AsciiImage): Boolean = {
    for (row <- 0 until data.getHeight) {
      for (col <- 0 until data.getWidth) {
        print(data.getCharAt(col,row).get)
      }
      println() // Move to the next line after each column
    }
    true
  }
}
