package executorsTests

import commands.{BrightnessCmd, Command, FlipCmd, LoadFromFileCmd, OutputFileCmd, RandomImgCmd}
import executors.{Executor, LinearExecutor}
import filters.FLIP_AXIS
import loader.ImageFileLoader
import org.scalatest.funsuite.AnyFunSuite
import testUtils.TestUtils

import java.nio.file.{Files, Paths}
import scala.io.Source

class LinearExecutorTest extends AnyFunSuite{

  test("Linear executor can execute commands given in non-logical order"){
    val commands: List[Command[_]] = List(
      new BrightnessCmd(90),
      new FlipCmd(FLIP_AXIS.X),
      new LoadFromFileCmd(TestUtils.TEST_IMG_SRC),
      new OutputFileCmd(TestUtils.LINEAR_EXECUTOR_RESULT),
    )
    val executor: Executor = new LinearExecutor(commands)
    executor.executeCommands()
    assert( doesFileExist(TestUtils.LINEAR_EXECUTOR_RESULT), "Linear executor has not created an expected result file")

    val tmpFileForComparison = TestUtils.LINEAR_EXECUTOR_RESULT + "2"

    val commands2: List[Command[_]] = List(
      new FlipCmd(FLIP_AXIS.X),
      new OutputFileCmd(tmpFileForComparison),
      new BrightnessCmd(90),
      new LoadFromFileCmd(TestUtils.TEST_IMG_SRC),
    )
    val executor2 : Executor = new LinearExecutor(commands2)
    executor2.executeCommands()

    val filesEq = areFileContentsEqual(TestUtils.LINEAR_EXECUTOR_RESULT, tmpFileForComparison)
    //clean up
    removeFile(tmpFileForComparison)

    assert(filesEq,"Linear executor failed when given different order of same commands.")
  }

  private def doesFileExist(filePath: String): Boolean = {
    Files.exists(Paths.get(filePath))
  }


  private def readFile(filePath: String): String = {
    val source = Source.fromFile(filePath)
    try {
      source.mkString
    } finally {
      source.close()
    }
  }


  private def areFileContentsEqual(file1Path: String, file2Path: String): Boolean = {
    val content1 = readFile(file1Path)
    val content2 = readFile(file2Path)

    content1 == content2
  }

  private def removeFile(filePath: String): Boolean = {
    try {
      Files.delete(Paths.get(filePath))
      true
    } catch {
      case e: Exception =>
        // Handle exceptions (e.g., file not found, permission issues)
        println(s"Error deleting file: ${e.getMessage}")
        false
    }
  }

}
