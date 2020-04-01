package pdt.client

import java.time.format.DateTimeFormatter

import io.circe.Decoder

object decoders {
  private val formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy")

  implicit val localDateDecoder = Decoder.decodeLocalDateWithFormatter(formatter)
}
