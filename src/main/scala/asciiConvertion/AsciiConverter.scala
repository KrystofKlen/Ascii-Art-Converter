package asciiConvertion

import core.{AsciiImage, Image}

trait AsciiConverter {
  def convert(image:Image):AsciiImage
}
