package parserTests

import asciiConvertion.AsciiTableProvider
import commands.{BrightnessCmd, Command, CustomTableCmd, FlipCmd, InvertCmd, LoadFromFileCmd, OutputConsoleCmd, OutputFileCmd, PickAsciiTableCmd, RandomImgCmd}
import core.AsciiTable
import filters.FLIP_AXIS
import org.scalatest.funsuite.AnyFunSuite
import parser.ConsoleParser

import java.util

class ConsoleParserTest extends AnyFunSuite{

    val parser1 = new ConsoleParser(List("--image", "src/file.jpg", "--invert", "--image-random",
      "--brightness", "90", "--flip", "x", "--output-file", "src/dst.txt", "--output-console"))

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
        classOf[LoadFromFileCmd])

      val actualClasses = commands1.map(_.getClass)
      assert(actualClasses.toSet.size == expectedClasses.size)
    }

    test("Command parser: Arguments for options read and stored in commands.") {
      val commands1 = parser1.parse()
      assert(commands1.exists {
        case cmd: OutputConsoleCmd => true
        case cmd: OutputFileCmd => cmd.arg.get.equals("dst")
        case cmd: BrightnessCmd => cmd.arg.get == 90
        case cmd: LoadFromFileCmd => cmd.arg.get.equals("src")
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

    test("Should throw exception - invalid arg values"){
      var parserIncorrectInput2 = new ConsoleParser(List("--brightness", "klkl"))
      intercept[IllegalArgumentException] {
        parserIncorrectInput2.parse()
      }

      parserIncorrectInput2 = new ConsoleParser(List("--image", "fbsbvh    085 e8 "))
      intercept[IllegalArgumentException] {
        parserIncorrectInput2.parse()
      }

      parserIncorrectInput2 = new ConsoleParser(List("--image"))
      intercept[IllegalArgumentException] {
        parserIncorrectInput2.parse()
      }

      parserIncorrectInput2 = new ConsoleParser(List("--brightness"))
      intercept[IllegalArgumentException] {
        parserIncorrectInput2.parse()
      }

      parserIncorrectInput2 = new ConsoleParser(List("--flip","x y"))
      intercept[IllegalArgumentException] {
        parserIncorrectInput2.parse()
      }

      parserIncorrectInput2 = new ConsoleParser(List("--flip"))
      intercept[IllegalArgumentException] {
        parserIncorrectInput2.parse()
      }

      parserIncorrectInput2 = new ConsoleParser(List("--flip", "xy"))
      intercept[IllegalArgumentException] {
        parserIncorrectInput2.parse()
      }

      parserIncorrectInput2 = new ConsoleParser(List("--output-file"))
      intercept[IllegalArgumentException] {
        parserIncorrectInput2.parse()
      }

      parserIncorrectInput2 = new ConsoleParser(List("--output-file","invalid_file.jpg"))
      intercept[IllegalArgumentException] {
        parserIncorrectInput2.parse()
      }
    }

   private def parseCommand(args: List[String]): Command[_] = {
      val parser = new ConsoleParser(args)
      val commands = parser.parse()

      val result = commands.headOption.getOrElse(fail("No command found in parsed commands"))
      result
    }

    private def assertTableContent(actual: AsciiTable, expectedChars: Array[Char]): Unit = {
      assert(
        actual.chars.sameElements(expectedChars),
        s"Expected: ${expectedChars.mkString("Array(", ", ", ")")}, Actual: ${actual.chars.mkString("Array(", ", ", ")")}"
      )
    }

    test("should return default table for unknown table name") {
      val result = parseCommand(List("--table", "some table"))
      val defaultTable = AsciiTableProvider.DEFAULT_TABLE
      assertTableContent(result.asInstanceOf[PickAsciiTableCmd].arg.getOrElse(fail("Ascii table not found")), defaultTable.chars)
    }

    test("should return DENSE_TABLE table for --table DENSE_TABLE") {
      val result = parseCommand(List("--table", "DENSE_TABLE"))
      val denseTable = AsciiTableProvider.DENSE_TABLE
      assertTableContent(result.asInstanceOf[PickAsciiTableCmd].arg.getOrElse(fail("Ascii table not found")), denseTable.chars)
    }

    test("custom table test") {
      val customTable = "abcdefg"
      val result = parseCommand(List("--custom-table", customTable))
      assertTableContent(result.asInstanceOf[CustomTableCmd].arg.getOrElse(fail("Ascii table not found")), customTable.toCharArray)
    }
  }

