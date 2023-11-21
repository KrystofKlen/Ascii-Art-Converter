package generatorsTest

import core.Image
import imageGenerators.RandomImageGenerator
import org.scalatest.funsuite.AnyFunSuite
import testUtils.TestUtils

class RandomGeneratorTest extends AnyFunSuite{

  test("Generating random image"){
    val generator = new RandomImageGenerator
    val image: Image = generator.generateImage()
    ignore("saving image"){
      val utils = new TestUtils
      utils.saveToFile(image)
    }
  }

}
