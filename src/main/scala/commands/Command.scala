package commands

trait Command[T] {
  def executeCommand():T
}
