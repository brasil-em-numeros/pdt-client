package pdt

import java.time.{LocalDate, YearMonth}

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

  case class BPCRequest(mesAno: YearMonth, codigoIbge: String, pagina: Int = 1)

  case class Uf(sigla: String, nome: String)

  case class Tipo(id: Long, descricao: String, descricaoDetalhada: String)

  case class Municipio(codigoIBGE: String,
                       nomeIBGE: String,
                       nomeIBGEsemAcento: Option[String],
                       pais: String,
                       uf: Uf)

  case class BPC(id: Long,
                 dataReferencia: LocalDate,
                 municipio: Municipio,
                 tipo: Tipo,
                 valor: BigDecimal,
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

  case class BolsaFamiliaRequest(mesAno: YearMonth, codigoIbge: String, pagina: Int = 1)

  case class CEAFRequest(cpfSancionado: Option[String] = None,
                         nomeSancionado: Option[String] = None,
                         orgaoLotacao: Option[String] = None,
                         dataPublicacaoInicio: Option[String] = None,
                         dataPublicacaoFim: Option[String] = None,
                         pagina: Int = 1)

  case class CEAF(id: Long,
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

  case class CNAE(classe: String,
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
                    cnae: Option[CNAE],
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

  case class CEISRequest(codigoSancionado: Option[String] = None,
                         nomeSancionado: Option[String] = None,
                         orgaoSancionador: Option[String] = None,
                         dataInicialSancao: Option[String] = None,
                         dataFinalSancao: Option[String] = None,
                         pagina: Int = 1)

  case class CEIS(id: Long,
                  dataReferencia: LocalDate,
                  dataInicioSancao: LocalDate,
                  dataFimSancao: Option[LocalDate],
                  dataPublicacaoSancao: Option[LocalDate],
                  dataTransitadoJulgado: Option[LocalDate],
                  dataOrigemInformacao: LocalDate,
                  tipoSancao: TipoSancao,
                  fonteSancao: FonteSancao,
                  legislacao: Legislacao,
                  orgaoSancionador: OrgaoSancionador,
                  sancionado: Sancionado,
                  pessoa: Pessoa,
                  textoPublicacao: String,
                  linkPublicacao: String,
                  detalhamentoPublicacao: String,
                  numeroProcesso: String,
                  abrangenciaDefinidaDecisaoJudicial: AbrangenciaDefinidaDecisaoJudicial,
                  informacoesAdicionaisDoOrgaoSancionador: String)

  case class TipoSancao(descricaoResumida: String, descricaoPortal: String)

  case class FonteSancao(nomeExibicao: String, telefoneContato: String, enderecoContato: String)

  case class Legislacao(fundamentacaoLegal: String, descricaoFundamentacaoLegal: String)

  case class OrgaoSancionador(nome: String, siglaUf: String, poder: String)

  case class Sancionado(nome: String, codigoFormatado: String)

  case class AbrangenciaDefinidaDecisaoJudicial(descricao: String)

  case class CNEPRequest(cnpjSancionado: Option[String] = None,
                         nomeSancionado: Option[String] = None,
                         orgaoSancionador: Option[String] = None,
                         dataInicialSancao: Option[LocalDate] = None,
                         dataFinalSancao: Option[LocalDate] = None,
                         pagina: Int = 1)

  case class CNEP(id: Long,
                  dataReferencia: LocalDate,
                  dataInicioSancao: LocalDate,
                  dataFimSancao: Option[LocalDate],
                  dataPublicacaoSancao: LocalDate,
                  dataTransitadoJulgado: Option[LocalDate],
                  dataOrigemInformacao: LocalDate,
                  tipoSancao: TipoSancao,
                  fonteSancao: FonteSancao,
                  legislacao: Legislacao,
                  orgaoSancionador: OrgaoSancionador,
                  sancionado: Sancionado,
                  valorMulta: BigDecimal,
                  pessoa: Pessoa,
                  textoPublicacao: String,
                  linkPublicacao: String,
                  detalhamentoPublicacao: String,
                  numeroProcesso: String,
                  abrangenciaDefinidaDecisaoJudicial: AbrangenciaDefinidaDecisaoJudicial,
                  informacoesAdicionaisDoOrgaoSancionador: String)

  case class ContratoRequest private(dataInicial: LocalDate,
                                     dataFinal: LocalDate,
                                     codigoOrgao: String,
                                     pagina: Int)

  object ContratoRequest {
    def apply(dataInicial: LocalDate, dataFinal: LocalDate, orgao: OrgaoSiafi, pagina: Int = 1) =
      new ContratoRequest(dataInicial, dataFinal, orgao.codigo, pagina)
  }

  case class NumeroContratoRequest private(numero: String, pagina: Int = 1)

  case class ContratoPoderExecutivoFederal(id: Int,
                                           contrato: Contrato,
                                           dataAssinatura: LocalDate,
                                           dataFimVigencia: LocalDate,
                                           dataInicioVigencia: LocalDate,
                                           dataPublicacaoDOU: LocalDate,
                                           dimCompra: DimCompra,
                                           fornecedor: Fornecedor,
                                           modalidadeCompra: ModalidadeCompra,
                                           situacaoContrato: SituacaoContrato,
                                           unidadeGestora: UnidadeGestora,
                                           unidadeGestoraCompras: UnidadeGestoraCompras,
                                           valorFinalCompra: BigDecimal,
                                           valorInicialCompra: BigDecimal)

  case class Contrato(fundamentoLegal: String,
                      numero: String,
                      numeroProcesso: String,
                      objeto: String)

  case class DimCompra(contatoResponsavel: String,
                       numero: String,
                       numeroProcesso: String,
                       objeto: String)

  case class Fornecedor(cnae: CNAE,
                        codigoFormatado: String,
                        complementoEndereco: String,
                        dataAbertura: LocalDate,
                        descricaoLogradouro: String,
                        enderecoEletronico: String,
                        localidadePessoa: LocalidadePessoa,
                        municipio: Municipio,
                        naturezaJuridica: NaturezaJuridica,
                        nome: String,
                        nomeBairro: String,
                        nomeFantasiaReceita: String,
                        numeroCEP: String,
                        numeroEndereco: String,
                        numeroInscricaoSocial: String,
                        numeroTelefone: String,
                        pessoaFisica: Boolean,
                        pessoaJuridica: Boolean,
                        razaoSocialReceita: String,
                        tipoCodigo: String,
                        tipoPessoa: TipoPessoa)

  case class ModalidadeCompra(descricao: String)

  case class SituacaoContrato(descricao: String)

  case class UnidadeGestora(codigo: String,
                            descricaoPoder: String,
                            nome: String,
                            nomeSemAcento: String,
                            orgaoMaximo: OrgaoMaximo,
                            orgaoVinculado: OrgaoVinculado)

  case class OrgaoMaximo(codigo: String, nome: String, sigla: String)

  case class OrgaoVinculado(cnpj: String,
                            codigoSIAFI: String,
                            nome: String,
                            nomeOriginal: Option[String],
                            nomeSemAcento: Option[String],
                            sigla: String,
                            siglaOriginal: Option[String],
                            siglaSemAcento: Option[String],
                            naturezaJuridica: Option[NaturezaJuridica])

  case class UnidadeGestoraCompras(codigo: String,
                                   descricaoPoder: String,
                                   existeFatoDespesa: Boolean,
                                   existeFatoReceita: Boolean,
                                   nome: String,
                                   nomeSemAcento: String,
                                   orgaoMaximo: OrgaoMaximo,
                                   orgaoVinculado: OrgaoVinculado)

  case class Apostilamento(dataInclusao: LocalDate,
                           descricao: String,
                           numero: String,
                           situacao: String,
                           valor: BigDecimal)

  case class DocumentosRelacionados(dataEmissao: LocalDate,
                                    empenho: String,
                                    empenhoResumido: String,
                                    observacao: String,
                                    valor: BigDecimal)

  case class TermoAditivo(dataPublicacao:String, numero: Int, objetoAditivo: String)

  case class ConvenioRequest(dataInicial: Option[LocalDate] = None,
                             dataFinal: Option[LocalDate] = None,
                             dataUltimaLiberacaoInicial: Option[LocalDate] = None,
                             dataUltimaLiberacaoFinal: Option[LocalDate] = None,
                             dataVigenciaInicial: Option[LocalDate] = None,
                             dataVigenciaFinal: Option[LocalDate] = None,
                             convenente: Option[String] = None,
                             tipoConvenente: Option[String] = None,
                             numero: Option[String] = None,
                             numeroOriginal: Option[String] = None,
                             codigoOrgao: Option[String] = None,
                             uf: Option[String] = None,
                             codigoIBGE: Option[String] = None,
                             situacao: Option[String] = None,
                             tipoInstrumento: Option[String] = None,
                             funcao: Option[String] = None,
                             subfuncao: Option[String] = None,
                             valorLiberadoDe: Option[BigDecimal] = None,
                             valorLiberadoAte: Option[BigDecimal] = None,
                             valorTotalDe: Option[BigDecimal] = None,
                             valorTotalAte: Option[BigDecimal] = None,
                             pagina: Int = 1)

  case class ConvenioPoderExecutivoFederal(id: Long,
                                           dataReferencia: LocalDate,
                                           dataInicioVigencia: LocalDate,
                                           dataFinalVigencia: LocalDate,
                                           dataPublicacao: LocalDate,
                                           dataUltimaLiberacao: Option[LocalDate],
                                           dataConclusao: Option[LocalDate],
                                           situacao: Situacao,
                                           convenente: Convenente,
                                           localidadePessoa: LocalidadePessoa,
                                           municipioConvenente: Municipio,
                                           orgao: Option[Orgao],
                                           unidadeGestora: UnidadeGestora,
                                           subfuncao: Subfuncao,
                                           tipoInstrumento: Option[TipoInstrumento],
                                           valor: BigDecimal,
                                           valorLiberado: BigDecimal,
                                           valorContrapartida:BigDecimal,
                                           valorDaUltimaLiberacao: BigDecimal,
                                           convenio: Convenio)

  case class Situacao(codigo: String, descricao: String)

  case class Convenente(numeroInscricaoSocial: String,
                        nome: String,
                        razaoSocialReceita: String,
                        nomeFantasiaReceita: String,
                        cnae: CNAE,
                        municipio: Municipio,
                        localidadePessoa: Option[LocalidadePessoa],
                        naturezaJuridica: NaturezaJuridica,
                        dataAbertura: Option[LocalDate],
                        enderecoEletronico: String,
                        numeroTelefone: String,
                        descricaoLogradouro: String,
                        numeroEndereco: String,
                        complementoEndereco: String,
                        numeroCEP: String,
                        nomeBairro: String,
                        tipoCodigo: String,
                        codigoFormatado: String,
                        tipoPessoa: TipoPessoa)

  case class Orgao(cnpj: String,
                   codigoSIAFI: String,
                   descricaoPoder: String,
                   nome: String,
                   orgaoMaisRecente: Boolean,
                   orgaoMaximo: OrgaoMaximo,
                   sigla: String)

  case class Subfuncao(id: Long,
                       funcao: Funcao,
                       subFuncao: SubFuncao0,
                       descricaoSubFuncaoOriginal: String,
                       subfuncaoMaisRecente: Boolean)

  case class Funcao(codigo: String, descricao: String, slug: String)

  case class SubFuncao0(codigo: String, descricao: String)

  case class TipoInstrumento(id: Long, codigo: String, descricao: String)

  case class Convenio(objeto: String, numero: Long, numeroOriginal: String)

  case class NumeroConvenioRequest(numero: Long, pagina: Int = 1)

  case class NumeroOriginalRequest(numeroOriginal: String, pagina: Int = 1)
}
