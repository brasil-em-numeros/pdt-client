package pdt.client

import io.circe.generic.auto._
import pdt.client.decoders._
import pdt.http.HttpClient.{HttpClient, get}
import pdt.domain._
import pdt.http.implicits.HttpRequestOps
import zio.RIO

object CEPIMs {

  def by(id: Long): RIO[HttpClient, CEPIM] =
    get[CEPIM]("cepim", id)

  def by(request: CEPIMRequest): RIO[HttpClient, List[CEPIM]] =
    get[CEPIM]("cepim", request.parameters)
}
