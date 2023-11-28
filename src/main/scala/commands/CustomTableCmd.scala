package commands

import core.AsciiTable

class CustomTableCmd(table: AsciiTable) extends Command [AsciiTable]{
  override val name: String = "custom-table"
  override val arg: Option[AsciiTable] = Option(table)

  override def checkArgs(): Boolean = true
}
