package generatorsTest

import core.Image
import imageGenerators.RandomImageGenerator
import org.scalatest.funsuite.AnyFunSuite
import testUtils.TestUtils

class RandomGeneratorTest extends AnyFunSuite{

  test("Generating random image"){
    val generator = new RandomImageGenerator
    val image: Image = generator.generateImage()
    assert(image.getHeight>0 && image.getWidth>0)
    TestUtils.saveToFile(image,TestUtils.PATH_TEST_RESULTS + TestUtils.RANDOM_RESULT)
  }

}
