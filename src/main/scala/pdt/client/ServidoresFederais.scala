package pdt.client

import io.circe.generic.auto._
import pdt.client.decoders._
import pdt.http.HttpClient.{HttpClient, get}
import pdt.domain._
import pdt.http.implicits.HttpRequestOps
import zio.RIO

object ServidoresFederais {

  def by(request: ServidorFederalRequest): RIO[HttpClient, List[ServidorFederal]] =
    get[ServidorFederal]("servidores", request.parameters)

  def by(id: Long): RIO[HttpClient, ServidorFederal] =
    get[ServidorFederal]("servidores", id)

  def funcoesCargos(): RIO[HttpClient, List[FuncaoCargo]] =
    get[FuncaoCargo]("servidores/funcoes-e-cargos")

  def porOrgao(request: ServidorPorOrgaoRequest): RIO[HttpClient, List[ServidorPorOrgao]] =
    get[ServidorPorOrgao]("servidores/por-orgao", request.parameters)

  def remuneracoes(request: RemuneracaoRequest): RIO[HttpClient, List[RemuneracaoServidorFederal]] =
    get[RemuneracaoServidorFederal]("servidores/remuneracao", request.parameters)
}
