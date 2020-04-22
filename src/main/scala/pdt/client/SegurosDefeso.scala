package pdt.client

import io.circe.generic.auto._
import pdt.client.decoders._
import pdt.domain._
import pdt.http.HttpClient.{Response, get}
import pdt.http.implicits.HttpRequestOps

object SegurosDefeso {

  def by(cpf: Cpf): Response[List[SeguroDefeso]] =
    get[SeguroDefeso]("seguro-defeso-codigo", cpf.parameters)

  def by(nis: Nis): Response[List[SeguroDefeso]] =
    get[SeguroDefeso]("seguro-defeso-codigo", nis.parameters)

  def by(municipio: MunicipioRequest): Response[List[SeguroDefeso]] =
    get[SeguroDefeso]("seguro-defeso-por-municipio", municipio.parameters)
}
