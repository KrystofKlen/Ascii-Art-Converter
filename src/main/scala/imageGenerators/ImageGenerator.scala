package imageGenerators

import core.Image

trait ImageGenerator {
  def generateImage(): Image
}
