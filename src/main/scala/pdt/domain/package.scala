package pdt

import java.time.{LocalDate, YearMonth}

package object domain {

  case class OrgaoRequest(codigo: Option[String], descricao: Option[String], pagina: Int = 1)

  object OrgaoRequest {
    def apply(pagina: Integer): OrgaoRequest = new OrgaoRequest(None, None, pagina)
  }

  case class OrgaoSiafi(codigo: String, codigoDescricaoFormatado: String, descricao: String)

  case class OrgaoSiape(codigo: String, codigoDescricaoFormatado: String, descricao: String)

  case class AcordoLenienciaRequest(cnpjSancionado: Option[String] = None,
                                    nomeSancionado: Option[String] = None,
                                    situacao: Option[String] = None,
                                    dataInicialSancao: Option[LocalDate] = None,
                                    dataFinalSancao: Option[LocalDate] = None,
                                    pagina: Int = 1)

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

  case class MunicipioRequest(mesAno: YearMonth, codigoIbge: String, pagina: Int = 1)

  case class Uf(sigla: String, nome: String)

  case class Tipo(id: Long, descricao: String, descricaoDetalhada: Option[String])

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
                  tipoPunicao: Descricao,
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

  case class Descricao(descricao: String)

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

  case class NaturezaJuridica(codigo: String, codigoTipo: String, descricao: String, descricaoTipo: String)

  case class Pessoa(nome: String,
                    numeroInscricaoSocial: String,
                    razaoSocialReceita: String,
                    nomeFantasiaReceita: String,
                    cnae: Option[CNAE],
                    municipio: Municipio,
                    localidadePessoa: Option[Descricao],
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
                    tipoPessoa: Option[Descricao])

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
                  abrangenciaDefinidaDecisaoJudicial: Descricao,
                  informacoesAdicionaisDoOrgaoSancionador: String)

  case class TipoSancao(descricaoResumida: String, descricaoPortal: String)

  case class FonteSancao(nomeExibicao: String, telefoneContato: String, enderecoContato: String)

  case class Legislacao(fundamentacaoLegal: String, descricaoFundamentacaoLegal: String)

  case class OrgaoSancionador(nome: String, siglaUf: String, poder: String)

  case class Sancionado(nome: String, codigoFormatado: String)

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
                  abrangenciaDefinidaDecisaoJudicial: Descricao,
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
                                           modalidadeCompra: Descricao,
                                           situacaoContrato: Descricao,
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
                        localidadePessoa: Descricao,
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
                        tipoPessoa: Descricao)

  case class UnidadeGestora(codigo: String,
                            descricaoPoder: String,
                            nome: String,
                            nomeSemAcento: String,
                            orgaoMaximo: OrgaoMaximo,
                            orgaoVinculado: OrgaoVinculado,
                            existeFatoDespesa: Option[Boolean],
                            existeFatoReceita: Option[Boolean])

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

  case class TermoAditivo(dataPublicacao: String, numero: Int, objetoAditivo: String)

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
                                           localidadePessoa: Descricao,
                                           municipioConvenente: Municipio,
                                           orgao: Option[Orgao],
                                           unidadeGestora: UnidadeGestora,
                                           subfuncao: Subfuncao,
                                           tipoInstrumento: Option[TipoInstrumento],
                                           valor: BigDecimal,
                                           valorLiberado: BigDecimal,
                                           valorContrapartida: BigDecimal,
                                           valorDaUltimaLiberacao: BigDecimal,
                                           convenio: Convenio)

  case class Situacao(codigo: String, descricao: String)

  case class Convenente(numeroInscricaoSocial: String,
                        nome: String,
                        razaoSocialReceita: String,
                        nomeFantasiaReceita: String,
                        cnae: CNAE,
                        municipio: Municipio,
                        localidadePessoa: Option[Descricao],
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
                        tipoPessoa: Descricao)

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

  case class DocumentoRequest(unidadeGestora: Option[String] = None,
                              gestao: Option[String] = None,
                              dataEmissao: LocalDate,
                              fase: Int,
                              pagina: Int = 1)

  case class FavorecidoRequest(codigoPessoa: String,
                               fase: Int,
                               ano: Int,
                               ug: Option[String],
                               gestao: Option[String],
                               pagina: Int = 1)

  case class Documento(acao: String,
                       categoria: String,
                       codigoFavorecido: String,
                       data: String,
                       documento: String,
                       documentoResumido: String,
                       elemento: String,
                       especie: String,
                       fase: String,
                       favorecido: String,
                       funcao: String,
                       grupo: String,
                       localizadorGasto: String,
                       modalidade: String,
                       nomeFavorecido: String,
                       numeroProcesso: String,
                       observacao: String,
                       orgao: String,
                       orgaoSuperior: String,
                       programa: String,
                       subfuncao: String,
                       subtitulo: String,
                       ufFavorecido: String,
                       ug: String,
                       uo: String,
                       valor: String)

  case class DocumentoRelacionadoRequest(codigoDocumento: String, fase: Int)

  case class DocumentoRelacionado(data: String,
                                  documento: String,
                                  documentoResumido: String,
                                  elementoDespesa: String,
                                  especie: String,
                                  fase: String,
                                  favorecido: String,
                                  orgaoSuperior: String,
                                  orgaoVinculado: String,
                                  unidadeGestora: String,
                                  valor: String)

  case class DespesasPorOrgaoRequest(ano: Int,
                                     orgaoSuperior: Option[String] = None,
                                     orgao: Option[String] = None,
                                     pagina: Int = 1)

  case class Despesa(ano: Int,
                     orgao: String,
                     codigoOrgao: String,
                     orgaoSuperior: String,
                     codigoOrgaoSuperior: String,
                     empenhado: BigDecimal,
                     liquidado: BigDecimal,
                     pago: BigDecimal)

  case class EmpenhoImpactadoRequest(codigoDocumento: String, fase: Int, pagina: Int = 1)

  case class EmpenhoImpactado(empenho: String,
                              empenhoResumido: String,
                              subitem: String,
                              valorLiquidado: String,
                              valorPago: String,
                              valorRestoCancelado: String,
                              valorRestoInscrito: String,
                              valorRestoPago: String)

  case class FavorecidoPorDocumentoRequest(codigoDocumento: String, pagina: Int = 1)

  case class FavorecidoFinal(codigoFavorecidoDespesa: String,
                             codigoFavorecidoFinal: String,
                             codigoListaCredor: String,
                             codigoOrgaoSuperior: String,
                             codigoOrgaoVinculado: String,
                             codigoPagamento: String,
                             codigoUnidadeGestora: String,
                             dataCarga: String,
                             municipioFavorecidoFinal: String,
                             nomeFavorecidoDespesa: String,
                             nomeFavorecidoFinal: String,
                             orgaoSuperior: String,
                             orgaoVinculado: String,
                             skFatDW: Int,
                             skPessoaDespesa: Int,
                             skPessoaFinal: Int,
                             tipoDocumento: String,
                             tipoFavorecidoDespesa: String,
                             tipoFavorecidoFinal: String,
                             tipoOB: String,
                             ufFavorecidoFinal: String,
                             unidadeGestora: String,
                             valorFinal: String)

  case class FuncionalProgramaticaRequest(ano: Int,
                                          funcao: Option[String] = None,
                                          subfuncao: Option[String] = None,
                                          programa: Option[String] = None,
                                          acao: Option[String] = None,
                                          pagina: Int = 1)

  case class FuncionalProgramatica(acao: String,
                                   ano: Int,
                                   codigoAcao: String,
                                   codigoFuncao: String,
                                   codigoPrograma: String,
                                   codigoSubfuncao: String,
                                   empenhado: String,
                                   funcao: String,
                                   liquidado: String,
                                   pago: String,
                                   programa: String,
                                   subfuncao: String)

  case class RecursoRecebidoRequest(mesAnoInicio: YearMonth,
                                    mesAnoFim: YearMonth,
                                    nomeFavorecido: Option[String] = None,
                                    codigoFavorecido: Option[String] = None,
                                    tipoFavorecido: Option[String] = None,
                                    uf: Option[String] = None,
                                    codigoIBGE: Option[String] = None,
                                    orgaoSuperior: Option[String] = None,
                                    orgao: Option[String] = None,
                                    unidadeGestora: Option[String] = None,
                                    pagina: Int = 1)

  case class RecursoRecebido(anoMes: YearMonth,
                             codigoFormatado: String,
                             codigoOrgao: String,
                             codigoOrgaoSuperior: String,
                             codigoUG: String,
                             municipioPessoa: String,
                             nomeOrgao: String,
                             nomeOrgaoSuperior: String,
                             nomePessoa: String,
                             nomeUG: String,
                             siglaUFPessoa: String,
                             tipoPessoa: String,
                             valor: BigDecimal)

  case class SubitemDeEmpenho(descricao: String,
                              quantidade: Int,
                              subitem: String,
                              valorTotal: BigDecimal,
                              valorUnitario: BigDecimal)

  case class EmendaParlamentarRequest(codigoEmenda: Option[String] = None,
                                      numeroEmenda: Option[String] = None,
                                      nomeAutor: Option[String] = None,
                                      ano: Option[Int] = None,
                                      codigoFuncao: Option[String] = None,
                                      codigoSubfuncao: Option[String] = None,
                                      pagina: Int = 1)

  case class EmendaParlamentar(ano: Int,
                               autor: String,
                               codigoEmenda: String,
                               funcao: String,
                               localidadeDoGasto: String,
                               nomeAutor: Option[String],
                               numeroEmenda: String,
                               subfuncao: String,
                               valorEmpenhado: BigDecimal,
                               valorLiquidado: BigDecimal,
                               valorPago: BigDecimal,
                               valorRestoAPagar: BigDecimal,
                               valorRestoCancelado: BigDecimal,
                               valorRestoInscrito: BigDecimal,
                               valorRestoPago: BigDecimal)

  case class EmendaDocumento(codigoDocumento: String,
                             codigoDocumentoResumido: String,
                             data: LocalDate,
                             especieTipo: String,
                             fase: String)

  case class CEPIMRequest(cnpjSancionado: Option[String] = None,
                          nomeSancionado: Option[String] = None,
                          ufSancionado: Option[String] = None,
                          orgaoEntidade: Option[String] = None,
                          pagina: Int = 1)

  case class CEPIM(id: Long,
                   dataReferencia: LocalDate,
                   motivo: Descricao,
                   orgaoSuperior: OrgaoSuperior,
                   pessoaJuridica: Pessoa,
                   convenio: ConvenioCEPIM,
                   codigoConvenio: Option[String])

  case class OrgaoSuperior(nome: String,
                           codigoSIAFI: String,
                           cnpj: String,
                           sigla: String,
                           descricaoPoder: String,
                           orgaoMaisRecente: Option[Boolean],
                           orgaoMaximo: OrgaoMaximo)

  case class ConvenioCEPIM(codigo: String, objeto: String, numero: String)

  case class BeneficioSafra(id: Long,
                            beneficiarioSafra: BeneficiarioSafra,
                            dataMesReferencia: LocalDate,
                            municipio: Municipio,
                            valor: BigDecimal)

  case class BeneficiarioSafra(cpfFormatado: String,
                               multiploCadastro: Boolean,
                               nis: String,
                               nome: String)

  case class Safra(id: Long,
                   dataReferencia: LocalDate,
                   municipio: Municipio,
                   quantidadeBeneficiados: Int,
                   tipo: Tipo,
                   valor: BigDecimal)

  case class GastoRequest(mesExtratoInicio: Option[YearMonth] = None,
                          mesExtratoFim: Option[YearMonth] = None,
                          dataTransacaoInicio: Option[LocalDate] = None,
                          dataTransacaoFim: Option[LocalDate] = None,
                          tipoCartao: Option[Int] = None,
                          codigoOrgao: Option[String] = None,
                          cpfPortador: Option[String] = None,
                          cpfCnpjFavorecido: Option[String] = None,
                          valorDe: Option[BigDecimal] = None,
                          valorAte: Option[BigDecimal] = None,
                          pagina: Int = 1)

  case class Gasto(id: Long,
                   mesExtrato: YearMonth,
                   dataTransacao: LocalDate,
                   valorTransacao: BigDecimal,
                   tipoCartao: TipoCartao,
                   estabelecimento: Pessoa,
                   unidadeGestora: GastoUnidadeGestora,
                   portador: Portador)

  case class TipoCartao(id: Long, codigo: String, descricao: String)

  case class GastoUnidadeGestora(codigo: String,
                                 nome: String,
                                 orgaoVinculado: GastoOrgaoVinculado)

  case class GastoOrgaoVinculado(nome: String,
                                 codigoSIAFI: String,
                                 cnpj: String,
                                 sigla: String,
                                 descricaoPoder: String,
                                 orgaoMaximo: OrgaoMaximo)

  case class Portador(codigoFormatado: String, nome: String)

  case class LicitacaoRequest(dataInicial: LocalDate,
                              dataFinal: LocalDate,
                              codigoOrgao: String,
                              pagina: Int = 1)

  case class LicitacaoPoderExecutivoFederal(id: Long,
                                            dataResultadoCompra: LocalDate,
                                            dataAbertura: LocalDate,
                                            dataReferencia: LocalDate,
                                            dataPublicacao: LocalDate,
                                            situacaoCompra: SituacaoCompra,
                                            modalidadeLicitacao: Descricao,
                                            instrumentoLegal: Option[Descricao],
                                            valor: BigDecimal,
                                            municipio: Municipio,
                                            unidadeGestora: UnidadeGestora,
                                            licitacao: Licitacao)

  case class SituacaoCompra(codigo: Long, descricao: String)

  case class Licitacao(numero: String,
                       objeto: String,
                       numeroProcesso: String,
                       contatoResponsavel: String)

  case class UASGModalidadeRequest(codigoUASG: String, numero: String, codigoModalidade: String)

  case class Empenho(dataEmissao: LocalDate,
                     empenho: String,
                     empenhoResumido: String,
                     observacao: String,
                     valor: BigDecimal)

  case class Modalidade(codigo: String, codigoDescricaoFormatado: String, descricao: String)

  case class UASG(codigo: String, nome: String, nomeOrgao: String)

  case class PETI(id: Long,
                  dataReferencia: LocalDate,
                  municipio: Municipio,
                  quantidadeBeneficiados: Int,
                  tipo: Tipo,
                  valor: BigDecimal)

  case class SeguroDefeso(id: Long,
                          dataReferencia: LocalDate,
                          municipio: Municipio,
                          quantidadeBeneficiados: Int,
                          tipo: Tipo,
                          valor: BigDecimal)

  case class ServidorFederalRequest(tipoServidor: Option[Int] = None,
                                    codigoSituacaoVinculo: Option[String] = None,
                                    cpf: Option[String] = None,
                                    nome: Option[String] = None,
                                    codigoFuncaoCargo: Option[String] = None,
                                    orgaoServidorExercicio: Option[String] = None,
                                    orgaoServidorLotacao: Option[String] = None,
                                    pagina: Int = 1)

  case class ServidorFederal(servidor: Servidor,
                             fichasCargoEfetivo: List[Ficha],
                             fichasFuncao: List[Ficha],
                             fichasMilitar: List[Ficha],
                             fichasDemaisSituacoes: List[Ficha])

  case class Servidor(id: Long,
                      pessoa: Pessoa,
                      situacao: Situacao,
                      orgaoServidorLotacao: Option[OrgaoServidor],
                      orgaoServidorExercicio: Option[OrgaoServidor],
                      estadoExercicio: EstadoExercicio,
                      tipoServidor: Tipo,
                      funcao: FuncaoServidor,
                      matriculaFormatada: String)

  case class OrgaoServidor(codigo: String,
                           nome: String,
                           sigla: String,
                           idOrgaoVinculado: Long,
                           codigoOrgaoVinculado: String,
                           nomeOrgaoVinculado: String)

  case class EstadoExercicio(codigoIBGE: String, codigoCNPJEstado: String, populacao: Long, uf: Uf)

  case class FuncaoServidor(id: Long,
                            sigla: String,
                            descricao: String,
                            nivel: String,
                            codigoAtividade: String)

  case class Ficha(nome: String,
                   cpfDescaracterizado: String,
                   matriculaDescaracterizada: String,
                   dataPublicacaoDocumentoIngressoServicoPublico: Option[LocalDate],
                   diplomaLegal: String,
                   jornadaTrabalho: String,
                   regimeJuridico: String,
                   situacaoServidor: String,
                   afastamentos: List[String],
                   orgaoSuperiorLotacao: String,
                   orgaoLotacao: String,
                   uorgLotacao: String,
                   orgaoServidorLotacao: String,
                   dataIngressoOrgao: LocalDate,
                   dataIngressoServicoPublico: Option[LocalDate],
                   orgaoSuperiorExercicio: String,
                   orgaoExercicio: String,
                   orgaoServidorExercicio: String,
                   uorgExercicio: String,
                   cargo: String,
                   classeCargo: String,
                   padraoCargo: String,
                   nivelCargo: String,
                   dataIngressoCargo: LocalDate,
                   formaIngresso: String,
                   ufExercicio: String)

  case class FuncaoCargo(codigoFuncaoCargo: String, descricaoFuncaoCargo: String)

  case class ServidorPorOrgaoRequest(orgaoLotacao: Option[String] = None,
                                     orgaoExercicio: Option[String] = None,
                                     tipoServidor: Option[Int] = None,
                                     tipoVinculo: Option[Int] = None,
                                     licenca: Option[Int] = None,
                                     pagina: Int = 1)

  case class ServidorPorOrgao(qntPessoas: Int,
                              qntVinculos: Int,
                              skTipoVinculo: Int,
                              descTipoVinculo: String,
                              skTipoServidor: Int,
                              descTipoServidor: String,
                              licenca: Int,
                              codOrgaoExercicioSiape: String,
                              nomOrgaoExercicioSiape: String,
                              codOrgaoSuperiorExercicioSiape: String,
                              nomOrgaoSuperiorExercicioSiape: String)

  case class RemuneracaoRequest(cpf: String, mesAno: YearMonth, pagina: Int = 1)

  case class RemuneracaoServidorFederal(remuneracoesDTO: List[Remuneracao], servidor: Servidor)

  case class Remuneracao(abateGratificacaoNatalina: String,
                         abateGratificacaoNatalinaDolar: String,
                         abateRemuneracaoBasicaBruta: String,
                         abateRemuneracaoBasicaBrutaDolar: String,
                         ferias: String,
                         feriasDolar: String,
                         fundoSaude: String,
                         fundoSaudeDolar: String,
                         gratificacaoNatalina: String,
                         gratificacaoNatalinaDolar: String,
                         honorariosAdvocaticios: List[HonorarioAdvocaticio],
                         impostoRetidoNaFonte: String,
                         impostoRetidoNaFonteDolar: String,
                         jetons: List[Jetom],
                         mesAno: YearMonth,
                         mesAnoPorExtenso: String,
                         observacoes: List[String],
                         outrasDeducoesObrigatorias: String,
                         outrasDeducoesObrigatoriasDolar: String,
                         outrasRemuneracoesEventuais: String,
                         outrasRemuneracoesEventuaisDolar: String,
                         pensaoMilitar: String,
                         pensaoMilitarDolar: String,
                         previdenciaOficial: String,
                         previdenciaOficialDolar: String,
                         remuneracaoBasicaBruta: String,
                         remuneracaoBasicaBrutaDolar: String,
                         rubricas: List[Rubrica],
                         skMesReferencia: String,
                         taxaOcupacaoImovelFuncional: String,
                         taxaOcupacaoImovelFuncionalDolar: String,
                         valorTotalHonorariosAdvocaticios: String,
                         valorTotalJetons: String,
                         valorTotalRemuneracaoAposDeducoes: String,
                         valorTotalRemuneracaoDolarAposDeducoes: String,
                         verbasIndenizatorias: String,
                         verbasIndenizatoriasCivil: String,
                         verbasIndenizatoriasCivilDolar: String,
                         verbasIndenizatoriasDolar: String,
                         verbasIndenizatoriasMilitar: String,
                         verbasIndenizatoriasMilitarDolar: String,
                         verbasIndenizatoriasReferentesPDV: String,
                         verbasIndenizatoriasReferentesPDVDolar: String,
                        )

  case class HonorarioAdvocaticio(mesReferencia: String,
                                  skMesReferencia: String,
                                  valor: BigDecimal,
                                  valorFormatado: String)

  case class Jetom(descricao: String,
                   skMesReferencia: String,
                   valor: BigDecimal,
                   valorFormatado: String,
                   valorJetom: String)

  case class Rubrica(codigo: String,
                     descricao: String,
                     skMesReferencia: String,
                     valor: BigDecimal,
                     valorDolar: BigDecimal,
                     valorFormatado: String)

}