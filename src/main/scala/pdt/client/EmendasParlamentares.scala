package pdt.client

import io.circe.generic.auto._
import pdt.client.decoders._
import pdt.http.HttpClient.{HttpClient, get}
import pdt.domain._
import pdt.http.implicits.HttpRequestOps
import zio.RIO

object EmendasParlamentares {

  def by(request: EmendaParlamentarRequest): RIO[HttpClient, List[EmendaParlamentar]] =
    get[EmendaParlamentar]("emendas", request.parameters)

  def documentos(codigo: String, pagina: Int = 1): RIO[HttpClient, List[EmendaDocumento]] =
    get[EmendaDocumento]("emendas/documentos", codigo, Map("pagina" -> pagina.toString))
}
