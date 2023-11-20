package loader

import core.Image

trait ImageLoader {
  def load():Option[Image]
}
