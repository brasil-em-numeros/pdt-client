package pdt.client

import java.time.format.DateTimeFormatter

import io.circe.generic.auto._
import pdt.client.decoders._
import pdt.domain._
import pdt.http.HttpClient.{Response, get}
import pdt.http.implicits.HttpRequestOps

object DespesasPublicas {

  def documentos(request: DocumentoRequest): Response[List[Documento]] =
    get[Documento]("despesas/documentos", request.parameters)

  def documento(id: Long): Response[Documento] =
    get[Documento]("despesas/documentos", id)

  def documentos(request: FavorecidoRequest): Response[List[Documento]] =
    get[Documento]("despesas/documentos-por-favorecido", request.parameters)

  def documento(codigo: String): Response[Documento] =
    get[Documento]("despesas/documentos", codigo)

  def documentosRelacionados(request: DocumentoRelacionadoRequest): Response[List[Documento]] =
    get[Documento]("despesas/documentos-relacionados", request.parameters)

  def empenhosImpactados(request: EmpenhoImpactadoRequest): Response[List[EmpenhoImpactado]] =
    get[EmpenhoImpactado]("despesas/empenhos-impactados", request.parameters)

  def favorecidosFinais(request: FavorecidoPorDocumentoRequest): Response[List[FavorecidoFinal]] =
    get[FavorecidoFinal]("despesas/favorecidos-finais-por-documento", request.parameters)

  def funcionaisProgramaticas(request: FuncionalProgramaticaRequest): Response[List[FuncionalProgramatica]] =
    get[FuncionalProgramatica]("despesas/por-funcional-programatica", request.parameters)

  def by(request: DespesasPorOrgaoRequest): Response[List[Despesa]] =
    get[Despesa]("despesas/por-orgao", request.parameters)

  def recursosRecebidos(request: RecursoRecebidoRequest): Response[List[RecursoRecebido]] = {
    implicit val yearMonthFormatter = DateTimeFormatter.ofPattern("MM/yyyy")
    get[RecursoRecebido]("despesas/recursos-recebidos", request.parameters)
  }

  def subitensDeEmpenho(codigo: String): Response[List[SubitemDeEmpenho]] =
    get[SubitemDeEmpenho]("despesas/subitens-de-empenho", Map("codigoDocumento" -> codigo))
}
