package filters

import core.Image

trait Filter {
  def apply(image: Image) : Image
}
