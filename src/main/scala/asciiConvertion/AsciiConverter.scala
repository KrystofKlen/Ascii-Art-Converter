package asciiConvertion

import core.{AsciiImage, Image}

/**
 * Each derived class inheriting this trait uses different strategy
 * to convert Image into AsciiImage
 */
trait AsciiConverter {
  def convert(image:Image):AsciiImage
}
