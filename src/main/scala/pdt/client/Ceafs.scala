package pdt.client

import io.circe.generic.auto._
import pdt.client.decoders.localDateDecoder
import pdt.client.HttpClient.{HttpClient, get}
import pdt.domain._
import pdt.implicits.HttpRequestOps
import zio.RIO

object Ceafs {

  def by(id: Long): RIO[HttpClient, Ceaf] =
    get[Ceaf]("ceaf", id)

  def by(request: CeafRequest): RIO[HttpClient, List[Ceaf]] =
    get[Ceaf]("ceaf", request.parameters)
}
