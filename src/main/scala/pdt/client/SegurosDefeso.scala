package pdt.client

import io.circe.generic.auto._
import pdt.client.decoders._
import pdt.http.HttpClient.{HttpClient, get}
import pdt.domain._
import pdt.http.implicits.HttpRequestOps
import zio.RIO

object SegurosDefeso {

  def by(cpf: Cpf): RIO[HttpClient, List[SeguroDefeso]] =
    get[SeguroDefeso]("seguro-defeso-codigo", cpf.parameters)

  def by(nis: Nis): RIO[HttpClient, List[SeguroDefeso]] =
    get[SeguroDefeso]("seguro-defeso-codigo", nis.parameters)

  def by(municipio: MunicipioRequest): RIO[HttpClient, List[SeguroDefeso]] =
    get[SeguroDefeso]("seguro-defeso-por-municipio", municipio.parameters)
}
