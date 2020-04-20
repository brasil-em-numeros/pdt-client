package pdt.client

import java.time.format.DateTimeFormatter

import io.circe.generic.auto._
import pdt.client.decoders._
import pdt.http.HttpClient.{HttpClient, get}
import pdt.domain._
import pdt.http.implicits.HttpRequestOps
import zio.RIO

object DespesasPublicas {

  def documentos(request: DocumentoRequest): RIO[HttpClient, List[Documento]] =
    get[Documento]("despesas/documentos", request.parameters)

  def documento(id: Long): RIO[HttpClient, Documento] =
    get[Documento]("despesas/documentos", id)

  def documentos(request: FavorecidoRequest): RIO[HttpClient, List[Documento]] =
    get[Documento]("despesas/documentos-por-favorecido", request.parameters)

  def documento(codigo: String): RIO[HttpClient, Documento] =
    get[Documento]("despesas/documentos", codigo)

  def documentosRelacionados(request: DocumentoRelacionadoRequest): RIO[HttpClient, List[Documento]] =
    get[Documento]("despesas/documentos-relacionados", request.parameters)

  def empenhosImpactados(request: EmpenhoImpactadoRequest): RIO[HttpClient, List[EmpenhoImpactado]] =
    get[EmpenhoImpactado]("despesas/empenhos-impactados", request.parameters)

  def favorecidosFinais(request: FavorecidoPorDocumentoRequest): RIO[HttpClient, List[FavorecidoFinal]] =
    get[FavorecidoFinal]("despesas/favorecidos-finais-por-documento", request.parameters)

  def funcionaisProgramaticas(request: FuncionalProgramaticaRequest): RIO[HttpClient, List[FuncionalProgramatica]] =
    get[FuncionalProgramatica]("despesas/por-funcional-programatica", request.parameters)

  def by(request: DespesasPorOrgaoRequest): RIO[HttpClient, List[Despesa]] =
    get[Despesa]("despesas/por-orgao", request.parameters)

  def recursosRecebidos(request: RecursoRecebidoRequest): RIO[HttpClient, List[RecursoRecebido]] = {
    implicit val yearMonthFormatter = DateTimeFormatter.ofPattern("MM/yyyy")
    get[RecursoRecebido]("despesas/recursos-recebidos", request.parameters)
  }

  def subitensDeEmpenho(codigo: String): RIO[HttpClient, List[SubitemDeEmpenho]] =
    get[SubitemDeEmpenho]("despesas/subitens-de-empenho", Map("codigoDocumento" -> codigo))
}
