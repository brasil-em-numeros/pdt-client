package pdt.client

import io.circe.generic.auto._
import pdt.client.decoders._
import pdt.domain._
import pdt.http.HttpClient.{Response, get}
import pdt.http.implicits.HttpRequestOps

object Viagens {

  def by(request: ViagemRequest): Response[List[Viagem]] =
    get[Viagem]("viagens", request.parameters)

  def by(id: Long): Response[Viagem] =
    get[Viagem]("viagens", id)

  def by(request: Cpf): Response[List[Viagem]] =
    get[Viagem]("viagens-por-cpf", request.parameters)
}
