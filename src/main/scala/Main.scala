import parser.ConsoleParser

object Main extends App {
    val parser = new ConsoleParser(args);
    val res = parser.parse();
    for(x<-res){
      print(x.option + " -> ")
      for(y<-x.args){
        print(y + " ")
      }
      println()
    }
}
