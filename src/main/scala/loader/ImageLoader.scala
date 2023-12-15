package loader

import core.Image

/**
 * Loader for images, should product Image from the source. Derived classes
 * used for loading the source image in different scenarios (File Directory, Network, ...)
 */
trait ImageLoader {
  def load():Option[Image]
}
