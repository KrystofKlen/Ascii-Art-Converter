package commandsTests

import commands.BrightnessCmd
import org.scalatest.funsuite.AnyFunSuite

class BrightnessCmdTest extends AnyFunSuite{

  test("Valid args for brightness cmd"){
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
    )
    cmdList.foreach(cmd => {
      assert(cmd.checkArgs())
    })
  }

  test("Invalid args for brightness cmd"){
    val cmdList = List(
      new BrightnessCmd(7),
      new BrightnessCmd(9),
      new BrightnessCmd(80),
      new BrightnessCmd(-1120),
    )
    cmdList.foreach(cmd => {
      assert(!cmd.checkArgs())
    })
  }

}
