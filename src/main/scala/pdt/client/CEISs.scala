package pdt.client

import io.circe.generic.auto._
import pdt.client.decoders._
import pdt.domain._
import pdt.http.HttpClient.{Response, get}
import pdt.http.implicits.HttpRequestOps

object CEISs {

  def by(id: Long): Response[CEIS] =
    get[CEIS]("ceis", id)

  def by(request: CEISRequest): Response[List[CEIS]] =
    get[CEIS]("ceis", request.parameters)
}
