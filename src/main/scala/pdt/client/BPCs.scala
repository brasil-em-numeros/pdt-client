package pdt.client

import io.circe.generic.auto._
import pdt.client.decoders.localDateDecoder
import pdt.http.HttpClient.{HttpClient, get}
import pdt.domain._
import pdt.http.implicits.HttpRequestOps
import zio.RIO

object BPCs {

  def by(request: BPCRequest): RIO[HttpClient, List[BPC]] =
    get[BPC]("bpc-por-municipio", request.parameters)

  def by(cpf: Cpf): RIO[HttpClient, List[BPC]] =
    get[BPC]("bpc-por-cpf-ou-nis", cpf.parameters)

  def by(nis: Nis): RIO[HttpClient, List[BPC]] =
    get[BPC]("bpc-por-cpf-ou-nis", nis.parameters)
}
