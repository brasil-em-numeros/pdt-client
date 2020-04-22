package pdt.client

import java.time.format.DateTimeFormatter

import io.circe.generic.auto._
import pdt.client.decoders._
import pdt.domain._
import pdt.http.HttpClient.{Response, get}
import pdt.http.implicits.HttpRequestOps

object GastosCartaoPagamento {

  def by(request: GastoRequest): Response[List[Gasto]] = {
    implicit val yearMonthFormatter = DateTimeFormatter.ofPattern("MM/yyyy")
    get[Gasto]("cartoes", request.parameters)
  }
}
