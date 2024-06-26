package output

import core.AsciiImage

import java.io.{BufferedWriter, File, FileWriter, IOException}

class AsciiFileOutputWriter(filePath: String) extends OutputWriter [AsciiImage]{
  override def output(data: AsciiImage): Boolean = {
    try {
      val writer = new BufferedWriter(new FileWriter(new File(filePath)))
      for (y <- 0 until data.getHeight) {
        for (x <- 0 until data.getWidth) {
          writer.write(data.getCharAt(x,y).get)
        }
        writer.newLine() // Move to the next line after writing each row
      }
      writer.close()
      true
    } catch {
      // false can be returned if needed -> Exception then has to be handled elsewhere.
      case e: IOException => throw new IOException("Could not create the result file, check filePath.")
    }
  }
}
