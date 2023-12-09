package commandsTests

import commands.LoadFromFileCmd
import org.scalatest.funsuite.AnyFunSuite

class LoadFromFileCmdTest extends AnyFunSuite{
  test("Valid args for loadFromFile cmd"){
    val cmdList = List(
      new LoadFromFileCmd("src/file.png"),
      new LoadFromFileCmd("src/A/B/C/file.jpg"),
      new LoadFromFileCmd("../../A/B/file.png"),
      new LoadFromFileCmd("/X/Y/Z/file.jpg")
    )
    cmdList.foreach(cmd => {
      assert(cmd.checkArgs())
    })
  }

  test("Invalid args for loadFromFile cmd"){
    val cmdList = List(
      new LoadFromFileCmd("ey hey"),
      new LoadFromFileCmd(""),
      new LoadFromFileCmd(" "),
      new LoadFromFileCmd("/"),
      new LoadFromFileCmd("/A/B/C"),
    )
    cmdList.foreach(cmd => {
      assert(!cmd.checkArgs())
    })
  }
}
