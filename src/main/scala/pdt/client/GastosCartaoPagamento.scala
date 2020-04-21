package pdt.client

import java.time.format.DateTimeFormatter

import io.circe.generic.auto._
import pdt.client.decoders._
import pdt.http.HttpClient.{HttpClient, get}
import pdt.domain._
import pdt.http.implicits.HttpRequestOps
import zio.RIO

object GastosCartaoPagamento {

  def by(request: GastoRequest): RIO[HttpClient, List[Gasto]] = {
    implicit val yearMonthFormatter = DateTimeFormatter.ofPattern("MM/yyyy")
    get[Gasto]("cartoes", request.parameters)
  }
}
