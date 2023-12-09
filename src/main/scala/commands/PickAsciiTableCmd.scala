package commands

import core.AsciiTable

class PickAsciiTableCmd(selectedTable: AsciiTable) extends Command [AsciiTable]{
  override val name: String = "table"
  override val arg: Option[AsciiTable] = Option(selectedTable)

  /**
   * If table name not recognized the ascii-table-provider returns default table
   * @return true
   */
  override def checkArgs(): Boolean = true
}
