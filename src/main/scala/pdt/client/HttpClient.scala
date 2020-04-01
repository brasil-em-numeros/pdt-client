package pdt.client

import io.circe.Decoder
import org.http4s.client.Client
import zio.{Has, RIO, Task, URLayer, ZLayer}

object HttpClient {
  type HttpClient = Has[Service]

  trait Service {
    protected final val rootUrl = "http://www.transparencia.gov.br/api-de-dados/"

    def get[T](uri: String, parameters: Map[String, String])
              (implicit d: Decoder[T]): Task[List[T]]
  }

  def get[T](resource: String, parameters: Map[String, String] = Map())
            (implicit d: Decoder[T]): RIO[HttpClient, List[T]] =
    RIO.accessM[HttpClient](_.get.get[T](resource, parameters))

  def http4s: URLayer[Has[Client[Task]], Has[Service]] =
    ZLayer.fromService[Client[Task], Service] { http4sClient: Client[Task] =>
      Http4s(http4sClient)
    }
}