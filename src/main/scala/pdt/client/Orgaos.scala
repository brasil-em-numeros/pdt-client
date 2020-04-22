package pdt.client

import io.circe.generic.auto._
import pdt.domain._
import pdt.http.HttpClient.{Response, get}
import pdt.http.implicits.HttpRequestOps
import zio.ZIO

object Orgaos {

  def siafi(request: OrgaoRequest): Response[List[OrgaoSiafi]] =
    get[OrgaoSiafi]("orgaos-siafi", request.parameters)

  def siafi: Response[List[OrgaoSiafi]] = {
    val tasks = (1 to 42).map(p => siafi(OrgaoRequest(p)))

    ZIO.collectAllPar(tasks)
      .foldM(
        ZIO.fail(_),
        a => ZIO.succeed(a.flatten))
  }

  def siape(request: OrgaoRequest): Response[List[OrgaoSiape]] =
    get[OrgaoSiape]("orgaos-siape", request.parameters)

  def siape: Response[List[OrgaoSiape]] = {
    val tasks = (1 to 42).map(p => siape(OrgaoRequest(p)))

    ZIO.collectAllPar(tasks)
      .foldM(
        ZIO.fail(_),
        a => ZIO.succeed(a.flatten))
  }
}
