package pdt.client

import io.circe.generic.auto._
import pdt.client.decoders._
import pdt.domain._
import pdt.http.HttpClient.{Response, get}
import pdt.http.implicits.HttpRequestOps

object CEPIMs {

  def by(id: Long): Response[CEPIM] =
    get[CEPIM]("cepim", id)

  def by(request: CEPIMRequest): Response[List[CEPIM]] =
    get[CEPIM]("cepim", request.parameters)
}
