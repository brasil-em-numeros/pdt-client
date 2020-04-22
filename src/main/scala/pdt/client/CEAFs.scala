package pdt.client

import io.circe.generic.auto._
import pdt.client.decoders.localDateDecoder
import pdt.domain._
import pdt.http.HttpClient.{Response, get}
import pdt.http.implicits.HttpRequestOps

object CEAFs {

  def by(id: Long): Response[CEAF] =
    get[CEAF]("ceaf", id)

  def by(request: CEAFRequest): Response[List[CEAF]] =
    get[CEAF]("ceaf", request.parameters)
}
