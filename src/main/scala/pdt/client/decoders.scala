package pdt.client

import java.time.LocalDate
import java.time.format.DateTimeFormatter

import io.circe.Decoder
import pdt.domain.AbrangenciaDefinidaDecisaoJudicial

import scala.util.Try

object decoders {
  private val formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy")

  private val emptyLocalDateDecoder = Decoder.decodeString.map(_ => LocalDate.of(0, 1, 1))

  implicit val localDateDecoder = Decoder.decodeLocalDateWithFormatter(formatter).or(emptyLocalDateDecoder)

  implicit val bigDecimalDateDecoder =
    Decoder.decodeString.emapTry { str =>
      Try(BigDecimal(str.replace(".", "").replace(",", ".")))
    }.or(Decoder.decodeBigDecimal)

  implicit val decodeAbrangenciaDefinidaDecisaoJudicial =
    decodePossiblyWrappedValue("descricao", AbrangenciaDefinidaDecisaoJudicial)

  private def decodePossiblyWrappedValue[A](field: String, f: String => A): Decoder[A] =
    Decoder.decodeJsonObject.map(_.apply(field))
      .map { case Some(value) => f(value.toString) }
      .or(Decoder.decodeJson.map(json => f(json.toString)))
}
