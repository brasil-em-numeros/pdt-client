package pdt

import shapeless.ops.product.ToMap
import shapeless.syntax.std.product._

package object implicits {

  implicit class HttpRequestOps[A <: Product](val a: A) {
    def parameters(implicit toMap: ToMap.Aux[A, Symbol, Any]): Map[String, String] =
      a.toMap[Symbol, Any]
        .filter {
          case (_, v: Option[Any]) => v.isDefined
          case (_, v) => v != null
        }
        .map {
          case (k, v: Option[Any]) => k.name -> v.get.toString
          case (k, v) => k.name -> v.toString
        }
  }
}
