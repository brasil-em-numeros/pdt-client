package pdt.client

import java.time.LocalDate
import java.time.format.DateTimeFormatter

import io.circe.Decoder

import scala.util.Try

object decoders {
  private val formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy")

  implicit val localDateDecoder = Decoder.decodeString.emapTry { str =>
    Try(LocalDate.parse(str, formatter)).orElse(Try(LocalDate.of(0, 1, 1)))
  }
}
