package pdt.client

import io.circe.generic.auto._
import pdt.client.decoders._
import pdt.client.HttpClient.{HttpClient, get}
import pdt.domain._
import pdt.implicits.HttpRequestOps
import zio.RIO

object CEISs {

  def by(id: Long): RIO[HttpClient, CEIS] =
    get[CEIS]("ceis", id)

  def by(request: CEISRequest): RIO[HttpClient, List[CEIS]] =
    get[CEIS]("ceis", request.parameters)
}
