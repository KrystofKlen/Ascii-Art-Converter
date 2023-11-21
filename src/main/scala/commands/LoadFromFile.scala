package commands
import core.Image
import loader.ImageFileLoader

class LoadFromFile(src: String) extends Command[String] {
  override val name: String = "image"
  override val arg: Option[String] = Option(src)
}
