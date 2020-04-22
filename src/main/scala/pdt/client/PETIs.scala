package pdt.client

import io.circe.generic.auto._
import pdt.client.decoders._
import pdt.domain._
import pdt.http.HttpClient.{Response, get}
import pdt.http.implicits.HttpRequestOps

object PETIs {

  def by(cpf: Cpf): Response[List[PETI]] =
    get[PETI]("peti-codigo-por-cpf-ou-nis", cpf.parameters)

  def by(nis: Nis): Response[List[PETI]] =
    get[PETI]("peti-codigo-por-cpf-ou-nis", nis.parameters)

  def by(municipio: MunicipioRequest): Response[List[PETI]] =
    get[PETI]("peti-por-municipio", municipio.parameters)
}
