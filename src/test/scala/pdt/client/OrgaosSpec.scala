package pdt.client

import pdt.client.HttpClient.HttpClient
import pdt.client.MockHttpClient.Get
import pdt.domain.OrgaoSiafi
import zio.ULayer
import zio.test.Assertion._
import zio.test.mock.Expectation._
import zio.test._

object OrgaosSpec extends DefaultRunnableSpec {
  val mockEnv: ULayer[HttpClient] =
    Get[OrgaoSiafi]()(equalTo("orgaos-siafi", Map("pagina" -> "1"))) returns value(Nil)

  def spec = suite("Orgaos Spec")(
    testM("siafi") {
      val result = Orgaos.siafi.provideLayer(mockEnv)
      assertM(result)(isEmpty)
    }
  )
}
