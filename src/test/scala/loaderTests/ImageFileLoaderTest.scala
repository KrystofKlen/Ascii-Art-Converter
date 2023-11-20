package loaderTests

import loader.ImageFileLoader
import org.scalatest.funsuite.AnyFunSuite

import scala.Console.in

class ImageFileLoaderTest  extends AnyFunSuite {

  test("Loading Image from file"){
    val loader = new ImageFileLoader("src/test/assets/parrot.jpg")
    val result = loader.load()
    assert(result.isDefined)
  }

  test("Loading Non-Existing Image from file") {
    val loader = new ImageFileLoader("nothing")
    val result = loader.load()
    assert(result.isEmpty)
  }


}
