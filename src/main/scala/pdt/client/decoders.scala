package pdt.client

import java.time.{LocalDate, YearMonth}
import java.time.format.DateTimeFormatter

import io.circe.{Decoder, DecodingFailure}
import pdt.domain._

import scala.util.Try

object decoders {
  private val localDateFormatter = DateTimeFormatter.ofPattern("dd/MM/yyyy")
  private val yearMonth = DateTimeFormatter.ofPattern("yyyyMM")
  private val `month/year` = DateTimeFormatter.ofPattern("MM/yyyy")

  private val emptyLocalDateDecoder = Decoder.decodeString.map(_ => LocalDate.of(0, 1, 1))

  implicit val localDateDecoder: Decoder[LocalDate] =
    Decoder.decodeLocalDateWithFormatter(localDateFormatter).or(emptyLocalDateDecoder)

  implicit val yearMonthDecoder: Decoder[YearMonth] =
    Decoder.decodeInt.map(v => YearMonth.parse(v.toString, yearMonth))
      .or(Decoder.decodeYearMonthWithFormatter(`month/year`))

  implicit val bigDecimalDecoder =
    Decoder.decodeString.emapTry { str =>
      Try(BigDecimal(str.replace(".", "").replace(",", ".")))
    }.or(Decoder.decodeBigDecimal)

  implicit val abrangenciaDefinidaDecisaoJudicialDecoder =
    decoderForPossiblyWrappedValue(AbrangenciaDefinidaDecisaoJudicial)

  implicit val localidadePessoaDecoder: Decoder[LocalidadePessoa] =
    decoderForPossiblyWrappedValue(LocalidadePessoa)

  implicit val tipoPessoaDecoder: Decoder[TipoPessoa] =
    decoderForPossiblyWrappedValue(TipoPessoa)

  implicit val motivoDecoder: Decoder[Motivo] = decoderForPossiblyWrappedValue(Motivo)

  implicit val modalidadeLicitacaoDecoder: Decoder[ModalidadeLicitacao] =
    decoderForPossiblyWrappedValue(ModalidadeLicitacao)

  implicit val instrumentoLegalDecoder: Decoder[InstrumentoLegal] =
    decoderForPossiblyWrappedValue(InstrumentoLegal)

  private def decoderForPossiblyWrappedValue[A](f: String => A): Decoder[A] =
    Decoder.decodeJsonObject.map(_.apply("descricao")).map {
      case Some(json) => f(json.asString.get)
      case None => throw DecodingFailure("deu ruim", Nil)
    }.or(Decoder.decodeString.map(f))
}
