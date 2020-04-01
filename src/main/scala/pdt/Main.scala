package pdt

import java.util.concurrent.TimeUnit

import org.http4s.client.Client
import org.http4s.client.blaze.BlazeClientBuilder
import pdt.client._
import zio._
import zio.clock._
import zio.console.putStrLn
import zio.interop.catz._

import scala.concurrent.ExecutionContext.Implicits

object Main extends App {

  def run(args: List[String]): ZIO[ZEnv, Nothing, Int] = {
    val program = for {
      http4sClient <- makeHttpClient

      _ <- makeProgram(http4sClient)
    } yield ()

    program.foldM(
      e => putStrLn(s"Execution failed with: ${e.printStackTrace()}") *> ZIO.succeed(1),
      _ => ZIO.succeed(0)
    )
  }

  private def makeHttpClient: UIO[TaskManaged[Client[Task]]] =
    ZIO.runtime[Any].map { implicit rts =>
        BlazeClientBuilder
          .apply[Task](Implicits.global)
          .resource
          .toManaged
      }

  private def makeProgram(http4sClient: TaskManaged[Client[Task]]): RIO[ZEnv, Unit] = {
    val http4sClientLayer = http4sClient.toLayer.orDie
    val httpClientLayer = http4sClientLayer >>> HttpClient.http4s

    val program = for {
      start <- currentTime(TimeUnit.MILLISECONDS)
      orgs <- AcordosLeniencia.all
      finish <- currentTime(TimeUnit.MILLISECONDS)
      _ <- putStrLn(orgs.mkString("\n"))
      _ <- putStrLn("Execution time: " + (finish - start))
    } yield ()

    program.provideSomeLayer[ZEnv](httpClientLayer)
  }
}
