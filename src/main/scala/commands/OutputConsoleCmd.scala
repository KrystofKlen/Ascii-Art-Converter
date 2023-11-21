package commands

class OutputConsoleCmd extends Command [Any]{
  override val name: String = "output-console"
  override val arg: Option[Any] = Option.empty

  override def checkArgs(): Boolean = true
}
