package pdt.client

import java.time.LocalDate
import java.time.format.DateTimeFormatter

import io.circe.Decoder
import pdt.domain._

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

  implicit val decodeLocalidadePessoa = decodePossiblyWrappedValue("descricao", LocalidadePessoa)

  implicit val decodeTipoPessoa = decodePossiblyWrappedValue("descricao", TipoPessoa)

  private def decodePossiblyWrappedValue[A](field: String, f: String => A): Decoder[A] =
    Decoder.decodeString.map(f).or(
      Decoder.decodeJsonObject.map(_.apply(field).map(json => f(json.toString)).getOrElse(f("")))
    )
}
