package pdt.client

import io.circe.generic.auto._
import pdt.client.decoders._
import pdt.domain._
import pdt.http.HttpClient.{Response, get}
import pdt.http.implicits.HttpRequestOps

object ServidoresFederais {

  def by(request: ServidorFederalRequest): Response[List[ServidorFederal]] =
    get[ServidorFederal]("servidores", request.parameters)

  def by(id: Long): Response[ServidorFederal] =
    get[ServidorFederal]("servidores", id)

  def funcoesCargos(): Response[List[FuncaoCargo]] =
    get[FuncaoCargo]("servidores/funcoes-e-cargos")

  def porOrgao(request: ServidorPorOrgaoRequest): Response[List[ServidorPorOrgao]] =
    get[ServidorPorOrgao]("servidores/por-orgao", request.parameters)

  def remuneracoes(request: RemuneracaoRequest): Response[List[RemuneracaoServidorFederal]] =
    get[RemuneracaoServidorFederal]("servidores/remuneracao", request.parameters)
}
