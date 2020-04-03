package pdt.client

import io.circe.Decoder
import pdt.client.HttpClient.HttpClient
import zio.{Has, Task, URLayer, ZLayer}
import zio.test.mock._

object MockHttpClient {

  sealed trait Tag[I, A] extends Method[HttpClient, I, A] {
    def envBuilder: URLayer[Has[Proxy], HttpClient] = MockHttpClient.envBuilder
  }

  case class Get[T]() extends Tag[(String, Map[String, String]), T]

  val envBuilder: URLayer[Has[Proxy], HttpClient] =
    ZLayer.fromService { invoke =>
      new HttpClient.Service {
        def get[T](uri: String, parameters: Map[String, String])
                  (implicit d: Decoder[T]): Task[T] =
          invoke(Get[T], uri, parameters)
      }
    }
}
