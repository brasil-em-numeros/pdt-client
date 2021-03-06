package pdt.client

import io.circe.generic.auto._
import pdt.client.decoders.localDateDecoder
import pdt.domain._
import pdt.http.HttpClient._
import pdt.http.implicits.HttpRequestOps
import zio.RIO

object Convenios {

  def by(request: ConvenioRequest): Response[List[ConvenioPoderExecutivoFederal]] =
    get[ConvenioPoderExecutivoFederal]("convenios", request.parameters)

  def by(id: Long): Response[ConvenioPoderExecutivoFederal] =
    RIO.accessM[HttpClient](_.get.get[ConvenioPoderExecutivoFederal]("convenios/id", Map("id" -> id.toString)))

  def by(request: NumeroConvenioRequest): Response[List[ConvenioPoderExecutivoFederal]] =
    get[ConvenioPoderExecutivoFederal]("convenios/numero", request.parameters)

  def by(request: NumeroOriginalRequest): Response[List[ConvenioPoderExecutivoFederal]] =
    get[ConvenioPoderExecutivoFederal]("convenios/numero-original", request.parameters)

  def tipoInstrumentos(): Response[List[TipoInstrumento]] =
    get[TipoInstrumento]("convenios/tipo-instrumento", Map.empty[String, String])
}
