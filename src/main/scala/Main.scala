import commands.LinearExecutor
import parser.ConsoleParser

object Main extends App {
    val parser = new ConsoleParser(args);
    val res = parser.parse();
    val exec = new LinearExecutor(res)


}
