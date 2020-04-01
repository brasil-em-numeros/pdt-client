package pdt.client

import io.circe.generic.auto._
import pdt.client.HttpClient.{HttpClient, get}
import pdt.domain.{OrgaoRequest, OrgaoSiafi, OrgaoSiape}
import pdt.implicits.HttpRequestOps
import zio.{RIO, ZIO}

object Orgaos {

  def siafi(request: OrgaoRequest): RIO[HttpClient, List[OrgaoSiafi]] =
    get[OrgaoSiafi]("orgaos-siafi", request.parameters)

  def siafi: RIO[HttpClient, List[OrgaoSiafi]] = {
    val tasks = (1 to 42).map(p => siafi(OrgaoRequest(p)))

    ZIO.collectAllPar(tasks)
      .foldM(
        ZIO.fail(_),
        a => ZIO.succeed(a.flatten))
  }

  def siape(request: OrgaoRequest): RIO[HttpClient, List[OrgaoSiape]] =
    get[OrgaoSiape]("orgaos-siape", request.parameters)

  def siape: RIO[HttpClient, List[OrgaoSiape]] = {
    val tasks = (1 to 42).map(p => siape(OrgaoRequest(p)))

    ZIO.collectAllPar(tasks)
      .foldM(
        ZIO.fail(_),
        a => ZIO.succeed(a.flatten))
  }
}
