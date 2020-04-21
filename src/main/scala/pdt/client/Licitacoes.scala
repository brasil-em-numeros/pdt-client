package pdt.client

import io.circe.generic.auto._
import pdt.client.decoders._
import pdt.http.HttpClient.{HttpClient, get}
import pdt.domain._
import pdt.http.implicits.HttpRequestOps
import zio.RIO

object Licitacoes {

  def by(request: LicitacaoRequest): RIO[HttpClient, List[LicitacaoPoderExecutivoFederal]] =
    get[LicitacaoPoderExecutivoFederal]("licitacoes", request.parameters)

  def empenhos(request: UASGModalidadeRequest): RIO[HttpClient, List[Empenho]] =
    get[Empenho]("licitacoes/empenhos", request.parameters)

  def modalidades(): RIO[HttpClient, List[Modalidade]] =
    get[Modalidade]("licitacoes/modalidades")

  def by(request: UASGModalidadeRequest): RIO[HttpClient, List[LicitacaoPoderExecutivoFederal]] =
    get[LicitacaoPoderExecutivoFederal]("licitacoes/por-uasg-modalidade-numero", request.parameters)

  def uasgs(): RIO[HttpClient, List[UASG]] =
    get[UASG]("licitacoes/uasgs")

  def by(id: Long): RIO[HttpClient, LicitacaoPoderExecutivoFederal] =
    get[LicitacaoPoderExecutivoFederal]("licitacoes", id)
}
