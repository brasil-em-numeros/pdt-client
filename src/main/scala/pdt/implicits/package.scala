package pdt

import java.time.{LocalDate, YearMonth}
import java.time.format.DateTimeFormatter

import shapeless.ops.product.ToMap
import shapeless.syntax.std.product._

package object implicits {

  private val yearMonthFormatter = DateTimeFormatter.ofPattern("yyyyMM")
  private val localDateFormatter = DateTimeFormatter.ofPattern("dd/MM/yyyy")

  implicit class HttpRequestOps[A <: Product](val a: A) {
    def parameters(implicit toMap: ToMap.Aux[A, Symbol, Any]): Map[String, String] =
      a.toMap[Symbol, Any]
        .filter {
          case (_, v: Option[Any]) => v.isDefined
          case (_, v) => v != null
        }
        .map {
          case (k, v: Option[LocalDate]) => k.name -> localDateFormatter.format(v.get)
          case (k, v: Option[Any]) => k.name -> v.get.toString
          case (k, v: YearMonth) => k.name -> yearMonthFormatter.format(v)
          case (k, v: LocalDate) => k.name -> localDateFormatter.format(v)
          case (k, v) => k.name -> v.toString
        }
  }
}
