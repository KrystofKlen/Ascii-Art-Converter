package commands

class LoadFromFile(src: String) extends Command[String] {
  override val name: String = "image"
  override val arg: Option[String] = Option(src)

  override def checkArgs(): Boolean = {
    if(arg.isEmpty) return false
    val pathRegex = "^(.+)\\/([^\\/]+)$".r
    pathRegex.matches(arg.get)
  }
}
