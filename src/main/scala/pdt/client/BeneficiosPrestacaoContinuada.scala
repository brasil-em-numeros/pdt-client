package pdt.client

import io.circe.generic.auto._
import pdt.client.decoders.localDateDecoder
import pdt.client.HttpClient.{HttpClient, get}
import pdt.domain._
import pdt.implicits.HttpRequestOps
import zio.RIO

object BeneficiosPrestacaoContinuada {

  def by(request: BeneficioPrestacaoContinuadaRequest): RIO[HttpClient, List[BeneficioPrestacaoContinuada]] =
    get[BeneficioPrestacaoContinuada]("bpc-por-municipio", request.parameters)

  def by(cpf: Cpf): RIO[HttpClient, List[BeneficioPrestacaoContinuada]] =
    get[BeneficioPrestacaoContinuada]("bpc-por-cpf-ou-nis", cpf.parameters)

  def by(nis: Nis): RIO[HttpClient, List[BeneficioPrestacaoContinuada]] =
    get[BeneficioPrestacaoContinuada]("bpc-por-cpf-ou-nis", nis.parameters)
}
