package executors

/**
 * Executes commands. Derived classes use different tactics for execution of
 * given commands.
 */
trait Executor {
  def executeCommands(): Unit
}
