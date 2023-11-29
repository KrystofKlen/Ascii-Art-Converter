import core.AsciiImage
import org.scalatest.funsuite.AnyFunSuite

class AsciiImageTest extends AnyFunSuite {
  test("AsciiImage should be created with the correct dimensions and characters") {
    val chars = Array(
      Array('A', 'B', 'C'),
      Array('D', 'E', 'F'),
      Array('G', 'H', 'I')
    )

    val asciiImage = new AsciiImage(chars)

    assert(asciiImage.getWidth == 3)
    assert(asciiImage.getHeight == 3)
    assert(asciiImage.getCharAt(0, 0) == Some('A'))
    assert(asciiImage.getCharAt(1, 1) == Some('E'))
    assert(asciiImage.getCharAt(2, 2) == Some('I'))
  }

  test("getCharAt should return None for out-of-bounds positions") {
    val chars = Array(Array('A', 'B'), Array('C', 'D'))
    val asciiImage = new AsciiImage(chars)

    assert(asciiImage.getCharAt(2, 1) == None)
    assert(asciiImage.getCharAt(1, 2) == None)
  }

  test("equals should return true for equal AsciiImages") {
    val chars1 = Array(
      Array('A', 'B'),
      Array('C', 'D')
    )
    val chars2 = Array(
      Array('A', 'B'),
      Array('C', 'D')
    )

    val asciiImage1 = new AsciiImage(chars1)
    val asciiImage2 = new AsciiImage(chars2)

    assert(asciiImage1.equals(asciiImage2))
  }

  test("equals should return false for AsciiImages with different dimensions") {
    val chars1 = Array(Array('A', 'B'), Array('C', 'D'))
    val chars2 = Array(Array('A', 'B', 'C'), Array('D', 'E', 'F'))

    val asciiImage1 = new AsciiImage(chars1)
    val asciiImage2 = new AsciiImage(chars2)

    assert(!asciiImage1.equals(asciiImage2))
  }

  test("equals should return false for AsciiImages with different characters") {
    val chars1 = Array(Array('A', 'B'), Array('C', 'D'))
    val chars2 = Array(Array('A', 'B'), Array('X', 'Y'))

    val asciiImage1 = new AsciiImage(chars1)
    val asciiImage2 = new AsciiImage(chars2)

    assert(!asciiImage1.equals(asciiImage2))
  }
}
