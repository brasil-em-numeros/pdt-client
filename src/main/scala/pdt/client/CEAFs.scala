package pdt.client

import io.circe.generic.auto._
import pdt.client.decoders.localDateDecoder
import pdt.http.HttpClient.{HttpClient, get}
import pdt.domain._
import pdt.http.implicits.HttpRequestOps
import zio.RIO

object CEAFs {

  def by(id: Long): RIO[HttpClient, CEAF] =
    get[CEAF]("ceaf", id)

  def by(request: CEAFRequest): RIO[HttpClient, List[CEAF]] =
    get[CEAF]("ceaf", request.parameters)
}
