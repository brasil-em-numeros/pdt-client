package pdt.client

import io.circe.generic.auto._
import pdt.client.decoders.localDateDecoder
import pdt.client.HttpClient.{HttpClient, get}
import pdt.domain._
import pdt.implicits.HttpRequestOps
import zio.RIO

object Convenios {

  def by(request: ConvenioRequest): RIO[HttpClient, List[ConvenioPoderExecutivoFederal]] =
    get[ConvenioPoderExecutivoFederal]("convenios", request.parameters)

  def by(id: Long): RIO[HttpClient, ConvenioPoderExecutivoFederal] =
    RIO.accessM[HttpClient](_.get.get[ConvenioPoderExecutivoFederal]("convenios/id", Map("id" -> id.toString)))

  def by(request: NumeroConvenioRequest): RIO[HttpClient, List[ConvenioPoderExecutivoFederal]] =
    get[ConvenioPoderExecutivoFederal]("convenios/numero", request.parameters)

  def by(request: NumeroOriginalRequest): RIO[HttpClient, List[ConvenioPoderExecutivoFederal]] =
    get[ConvenioPoderExecutivoFederal]("convenios/numero-original", request.parameters)

  def tipoInstrumentos(): RIO[HttpClient, List[TipoInstrumento]] =
    get[TipoInstrumento]("convenios/tipo-instrumento", Map.empty[String, String])
}
