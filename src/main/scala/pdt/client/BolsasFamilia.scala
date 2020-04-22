package pdt.client

import io.circe.generic.auto._
import pdt.client.decoders.localDateDecoder
import pdt.domain._
import pdt.http.HttpClient.{Response, get}
import pdt.http.implicits.HttpRequestOps

object BolsasFamilia {

  def by(request: DisponivelPorCpf): Response[List[BolsaFamilia]] =
    get[BolsaFamilia]("bolsa-familia-disponivel-por-cpf-ou-nis", request.parameters)

  def by(request: DisponivelPorNis): Response[List[BolsaFamilia]] =
    get[BolsaFamilia]("bolsa-familia-disponivel-por-cpf-ou-nis", request.parameters)

  def by(municipio: MunicipioRequest): Response[List[BolsaFamilia]] =
    get[BolsaFamilia]("bolsa-familia-por-municipio", municipio.parameters)

  def by(request: SacadoPorNis): Response[List[BolsaFamilia]] =
    get[BolsaFamilia]("bolsa-familia-sacado-por-nis", request.parameters)
}
