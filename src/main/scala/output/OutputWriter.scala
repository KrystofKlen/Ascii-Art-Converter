package output

/**
 * Outputs/Writes the product to dedicated destination. Derived classes output product T
 * to pre-defined place (File Directory, Network, ...)
 * @tparam T data to output
 */
trait OutputWriter[T] {
  def output(data: T): Boolean
}
