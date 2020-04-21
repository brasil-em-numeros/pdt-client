package pdt.client

import io.circe.generic.auto._
import pdt.client.decoders._
import pdt.http.HttpClient.{HttpClient, get}
import pdt.domain._
import pdt.http.implicits.HttpRequestOps
import zio.RIO

object PETIs {

  def by(cpf: Cpf): RIO[HttpClient, List[PETI]] =
    get[PETI]("peti-codigo-por-cpf-ou-nis", cpf.parameters)

  def by(nis: Nis): RIO[HttpClient, List[PETI]] =
    get[PETI]("peti-codigo-por-cpf-ou-nis", nis.parameters)

  def by(municipio: MunicipioRequest): RIO[HttpClient, List[PETI]] =
    get[PETI]("peti-por-municipio", municipio.parameters)
}
