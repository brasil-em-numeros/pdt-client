package pdt.client

import java.time.{LocalDate, YearMonth}

import io.circe.parser.decode
import pdt.client.decoders._
import pdt.domain._
import zio.test.Assertion._
import zio.test.Eql.eqlReflexive
import zio.test._

object decodersSpec extends DefaultRunnableSpec {
  def spec =
    suite("All custom decoders")(
      localDateDecoderSuite,
      yearMonthDecoderSuite,
      bigDecimalDecoderSuite,
      possiblyWrappedValueDecoderSuite)

  val localDateDecoderSuite = suite("LocalDate decoder")(
    test("decodes LocalDate") {
      val decoded = decode(""" "15/01/2020" """)(localDateDecoder)
      val expected = LocalDate.of(2020, 1, 15)

      assert(decoded)(isRight(equalTo(expected)))
    },
    test("generates LocalDate(0000, 1, 1) for invalid json") {
      val decoded = decode(""" "INVALID" """)(localDateDecoder)
      val expected = LocalDate.of(0, 1, 1)

      assert(decoded)(isRight(equalTo(expected)))
    }
  )

  val yearMonthDecoderSuite = suite("YearMonth decoder")(
    test("decodes YearMonth from integer") {
      val decoded = decode("202001")(yearMonthDecoder)
      val expected = YearMonth.of(2020, 1)

      assert(decoded)(isRight(equalTo(expected)))
    }
  )

  val bigDecimalDecoderSuite = suite("BigDecimal decoder")(
    test("decodes doubles") {
      val decoded = decode(""" 1.1 """)(bigDecimalDecoder)
      assert(decoded)(isRight(equalTo(BigDecimal(1.1))))
    },
    test("decodes string number in brazilian format") {
      val decoded = decode(""" "9.999,99" """)(bigDecimalDecoder)
      assert(decoded)(isRight(equalTo(BigDecimal(9999.99))))
    }
  )

  val possiblyWrappedValueDecoderSuite = suite("Possibly Wrapped Value decoder")(
    test("decodes AbrangenciaDefinidaDecisaoJudicial from string") {
      val decoded = decode(""" "valor" """)(abrangenciaDefinidaDecisaoJudicialDecoder)
      assert(decoded)(isRight(equalTo(AbrangenciaDefinidaDecisaoJudicial("valor"))))
    },
    test("decodes AbrangenciaDefinidaDecisaoJudicial from object") {
      val decoded = decode(""" { "descricao" : "valor" } """)(abrangenciaDefinidaDecisaoJudicialDecoder)
      assert(decoded)(isRight(equalTo(AbrangenciaDefinidaDecisaoJudicial("valor"))))
    },
    test("decodes LocalidadePessoa from string") {
      val decoded = decode(""" "valor" """)(localidadePessoaDecoder)
      assert(decoded)(isRight(equalTo(LocalidadePessoa("valor"))))
    },
    test("decodes LocalidadePessoa from object") {
      val decoded = decode(""" { "descricao" : "valor" } """)(localidadePessoaDecoder)
      assert(decoded)(isRight(equalTo(LocalidadePessoa("valor"))))
    },
    test("decodes TipoPessoa from string") {
      val decoded = decode(""" "valor" """)(tipoPessoaDecoder)
      assert(decoded)(isRight(equalTo(TipoPessoa("valor"))))
    },
    test("decodes TipoPessoa from object") {
      val decoded = decode(""" { "descricao" : "valor" } """)(tipoPessoaDecoder)
      assert(decoded)(isRight(equalTo(TipoPessoa("valor"))))
    }
  )
}
