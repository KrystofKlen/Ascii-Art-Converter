import core.{Image, Pixel}
import org.scalatest.funsuite.AnyFunSuite
import testUtils.TestUtils

import java.awt.Color
import javax.imageio.ImageIO
import java.io.File
import java.awt.image.BufferedImage

class ImageTest extends AnyFunSuite {

  test("Image should be created with the correct dimensions and pixels") {
    val pixels = Array.ofDim[Pixel](3, 3)
    val image = new Image(pixels)
    assert(image.getWidth == 3)
    assert(image.getHeight == 3)
  }



  test("getBufferedImage should return a BufferedImage with the same dimensions and colors") {
    val pixels = Array(
      Array(Pixel(255, 0, 0), Pixel(0, 255, 0), Pixel(0, 0, 255)),
      Array(Pixel(255, 255, 0), Pixel(255, 0, 255), Pixel(0, 255, 255)),
      Array(Pixel(128, 128, 128), Pixel(0, 0, 0), Pixel(255, 255, 255))
    )

    val image = new Image(pixels)
    val bufferedImage = image.getBufferedImage

    assert(bufferedImage.getWidth == image.getWidth)
    assert(bufferedImage.getHeight == image.getHeight)

    for (x <- 0 until image.getWidth) {
      for (y <- 0 until image.getHeight) {
        assert(bufferedImage.getRGB(x, y) == pixels(x)(y).getRGB)
      }
    }
  }

  test("getRGB should return the correct RGB value for valid coordinates") {
    val pixels = Array(
      Array(Pixel(255, 0, 0), Pixel(0, 255, 0)),
      Array(Pixel(0, 0, 255), Pixel(255, 255, 255))
    )

    val image = new Image(pixels)

    assert(image.getRGB(0, 0) == pixels(0)(0).getRGB)
    assert(image.getRGB(1, 1) == pixels(1)(1).getRGB)
  }

  test("getRGB should return default color for out-of-bounds coordinates") {
    val pixels = Array(Array(Pixel(255, 255, 255)))

    val image = new Image(pixels)

    assert(image.getRGB(1, 1) == Color.BLACK.getRGB)
  }

  test("bufferedImageToPixels should convert BufferedImage to pixels correctly") {
    val testImagePath = TestUtils.TEST_IMG_SRC
    val testImageFile = new File(testImagePath)
    val bufferedImage: BufferedImage = ImageIO.read(testImageFile)

    val pixels = Image.bufferedImageToPixels(bufferedImage)
    val reconstructedImage = new Image(pixels)

    assert(reconstructedImage.getWidth == bufferedImage.getWidth)
    assert(reconstructedImage.getHeight == bufferedImage.getHeight)
    val img1 = new Image(pixels)
    assert(img1.equals(reconstructedImage) && reconstructedImage.equals(img1))
  }

  test("equals should return true for equal images") {
    val pixels = Array(
      Array(Pixel(255, 0, 0), Pixel(0, 255, 0)),
      Array(Pixel(0, 0, 255), Pixel(255, 255, 255))
    )

    val image1 = new Image(pixels)
    val image2 = new Image(pixels)

    assert(image1.equals(image2))
  }

  test("equals should return false for images with different dimensions") {
    val pixels1 = Array(Array(Pixel(255, 0, 0), Pixel(0, 255, 0)))
    val pixels2 = Array(
      Array(Pixel(0, 0, 255), Pixel(255, 255, 255)),
      Array(Pixel(128, 128, 128), Pixel(0, 0, 0))
    )

    val image1 = new Image(pixels1)
    val image2 = new Image(pixels2)

    assert(!image1.equals(image2))
  }

  test("equals should return false for images with different pixels") {
    val pixels1 = Array(Array(Pixel(255, 0, 0), Pixel(0, 255, 0)))
    val pixels2 = Array(Array(Pixel(0, 0, 255), Pixel(255, 255, 255)))

    val image1 = new Image(pixels1)
    val image2 = new Image(pixels2)

    assert(!image1.equals(image2))
  }

  test("equals should return false for images with different pixel values") {
    val pixels1 = Array(Array(Pixel(255, 0, 0), Pixel(0, 255, 0)))
    val pixels2 = Array(Array(Pixel(255, 0, 0), Pixel(0, 0, 255)))

    val image1 = new Image(pixels1)
    val image2 = new Image(pixels2)

    assert(!image1.equals(image2))
  }
}
