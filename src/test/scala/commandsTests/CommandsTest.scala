package commandsTests

import commands.{BrightnessCmd, LoadFromFileCmd, OutputFileCmd}
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
      new LoadFromFileCmd("src/file.png"),
      new LoadFromFileCmd("src/A/B/C/file.jpg"),
      new LoadFromFileCmd("../../A/B/file.png"),
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
      new LoadFromFileCmd("ey hey"),
      new OutputFileCmd("/.."),
      new OutputFileCmd("a .. b"),
    )
    cmdList.foreach(cmd => {
      assert(!cmd.checkArgs())
    })

  }

}
