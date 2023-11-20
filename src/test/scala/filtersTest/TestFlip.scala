package filtersTest

import core.Image
import filters.FLIP_AXIS._
import filters.Flip
import org.scalatest.funsuite.AnyFunSuite
import testUtils.TestUtils

import java.awt.image.BufferedImage
import java.io.File
import javax.imageio.ImageIO

class TestFlip extends AnyFunSuite{

  val utils = new TestUtils

  test("Y flip"){
    val originalImage = utils.loadImg("src/test/assets/sunflower.jpg")

    val filter = new Flip(Y)
    val filtered1 = filter.apply(originalImage);

    val width = filtered1.getWidth
    val height = filtered1.getHeight
    for(x <- 0 until width){
      for(y <- 0 until height){
        if(originalImage.getRGB(x,y)
          != filtered1.getRGB(width-x-1,y)) fail("FLIP Y NOT OK")
      }
    }
  }

  test("X flip"){
    val originalImage = utils.loadImg("src/test/assets/sunflower.jpg")

    val filter = new Flip(X)
    val filtered1 = filter.apply(originalImage);

    val width = filtered1.getWidth
    val height = filtered1.getHeight
    for (y <- 0 until height) {
      for (x <- 0 until width) {
        if (originalImage.getRGB(x, y)
          != filtered1.getRGB(x, height - y - 1)) fail("FLIP X NOT OK")
      }
    }
  }

  test("Double flip"){
    val originalImage = utils.loadImg("src/test/assets/sunflower.jpg")
    val filter = new Flip(X)
    val filtered1 = filter.apply(originalImage);
    val filtered2 = filter.apply(filtered1);
    if(!originalImage.equals(filtered2)) fail("Double flipped => image not the same with original")
  }



}
