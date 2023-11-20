package output

trait OutputWriter[T] {
  def output(data: T): Boolean
}
