package pdt.client

import io.circe.generic.auto._
import pdt.client.decoders._
import pdt.http.HttpClient.{HttpClient, get}
import pdt.domain._
import pdt.http.implicits.HttpRequestOps
import zio.RIO

object Viagens {

  def by(request: ViagemRequest): RIO[HttpClient, List[Viagem]] =
    get[Viagem]("viagens", request.parameters)

  def by(id: Long): RIO[HttpClient, Viagem] =
    get[Viagem]("viagens", id)

  def by(request: Cpf): RIO[HttpClient, List[Viagem]] =
    get[Viagem]("viagens-por-cpf", request.parameters)
}
