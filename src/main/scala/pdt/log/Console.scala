package pdt.log

import pdt.implicits.ThrowableOps
import zio.UIO
import zio.clock._
import zio.console.{ Console => ConsoleZIO }

private[log] final case class Console(clock: Clock.Service, console: ConsoleZIO.Service)
  extends Logger.Service {

  def info(message: => String): UIO[Unit] = print(message)

  def error(t: Throwable)(message: => String): UIO[Unit] =
    for {
      _ <- print(message)
      _ <- console.putStrLn(t.stackTrace)
    } yield ()

  private def print(message: => String): UIO[Unit] =
    for {
      timestamp <- clock.currentDateTime.orDie
      _         <- console.putStrLn(s"[$timestamp] $message")
    } yield ()
}
