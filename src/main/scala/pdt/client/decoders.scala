package pdt.client

import java.time.LocalDate
import java.time.format.DateTimeFormatter

import io.circe.{Decoder, DecodingFailure}
import pdt.domain._

import scala.util.Try

object decoders {
  private val formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy")

  private val emptyLocalDateDecoder = Decoder.decodeString.map(_ => LocalDate.of(0, 1, 1))

  implicit val localDateDecoder = Decoder.decodeLocalDateWithFormatter(formatter).or(emptyLocalDateDecoder)

  implicit val bigDecimalDecoder =
    Decoder.decodeString.emapTry { str =>
      Try(BigDecimal(str.replace(".", "").replace(",", ".")))
    }.or(Decoder.decodeBigDecimal)

  implicit val decodeAbrangenciaDefinidaDecisaoJudicial =
    decodePossiblyWrappedValue(AbrangenciaDefinidaDecisaoJudicial)

  implicit val decodeLocalidadePessoa = decodePossiblyWrappedValue(LocalidadePessoa)

  implicit val decodeTipoPessoa = decodePossiblyWrappedValue(TipoPessoa)

  private def decodePossiblyWrappedValue[A](f: String => A): Decoder[A] =
    Decoder.decodeJsonObject.map(_.apply("descricao")).map {
      case Some(json) => f(json.asString.get)
      case None => throw DecodingFailure("deu ruim", Nil)
    }.or(Decoder.decodeString.map(f))
}
