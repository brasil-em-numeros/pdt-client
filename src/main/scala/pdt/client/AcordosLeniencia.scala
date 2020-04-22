package pdt.client

import io.circe.generic.auto._
import pdt.client.decoders.localDateDecoder
import pdt.domain.{AcordoLeniencia, AcordoLenienciaRequest}
import pdt.http.HttpClient.{Response, get}
import pdt.http.implicits.HttpRequestOps
import zio.ZIO

object AcordosLeniencia {

  def by(id: Long): Response[AcordoLeniencia] =
    get[AcordoLeniencia]("acordos-leniencia", id)

  def by(request: AcordoLenienciaRequest): Response[List[AcordoLeniencia]] =
    get[AcordoLeniencia]("acordos-leniencia", request.parameters)

  def all: Response[List[AcordoLeniencia]] = {
    val tasks = (1 to 5).map(p => by(AcordoLenienciaRequest(pagina = p)))

    ZIO.collectAllPar(tasks)
      .foldM(
        ZIO.fail(_),
        a => ZIO.succeed(a.flatten))
  }
}