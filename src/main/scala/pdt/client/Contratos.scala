package pdt.client

import io.circe.generic.auto._
import pdt.client.decoders.localDateDecoder
import pdt.domain._
import pdt.http.HttpClient._
import pdt.http.implicits.HttpRequestOps
import zio.RIO

object Contratos {

  def by(request: ContratoRequest): Response[List[ContratoPoderExecutivoFederal]] =
    get[ContratoPoderExecutivoFederal]("contratos", request.parameters)

  def by(id: Long): Response[ContratoPoderExecutivoFederal] =
    RIO.accessM[HttpClient](_.get.get[ContratoPoderExecutivoFederal]("contratos/id", Map("id" -> id.toString)))

  def by(request: NumeroContratoRequest): Response[List[ContratoPoderExecutivoFederal]] =
    get[ContratoPoderExecutivoFederal]("contratos/numero", request.parameters)

  def apostilamento(id: Int): Response[List[Apostilamento]] =
    get[Apostilamento]("contratos/apostilamento", Map("id" -> id.toString))

  def documentosRelacionados(id: Int): Response[List[DocumentosRelacionados]] =
    get[DocumentosRelacionados]("contratos/documentos-relacionados", Map("id" -> id.toString))

  def termoAditivo(id: Int): Response[List[TermoAditivo]] =
    get[TermoAditivo]("contratos/termo-aditivo", Map("id" -> id.toString))
}
