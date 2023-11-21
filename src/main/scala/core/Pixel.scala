package core

import java.awt.Color

case class Pixel ( red: Int, green: Int, blue: Int ){
  def getRGB: Int = new Color(red, green, blue).getRGB

  def this(adjustedRGB: Int) = {
    this(
      (adjustedRGB >> 16) & 0xFF, // Red component
      (adjustedRGB >> 8) & 0xFF, // Green component
      adjustedRGB & 0xFF // Blue component
    )
  }
}
