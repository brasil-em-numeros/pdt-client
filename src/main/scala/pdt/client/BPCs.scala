package pdt.client

import io.circe.generic.auto._
import pdt.client.decoders.localDateDecoder
import pdt.domain._
import pdt.http.HttpClient.{Response, get}
import pdt.http.implicits.HttpRequestOps

object BPCs {

  def by(municipio: MunicipioRequest): Response[List[BPC]] =
    get[BPC]("bpc-por-municipio", municipio.parameters)

  def by(cpf: Cpf): Response[List[BPC]] =
    get[BPC]("bpc-por-cpf-ou-nis", cpf.parameters)

  def by(nis: Nis): Response[List[BPC]] =
    get[BPC]("bpc-por-cpf-ou-nis", nis.parameters)
}
