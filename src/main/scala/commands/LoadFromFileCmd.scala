package commands

class LoadFromFileCmd(src: String) extends Command[String] {
  override val name: String = "image"
  override val arg: Option[String] = Option(src)

  override def checkArgs(): Boolean = {
    if(arg.isEmpty) return false
    // check if img in supported format
    if(! checkFileType()) return false
    val pathRegex = "^(.+)\\/([^\\/]+)$".r
    pathRegex.matches(arg.get)
  }

  private def checkFileType(): Boolean = {
    val supportedFormats = List(".jpg",".png")
    supportedFormats.foreach(format => {
      if(arg.get.endsWith(format)) return true
    })
    false
  }
}
