package br.unioeste.sisra.controle.validacao;

import br.unioeste.sisra.modelo.execao.ValidacaoException;
import br.unioeste.sisra.modelo.to.FuncionarioTO;
import br.unioeste.sisra.utils.DataUtils;
import br.unioeste.sisra.utils.NumericoUtils;

/**
 *
 * @author Tharle J. Camargo
 */
public class FuncionarioValidacao extends Validacao {

    public static final String CHAVE_CAMPO_FUNCIONARIO_ID = "idFuncionario";
    public static final String CHAVE_CAMPO_FUNCIONARIO_NOME = "nomeFuncionario";
    public static final String CHAVE_CAMPO_FUNCIONARIO_RUA = "ruaFuncionario";
    public static final String CHAVE_CAMPO_FUNCIONARIO_CIDADE = "cidadeFuncionario";
    public static final String CHAVE_CAMPO_FUNCIONARIO_CPF = "cpfFuncionario";
    public static final String CHAVE_CAMPO_FUNCIONARIO_DT_NASCIMENTO = "dtNascimentoFuncionario";
    public static final String CHAVE_CAMPO_FUNCIONARIO_DT_CONTRATO = "dtContratoFuncionario";
    public static final String CHAVE_CAMPO_FUNCIONARIO_DT_DEMISSAO = "dtDemissaoFuncionario";
    public static final String CHAVE_CAMPO_FUNCIONARIO_NUMERO_RESIDENCIA = "numResidenciaFuncionario";

    @Override
    public void validar(Object to) throws ValidacaoException {
        if (to == null) {
            throw new ValidacaoException("", "Funcionario nulo");
        }

        if (!(to instanceof FuncionarioTO)) {
            throw new ValidacaoException("", "Não é tipo funcionario");
        }

        FuncionarioTO funcionario = (FuncionarioTO) to;

        if (funcionario.getId() == null) {
            throw new ValidacaoException(CHAVE_CAMPO_FUNCIONARIO_ID, "A id do Funcionário é vazia");
        }

        if (funcionario.getNome() == null || funcionario.getNome().trim().equals("")) {
            throw new ValidacaoException(CHAVE_CAMPO_FUNCIONARIO_NOME, "Nome do Funcionário é obrigatório.");
        }

        if (funcionario.getCpf() == null || funcionario.getCpf().trim().equals("")) {
            throw new ValidacaoException(CHAVE_CAMPO_FUNCIONARIO_CPF, "CPF é obrigatório.");
        }else if(!NumericoUtils.validaCPF(funcionario.getCpf())){
            throw new ValidacaoException(CHAVE_CAMPO_FUNCIONARIO_CPF, "CPF inválido.");
        }

        if (funcionario.getRua() == null || funcionario.getRua().trim().equals("")) {
            throw new ValidacaoException(CHAVE_CAMPO_FUNCIONARIO_RUA, "Rua é obrigatório.");
        }

        //Validação das datas
        if (funcionario.getDtContratacao() == null ) {
            throw new ValidacaoException(CHAVE_CAMPO_FUNCIONARIO_DT_CONTRATO, "Data de Contratação é obrigatório.");
        }
        
        if (funcionario.getDtNascimento() == null) {
            throw new ValidacaoException(CHAVE_CAMPO_FUNCIONARIO_DT_NASCIMENTO, "Data de Nascimento é obrigatório.");
        }

//        if (funcionario.getDtDemissao() != null) {
//           throw new ValidacaoException(CHAVE_CAMPO_FUNCIONARIO_DT_DEMISSAO, "Data de Demissão: O formato para essa data é \"DD/MM/AAAA\".");
//        }
        
        if (funcionario.getNumero().trim().length() > 0) {
            validarInteger(funcionario.getNumero(), CHAVE_CAMPO_FUNCIONARIO_NUMERO_RESIDENCIA, "Numero da residencia deve ser do tipo numerico.");
        }

    }
}
