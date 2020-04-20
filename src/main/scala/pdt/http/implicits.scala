package pdt.http

import java.time.format.DateTimeFormatter
import java.time.{LocalDate, YearMonth}

import shapeless.ops.product.ToMap
import shapeless.syntax.std.product._

object implicits {

  private val defaultYearMonthFormatter = DateTimeFormatter.ofPattern("yyyyMM")
  private val localDateFormatter = DateTimeFormatter.ofPattern("dd/MM/yyyy")

  implicit class HttpRequestOps[A <: Product](val a: A)
                                             (implicit yearMonthFormatter: DateTimeFormatter = defaultYearMonthFormatter) {
    def parameters(implicit toMap: ToMap.Aux[A, Symbol, Any]): Map[String, String] =
      a.toMap[Symbol, Any]
        .filter {
          case (_, v: Option[_]) => v.isDefined
          case (_, v) => v != null
        }
        .map {
          case (k, v: Option[_]) => k.name -> format(v, yearMonthFormatter)
          case (k, v: YearMonth) => k.name -> yearMonthFormatter.format(v)
          case (k, v: LocalDate) => k.name -> localDateFormatter.format(v)
          case (k, v) => k.name -> v.toString
        }
  }

  private def format(opt: Option[_], yearMonthFormatter: DateTimeFormatter) = opt.map {
    case v: LocalDate => localDateFormatter.format(v)
    case v: YearMonth => yearMonthFormatter.format(v)
    case v => v.toString
  }.get
}
