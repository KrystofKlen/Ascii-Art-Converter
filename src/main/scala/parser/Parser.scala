package parser

trait Parser {
  def parse():List[CommandToken]
}
