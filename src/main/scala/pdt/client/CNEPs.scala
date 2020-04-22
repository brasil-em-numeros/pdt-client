package pdt.client

import io.circe.generic.auto._
import pdt.client.decoders._
import pdt.domain._
import pdt.http.HttpClient.{Response, get}
import pdt.http.implicits.HttpRequestOps

object CNEPs {

  def by(id: Long): Response[CNEP] =
    get[CNEP]("cnep", id)

  def by(request: CNEPRequest): Response[List[CNEP]] =
    get[CNEP]("cnep", request.parameters)
}
