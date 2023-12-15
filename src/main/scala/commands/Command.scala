package commands

import core.Image

/**
 * The Command trait serves as a standard representation for various types of commands
 * that may originate from different sources such as console or GUI. It provides a
 * unified interface for commands, ensuring consistency in their representation.
 *
 * @tparam A The type of argument that a given command may receive. If a command does not
 *           expect any argument, use the type 'Any'.
 */
trait Command[A] {
  val name: String
  val arg: Option[A]
  def checkArgs(): Boolean
}
