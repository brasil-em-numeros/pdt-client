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

  case class Cpf(codigo: String, pagina: Int = 1)

  case class Nis(codigo: String, pagina: Int = 1)

  case class BeneficioPrestacaoContinuadaRequest(mesAno: String, codigoIbge: String, pagina: Int = 1)

  case class Uf(sigla: String, nome: String)

  case class Tipo(id: Long, descricao: String, descricaoDetalhada: String)

  case class Municipio(codigoIBGE: String,
                       nomeIBGE: String,
                       nomeIBGEsemAcento: String,
                       pais: String,
                       uf: Uf)

  case class BeneficioPrestacaoContinuada(id: Long,
                                          dataReferencia: LocalDate,
                                          municipio: Municipio,
                                          tipo: Tipo,
                                          valor: Double,
                                          quantidadeBeneficiados: Long)

  case class DisponivelPorCpf(codigo: String,
                              anoMesReferencia: Option[String] = None,
                              anoMesCompetencia: Option[String] = None,
                              pagina: Int = 1)

  case class DisponivelPorNis(codigo: String,
                              anoMesReferencia: Option[String] = None,
                              anoMesCompetencia: Option[String] = None,
                              pagina: Int = 1)

  case class SacadoPorNis(nis: String,
                          anoMesReferencia: Option[String] = None,
                          anoMesCompetencia: Option[String] = None,
                          pagina: Int = 1)

  case class BolsaFamilia(id: Long,
                          dataReferencia: LocalDate,
                          municipio: Municipio,
                          tipo: Tipo,
                          valor: Double,
                          quantidadeBeneficiados: Long)

  case class BolsaFamiliaRequest(mesAno: String, codigoIbge: String, pagina: Int = 1)

  case class CeafRequest(cpfSancionado: Option[String] = None,
                         nomeSancionado: Option[String] = None,
                         orgaoLotacao: Option[String] = None,
                         dataPublicacaoInicio: Option[String] = None,
                         dataPublicacaoFim: Option[String] = None,
                         pagina: Int = 1)

  case class Ceaf(id: Long,
                  dataPublicacao: LocalDate,
                  dataReferencia: LocalDate,
                  punicao: Punicao,
                  tipoPunicao: TipoPunicao,
                  pessoa: Pessoa,
                  orgaoLotacao: OrgaoLotacao,
                  ufLotacaoPessoa: UfLotacaoPessoa,
                  cargoEfetivo: String,
                  codigoCargoComissao: String,
                  cargoComissao: String,
                  fundamentacao: List[Fundamentacao])

  case class Punicao(nomePunido: String,
                     portaria: String,
                     processo: String,
                     paginaDOU: String,
                     secaoDOU: Int,
                     cpfPunidoFormatado: String)

  case class TipoPunicao(descricao: String)

  case class Cnae(classe: String,
                  codigoClasse: String,
                  codigoDivisao: String,
                  codigoGrupo: String,
                  codigoSecao: String,
                  codigoSubclasse: String,
                  divisao: String,
                  grupo: String,
                  secao: String,
                  subclasse: String)

  case class LocalidadePessoa(descricao: String)

  case class NaturezaJuridica(codigo: String, codigoTipo: String, descricao: String, descricaoTipo: String)

  case class TipoPessoa(descricao: String)

  case class Pessoa(nome: String,
                    numeroInscricaoSocial: String,
                    razaoSocialReceita: String,
                    nomeFantasiaReceita: String,
                    cnae: Option[Cnae],
                    municipio: Municipio,
                    localidadePessoa: Option[LocalidadePessoa],
                    naturezaJuridica: Option[NaturezaJuridica],
                    dataAbertura: Option[LocalDate],
                    enderecoEletronico: String,
                    numeroTelefone: String,
                    descricaoLogradouro: String,
                    numeroEndereco: String,
                    complementoEndereco: String,
                    numeroCEP: String,
                    nomeBairro: String,
                    codigoFormatado: String,
                    tipoCodigo: String,
                    tipoPessoa: Option[TipoPessoa])

  case class OrgaoLotacao(siglaDaPasta: String, sigla: String, nome: String, nomeSemAcento: String)

  case class UfLotacaoPessoa(codigoIBGE: String, codigoCNPJEstado: String, populacao: Long, uf: Uf)

  case class Fundamentacao(codigo: String, descricao: String)
}
