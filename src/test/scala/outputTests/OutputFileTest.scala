package outputTests

import asciiConvertion.{AsciiConverter, AsciiTableProvider, LinearConverter}
import core.{AsciiImage, AsciiTable, Image}
import loader.ImageFileLoader
import org.scalatest.funsuite.AnyFunSuite
import output.{AsciiFileOutputWriter, ConsoleAsciiOutputWriter, ImageFileOutputWriter, OutputWriter}
import testUtils.TestUtils

class OutputFileTest extends AnyFunSuite{

  test("should create a file and write image in the file"){
    val result = TestUtils.loadImg(TestUtils.TEST_IMG_SRC)
    val output:OutputWriter[Image] = new ImageFileOutputWriter(TestUtils.PATH_TEST_RESULTS + TestUtils.OUTPUT_FILE_RESULT,"jpg")
    val success = output.output(result)
    assert(success)
  }

  test("should create file with ascii art"){
    val result = TestUtils.loadImg(TestUtils.TEST_IMG_SRC)
    val output: OutputWriter[AsciiImage] = new AsciiFileOutputWriter(TestUtils.PATH_TEST_RESULTS + TestUtils.OUTPUT_FILE_ASCII_RESULT);
    val converter: AsciiConverter = new LinearConverter(AsciiTableProvider.DEFAULT_TABLE)
    val asciiArt = converter.convert(result)
    val success = output.output(asciiArt)
    assert(success)
  }

  test("should create file with ascii art - absolute path"){
    val result = TestUtils.loadImg(TestUtils.TEST_IMG_SRC)
    val output: OutputWriter[AsciiImage] = new AsciiFileOutputWriter(TestUtils.ABS_PATH +
      TestUtils.PATH_TEST_RESULTS + TestUtils.ABS_OUTPUT_FILE_ASCII_RESULT);
    val converter: AsciiConverter = new LinearConverter(AsciiTableProvider.DEFAULT_TABLE)
    val asciiArt = converter.convert(result)
    val success = output.output(asciiArt)
    assert(success)
  }

}
