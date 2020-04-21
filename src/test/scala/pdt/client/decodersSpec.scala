package pdt.client

import java.time.{LocalDate, YearMonth}

import io.circe.parser.decode
import pdt.client.decoders._
import pdt.domain._
import zio.test.Assertion._
import zio.test._

object decodersSpec extends DefaultRunnableSpec {
  def spec =
    suite("All custom decoders")(
      localDateDecoderSuite,
      yearMonthDecoderSuite,
      bigDecimalDecoderSuite,
      descricaoDecoderSuite)

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
    test("decodes YearMonth from integer yyyyMM") {
      val decoded = decode(""" 202001 """)(yearMonthDecoder)
      val expected = YearMonth.of(2020, 1)

      assert(decoded)(isRight(equalTo(expected)))
    },
    test("decodes YearMonth from String M``/yyyy") {
      val decoded = decode(""" "01/2020" """)(yearMonthDecoder)
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

  val descricaoDecoderSuite = suite("Descricao decoder")(
    test("decodes Descricao from string") {
      val decoded = decode(""" "valor" """)(descricaoDecoder)
      assert(decoded)(isRight(equalTo(Descricao("valor"))))
    },
    test("decodes Descricao from object") {
      val decoded = decode(""" { "descricao" : "valor" } """)(descricaoDecoder)
      assert(decoded)(isRight(equalTo(Descricao("valor"))))
    }
  )
}
