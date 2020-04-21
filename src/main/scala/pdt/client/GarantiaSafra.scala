package pdt.client

import io.circe.generic.auto._
import pdt.client.decoders._
import pdt.http.HttpClient.{HttpClient, get}
import pdt.domain._
import pdt.http.implicits.HttpRequestOps
import zio.RIO

object GarantiaSafra {

  def by(cpf: Cpf): RIO[HttpClient, List[BeneficioSafra]] =
    get[BeneficioSafra]("safra-codigo-por-cpf-ou-nis", cpf.parameters)

  def by(nis: Nis): RIO[HttpClient, List[BeneficioSafra]] =
    get[BeneficioSafra]("safra-codigo-por-cpf-ou-nis", nis.parameters)

  def by(municipio: MunicipioRequest): RIO[HttpClient, List[BeneficioSafra]] =
    get[BeneficioSafra]("safra-por-municipio", municipio.parameters)
}
