package loaderTests

import loader.ImageFileLoader
import org.scalatest.funsuite.AnyFunSuite
import testUtils.TestUtils

import scala.Console.in

class ImageFileLoaderTest  extends AnyFunSuite {

  test("Loading Image from file"){
    val loader = new ImageFileLoader(TestUtils.TEST_IMG_SRC)
    val result = loader.load()
    assert(result.isDefined)
  }

  test("Loading Image from file - absolute path") {
    val loader = new ImageFileLoader(TestUtils.ABS_PATH + TestUtils.TEST_IMG_SRC)
    val result = loader.load()
    assert(result.isDefined)
  }

  test("Loading Non-Existing Image from file") {
    val loader = new ImageFileLoader("nothing")
    val result = loader.load()
    assert(result.isEmpty)
  }


}
