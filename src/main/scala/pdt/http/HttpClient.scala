package pdt.http

import io.circe.Decoder
import org.http4s.client.Client
import zio._
import zio.logging.{Logger, Logging}

object HttpClient {
  type HttpClient = Has[Service]

  type Response[T] = RIO[HttpClient, T]

  trait Service {
    protected final val rootUrl = "http://www.transparencia.gov.br/api-de-dados/"

    def get[T](uri: String, parameters: Map[String, String])
              (implicit d: Decoder[T]): Task[T]
  }

  def get[T](resource: String, parameters: Map[String, String] = Map())
            (implicit d: Decoder[T]): Response[List[T]] =
    RIO.accessM[HttpClient](_.get.get[List[T]](resource, parameters))

  def get[T](resource: String, id: Long)
            (implicit d: Decoder[T]): Response[T] =
    get[T](resource, id.toString)

  def get[T](resource: String, pathParameter: String)
            (implicit d: Decoder[T]): Response[T] =
    RIO.accessM[HttpClient](_.get.get[T](s"$resource/$pathParameter", Map()))

  def get[T](resource: String, pathParameter: String, parameters: Map[String, String])
            (implicit d: Decoder[T]): Response[List[T]] =
    get[T](s"$resource/$pathParameter", parameters)

  def http4s: URLayer[Logging with Has[Client[Task]], HttpClient] =
    ZLayer.fromServices[Logger[String], Client[Task], Service] { (logger, http4sClient) =>
      Http4s(logger, http4sClient)
    }
}