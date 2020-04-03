package pdt.client

import io.circe.generic.auto._
import pdt.client.decoders._
import pdt.client.HttpClient.{HttpClient, get}
import pdt.domain._
import pdt.implicits.HttpRequestOps
import zio.RIO

object CNEPs {

  def by(id: Long): RIO[HttpClient, CNEP] =
    get[CNEP]("cnep", id)

  def by(request: CNEPRequest): RIO[HttpClient, List[CNEP]] =
    get[CNEP]("cnep", request.parameters)
}
