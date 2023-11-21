package commandsTests

import commands.{BrightnessCmd, LoadFromFile, OutputFileCmd}
import org.scalatest.funsuite.AnyFunSuite

class CommandsTest extends AnyFunSuite{

  test("Commands receiving correct Arguments"){
    val cmdList = List(
      new BrightnessCmd(0),
      new BrightnessCmd(90),
      new BrightnessCmd(180),
      new BrightnessCmd(270),
      new BrightnessCmd(360),
      new BrightnessCmd(-90),
      new BrightnessCmd(-180),
      new BrightnessCmd(-270),
      new BrightnessCmd(-360),
      new LoadFromFile("src/file"),
      new LoadFromFile("src/A/B/C/file.jpg"),
      new LoadFromFile("../../A/B/file.txt"),
      new OutputFileCmd("src/good"),
      new OutputFileCmd("src/ok.txt"),
    )
    cmdList.foreach(cmd => {
      assert(cmd.checkArgs())
    })
  }

  test("Commands receiving wrong Arguments"){
    val cmdList = List(
      new BrightnessCmd(40),
      new LoadFromFile("ey hey"),
      new OutputFileCmd("/.."),
      new OutputFileCmd("a .. b"),
    )
    cmdList.foreach(cmd => {
      assert(!cmd.checkArgs())
    })

  }

}
