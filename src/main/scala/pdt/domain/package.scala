package pdt

import java.time.LocalDate

package object domain {

  case class OrgaoRequest(codigo: Option[String], descricao: Option[String], pagina: Int = 1)

  object OrgaoRequest {
    def apply(pagina: Integer): OrgaoRequest = new OrgaoRequest(None, None, pagina)
  }

  case class OrgaoSiafi(codigo: String, codigoDescricaoFormatado: String, descricao: String)

  case class OrgaoSiape(codigo: String, codigoDescricaoFormatado: String, descricao: String)

  case class AcordoLenienciaRequest(cnpjSancionado: Option[String],
                                    nomeSancionado: Option[String],
                                    situacao: Option[String],
                                    dataInicialSancao: Option[LocalDate],
                                    dataFinalSancao: Option[LocalDate],
                                    pagina: Int = 1)

  object AcordoLenienciaRequest {
    def apply(pagina: Integer): AcordoLenienciaRequest =
      new AcordoLenienciaRequest(None, None, None, None, None, pagina)
  }

  case class AcordoLeniencia(id: Long,
                             nomeEmpresa: String,
                             dataInicioAcordo: LocalDate,
                             dataFimAcordo: LocalDate,
                             orgaoResponsavel: String,
                             cnpj: String,
                             razaoSocial: String,
                             nomeFantasia: String,
                             ufEmpresa: String,
                             situacaoAcordo: String,
                             quantidade: Int)
}
