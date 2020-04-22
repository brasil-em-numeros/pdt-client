package pdt.client

import io.circe.generic.auto._
import pdt.client.decoders._
import pdt.domain._
import pdt.http.HttpClient.{Response, get}
import pdt.http.implicits.HttpRequestOps

object Licitacoes {

  def by(request: LicitacaoRequest): Response[List[LicitacaoPoderExecutivoFederal]] =
    get[LicitacaoPoderExecutivoFederal]("licitacoes", request.parameters)

  def empenhos(request: UASGModalidadeRequest): Response[List[Empenho]] =
    get[Empenho]("licitacoes/empenhos", request.parameters)

  def modalidades(): Response[List[Modalidade]] =
    get[Modalidade]("licitacoes/modalidades")

  def by(request: UASGModalidadeRequest): Response[List[LicitacaoPoderExecutivoFederal]] =
    get[LicitacaoPoderExecutivoFederal]("licitacoes/por-uasg-modalidade-numero", request.parameters)

  def uasgs(): Response[List[UASG]] =
    get[UASG]("licitacoes/uasgs")

  def by(id: Long): Response[LicitacaoPoderExecutivoFederal] =
    get[LicitacaoPoderExecutivoFederal]("licitacoes", id)
}
