package pdt.client

import io.circe.Decoder
import org.http4s.Uri
import org.http4s.circe.CirceEntityCodec.circeEntityDecoder
import org.http4s.client.Client
import org.http4s.client.dsl.Http4sClientDsl
import zio._
import zio.interop.catz._

private[client] final case class Http4s(client: Client[Task])
  extends HttpClient.Service with Http4sClientDsl[Task] {

  def get[T](resource: String, parameters: Map[String, String])
            (implicit d: Decoder[T]): Task[List[T]] = {
    val uri = Uri(path = rootUrl + resource).withQueryParams(parameters)

    client
      .expect[List[T]](uri.toString())
      .foldM(IO.fail(_), ZIO.succeed(_))
  }
}