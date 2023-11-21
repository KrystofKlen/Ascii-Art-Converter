import commands.{BrightnessCmd, Executor, FlipCmd, InvertCmd, LinearExecutor, LoadFromFile, OutputConsoleCmd, OutputFileCmd}
import filters.FLIP_AXIS
import parser.{ConsoleParser, Parser}

object Main extends App {
    val parser: Parser = new ConsoleParser(args.toList)
    val commands = parser.parse()
    val executor: Executor = new LinearExecutor(commands)
    executor.executeCommands()
}
