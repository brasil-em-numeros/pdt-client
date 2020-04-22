package pdt

import java.time.{LocalDate, YearMonth}
import java.util.concurrent.TimeUnit

import org.http4s.client.Client
import org.http4s.client.blaze.BlazeClientBuilder
import pdt.client._
import pdt.domain._
import pdt.http.HttpClient
import pdt.log.Logger
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
    val loggerLayer = Logger.console

    val httpClientLayer = http4sClient.toLayer.orDie
    val http4sClientLayer = (loggerLayer ++ httpClientLayer) >>> HttpClient.http4s

    val inicio = LocalDate.of(2019, 4, 1)
    val fim = LocalDate.of(2019, 4, 30)

    val anoMes = YearMonth.of(2020, 1)

    val program = for {
      start <- currentTime(TimeUnit.MILLISECONDS)
      result <- ServidoresFederais.by(415210208)

      finish <- currentTime(TimeUnit.MILLISECONDS)
      _ <- putStrLn(result.toString())
      _ <- putStrLn("Execution time: " + (finish - start))
    } yield ()

    program.provideSomeLayer[ZEnv](http4sClientLayer)
  }
}
