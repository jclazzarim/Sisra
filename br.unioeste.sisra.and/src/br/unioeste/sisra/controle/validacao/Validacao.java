package br.unioeste.sisra.controle.validacao;

import br.unioeste.sisra.modelo.execao.ValidacaoException;
import java.util.concurrent.ExecutionException;


/**
 *
 * @author Tharle
 */
public abstract class Validacao {

    public static final String CHAVE_CAMPO_BUSCA = "busca";

    /**
     * @return null se nao passar na validacao, e neste caso vai ficar alguma
     * das flags como falsa. Caso contrário será retornado a entidade do banco
     * com sucesso
     */
    public abstract void validar(Object to) throws ValidacaoException;

    /**
     * Validação da passagem de um objeto do tipo {@link String} do tipo
     * {@link Long}
     */
    public Long validaLong(String busca) throws ValidacaoException {
        return Long.parseLong(busca);
    }
    
    public int validarInteger(String integer, String chaveCampo, String mensagem) throws ValidacaoException{
        try{
            return Integer.parseInt(integer);
        }catch(Exception e){
            throw new ValidacaoException(chaveCampo, mensagem);
        }
    }
}
