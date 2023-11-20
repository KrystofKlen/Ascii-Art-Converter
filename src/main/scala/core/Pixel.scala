package core

import java.awt.Color

case class Pixel ( red: Int, green: Int, blue: Int){
  def getRGB: Int = new Color(red, green, blue).getRGB
}
