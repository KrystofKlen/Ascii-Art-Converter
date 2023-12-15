package testUtils

import core.Image

import java.io.File
import javax.imageio.ImageIO

object TestUtils {
  val PATH_TEST_RESULTS = "src/test/results/"
  val ABS_PATH = "/home/krystof/Documents/CVUT/OOP/AsciiArt/"
  val BRIGHTNESS_MINUS_50_RESULT = "brightness_minus50.jpg"
  val BRIGHTNESS_PLUS_50_RESULT = "brightness_plus50.jpg"
  val GREYSCALE_RESULT = "greyscale.jpg"
  val FLIP_X_RESULT = "flip_x.jpg"
  val FLIP_Y_RESULT = "flip_y.jpg"
  val INVERT_RESULT = "invert.jpg"
  val ROTATE_90_RESULT = "rotate_90.jpg"
  val RANDOM_RESULT = "random.jpg"
  val OUTPUT_FILE_RESULT = "output_file.jpg"
  val OUTPUT_FILE_ASCII_RESULT = "output_file_ascii.txt"
  val ABS_OUTPUT_FILE_ASCII_RESULT = "abs_output_file_ascii.txt"
  val TEST_IMG_SRC = "src/test/assets/parrot.jpg"
  val LINEAR_EXECUTOR_RESULT = "src/test/results/lin_exec_result.txt"
  val TEST_ASCII_RESULT = "test_ascii_result.txt"

  def saveToFile(filtered: Image, path: String): Unit = {
    val writer = ImageIO.getImageWritersByFormatName("jpg").next()
    val output = new File(path)
    writer.setOutput(ImageIO.createImageOutputStream(output))
    writer.write(filtered.getBufferedImage)
  }

  def loadImg(src: String): Image = {
    val imagePath = src
    // Load the image using ImageIO
    new Image(Image.bufferedImageToPixels(
      ImageIO.read(new java.io.File(imagePath))))
  }

}
