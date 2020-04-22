package pdt.client

import io.circe.generic.auto._
import pdt.client.decoders._
import pdt.domain._
import pdt.http.HttpClient.{Response, get}
import pdt.http.implicits.HttpRequestOps

object EmendasParlamentares {

  def by(request: EmendaParlamentarRequest): Response[List[EmendaParlamentar]] =
    get[EmendaParlamentar]("emendas", request.parameters)

  def documentos(codigo: String, pagina: Int = 1): Response[List[EmendaDocumento]] =
    get[EmendaDocumento]("emendas/documentos", codigo, Map("pagina" -> pagina.toString))
}
