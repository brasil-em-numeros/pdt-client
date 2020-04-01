package pdt.client

import io.circe.generic.auto._
import pdt.client.decoders.localDateDecoder
import pdt.client.HttpClient.{HttpClient, get}
import pdt.domain.{AcordoLeniencia, AcordoLenienciaRequest}
import pdt.implicits.HttpRequestOps
import zio.{RIO, ZIO}

object AcordosLeniencia {

  def by(id: Long): RIO[HttpClient, AcordoLeniencia] =
    get[AcordoLeniencia]("acordos-leniencia", id)

  def by(request: AcordoLenienciaRequest): RIO[HttpClient, List[AcordoLeniencia]] =
    get[AcordoLeniencia]("acordos-leniencia", request.parameters)

  def all: RIO[HttpClient, List[AcordoLeniencia]] = {
    val tasks = (1 to 5).map(p => by(AcordoLenienciaRequest(p)))

    ZIO.collectAllPar(tasks)
      .foldM(
        ZIO.fail(_),
        a => ZIO.succeed(a.flatten))
  }
}