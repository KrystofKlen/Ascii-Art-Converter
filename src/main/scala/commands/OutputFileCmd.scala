package commands

class OutputFileCmd(dst: String) extends Command [String]{
  override val name: String = "output-file"
  override val arg: Option[String] = Option(dst)

  override def checkArgs(): Boolean = {
    if (arg.isEmpty) return false
    val pathRegex = "^(.+)\\/([^\\/]+)$".r
    pathRegex.matches(arg.get)
  }
}
