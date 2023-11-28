package commands

import core.AsciiTable

class PickAsciiTableCmd(selectedTable: AsciiTable) extends Command [AsciiTable]{
  override val name: String = "table"
  override val arg: Option[AsciiTable] = Option(selectedTable)

  override def checkArgs(): Boolean = true
}
