package coreTests

import core.Pixel
import org.scalatest.funsuite.AnyFunSuite

class PixelTest extends AnyFunSuite {
  test("Pixel should be created with the correct RGB components") {
    val pixel = Pixel(255, 0, 128)

    assert(pixel.red == 255)
    assert(pixel.green == 0)
    assert(pixel.blue == 128)
  }

  test("getRGB should return the correct RGB value") {
    val pixel = Pixel(255, 0, 128)
    val expectedRGB = 0xFFFF0080 // RGB for (255, 0, 128)

    assert(pixel.getRGB == expectedRGB)
  }

  test("Pixel should be created with the correct RGB components from adjusted RGB") {
    val adjustedRGB = 0xFFFF0080 // RGB for (255, 0, 128)
    val pixel = new Pixel(adjustedRGB)

    assert(pixel.red == 255)
    assert(pixel.green == 0)
    assert(pixel.blue == 128)
  }

  test("Pixel created from adjusted RGB should have correct getRGB value") {
    val expectedRGB = 0xFFFF0080 // RGB for (255, 0, 128)
    val pixel = new Pixel(expectedRGB)

    assert(pixel.getRGB == expectedRGB)
  }

  test("Pixel created from adjusted RGB with max values should have correct components") {
    val adjustedRGB = 0xFFFFFF // RGB for (255, 255, 255)
    val pixel = new Pixel(adjustedRGB)

    assert(pixel.red == 255)
    assert(pixel.green == 255)
    assert(pixel.blue == 255)
  }

  test("Pixel created from adjusted RGB with min values should have correct components") {
    val adjustedRGB = 0x0 // RGB for (0, 0, 0)
    val pixel = new Pixel(adjustedRGB)

    assert(pixel.red == 0)
    assert(pixel.green == 0)
    assert(pixel.blue == 0)
  }

  test("Pixel created from adjusted RGB with negative values should have correct components") {
    val adjustedRGB = 0xFF0000 // RGB for (255, 0, 0)
    val pixel = new Pixel(adjustedRGB)

    assert(pixel.red == 255)
    assert(pixel.green == 0)
    assert(pixel.blue == 0)
  }
}

