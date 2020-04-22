package pdt.client

import io.circe.generic.auto._
import pdt.client.decoders._
import pdt.domain._
import pdt.http.HttpClient.{Response, get}
import pdt.http.implicits.HttpRequestOps

object GarantiaSafra {

  def by(cpf: Cpf): Response[List[BeneficioSafra]] =
    get[BeneficioSafra]("safra-codigo-por-cpf-ou-nis", cpf.parameters)

  def by(nis: Nis): Response[List[BeneficioSafra]] =
    get[BeneficioSafra]("safra-codigo-por-cpf-ou-nis", nis.parameters)

  def by(municipio: MunicipioRequest): Response[List[BeneficioSafra]] =
    get[BeneficioSafra]("safra-por-municipio", municipio.parameters)
}
