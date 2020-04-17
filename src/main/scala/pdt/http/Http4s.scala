package pdt.http

import io.circe.Decoder
import org.http4s.Uri
import org.http4s.circe.CirceEntityCodec.circeEntityDecoder
import org.http4s.client.Client
import org.http4s.client.dsl.Http4sClientDsl
import pdt.log.Logger
import zio._
import zio.interop.catz._

private[http] final case class Http4s(logger: Logger.Service, client: Client[Task])
  extends HttpClient.Service with Http4sClientDsl[Task] {

  def get[T](resource: String, parameters: Map[String, String])
            (implicit d: Decoder[T]): Task[T] = {
    val uri = Uri(path = rootUrl + resource).withQueryParams(parameters)

    logger.info(s"GET REQUEST: $uri") *>
      client
        .expect[T](uri.toString())
        .foldM(
          e => logger.error(e)(s"Couldn't fetch data from $uri: ${e.getMessage}") *> IO.fail(e),
          ZIO.succeed(_))
  }
}