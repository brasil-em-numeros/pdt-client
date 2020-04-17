package pdt.log

import zio._
import zio.clock._
import zio.console.{ Console => ConsoleZIO }

object Logger {
  type Logger = Has[Service]

  trait Service {
    def info(message: => String): UIO[Unit]
    def error(t: Throwable)(message: => String): UIO[Unit]
  }

  def console: URLayer[Clock with ConsoleZIO, Logger] =
    ZLayer.fromServices[Clock.Service, ConsoleZIO.Service, Service] { (clock, console) =>
      Console(clock, console)
    }
}
