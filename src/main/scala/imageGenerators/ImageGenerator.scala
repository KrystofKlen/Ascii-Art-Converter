package imageGenerators

import core.Image

/**
 * Creates a random image, derived classes use different tactics for random image generation.
 */
trait ImageGenerator {
  def generateImage(): Image
}
