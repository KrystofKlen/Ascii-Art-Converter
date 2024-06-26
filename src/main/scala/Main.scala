import commands.{BrightnessCmd, FlipCmd, InvertCmd, LoadFromFileCmd, OutputConsoleCmd, OutputFileCmd}
import executors.{Executor, LinearExecutor}
import filters.FLIP_AXIS
import parser.{ConsoleParser, Parser}

object Main extends App {
    val parser: Parser = new ConsoleParser(args.toList)
    val commands = parser.parse()
    val executor: Executor = new LinearExecutor(commands)
    executor.executeCommands()
}
