package parserTests

import commands.{BrightnessCmd, FlipCmd, InvertCmd, LoadFromFile, OutputConsoleCmd, OutputFileCmd, RandomImgCmd}
import filters.FLIP_AXIS
import org.scalatest.funsuite.AnyFunSuite
import parser.ConsoleParser

import java.util

class ConsoleParserTest extends AnyFunSuite{

    val parser1 = new ConsoleParser(List("--image", "src", "--invert", "--image-random",
      "--brightness", "90", "--flip", "x", "--output-file", "dst", "--output-console"))

    test("Console parser: Parsed all args") {
      val commands1 = parser1.parse()
      assert(commands1.size == 7)
    }

    test("Console parser: Correct Command classes") {
      val commands1 = parser1.parse()
      val expectedClasses = Set(
        classOf[OutputConsoleCmd],
        classOf[OutputFileCmd],
        classOf[FlipCmd],
        classOf[BrightnessCmd],
        classOf[RandomImgCmd],
        classOf[InvertCmd],
        classOf[LoadFromFile])

      val actualClasses = commands1.map(_.getClass)
      assert(actualClasses.toSet.size == expectedClasses.size)
    }

    test("Command parser: Arguments for options read and stored in commands.") {
      val commands1 = parser1.parse()
      assert(commands1.exists {
        case cmd: OutputConsoleCmd => true
        case cmd: OutputFileCmd => cmd.arg.get.equals("dst")
        case cmd: BrightnessCmd => cmd.arg.get == 90
        case cmd: LoadFromFile => cmd.arg.get.equals("src")
        case cmd: FlipCmd => cmd.arg.get == FLIP_AXIS.X
        case cmd: RandomImgCmd => true
        case cmd: InvertCmd => cmd.arg.isEmpty
        case _ => false
      })

    }

    test("Should throw exception - invalid commands"){
      val parserIncorrectInput1 = new ConsoleParser(List("blabla", "blabla"))
      intercept[IllegalArgumentException] {
        parserIncorrectInput1.parse()
      }
    }

    test("Should throw exception - invalid value for brightness"){
      val parserIncorrectInput2 = new ConsoleParser(List("--brightness", "klkl"))
      intercept[NumberFormatException] {
        parserIncorrectInput2.parse()
      }
    }

}
