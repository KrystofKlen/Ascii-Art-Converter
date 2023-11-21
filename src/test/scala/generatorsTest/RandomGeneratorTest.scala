package generatorsTest

import core.Image
import imageGenerators.RandomImageGenerator
import org.scalatest.funsuite.AnyFunSuite

class RandomGeneratorTest extends AnyFunSuite{

  test("Generating random image"){
    val generator = new RandomImageGenerator
    val image: Image = generator.generateImage()
    assert(image.getHeight>0 && image.getWidth>0)
  }

}
