package commandsTests

import commands.OutputFileCmd
import org.scalatest.funsuite.AnyFunSuite

class OutputFileCmdTest extends AnyFunSuite {
  test("Valid args for OutputFile cmd") {
    val cmdList = List(
      new OutputFileCmd("src/good"),
      new OutputFileCmd("src/ok.txt"),
      new OutputFileCmd("src/x/y/ok.txt")
    )
    cmdList.foreach(cmd => {
      assert(cmd.checkArgs())
    })
  }

  test("Invalid args for OutputFile cmd") {
    val cmdList = List(
      new OutputFileCmd("/.."),
      new OutputFileCmd("a .. b"),
      new OutputFileCmd("src/x/y/ok.jpg"),
      new OutputFileCmd("src/x/y/ok.png")
    )
    cmdList.foreach(cmd => {
      assert(!cmd.checkArgs())
    })

  }
}
