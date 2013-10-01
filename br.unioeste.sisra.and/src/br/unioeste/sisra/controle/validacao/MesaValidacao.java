/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package br.unioeste.sisra.controle.validacao;

import br.unioeste.sisra.modelo.execao.ValidacaoException;
import br.unioeste.sisra.modelo.to.MesaTO;

/**
 *
 * @author Charlinho
 */
public class MesaValidacao extends Validacao{
    
    public static final String CHAVE_CAMPO_ITEM_ID = "idMesa";
    public static final String CHAVE_CAMPO_ITEM_NOME = "obsMesa";
    public static final String CHAVE_CAMPO_ITEM_DESCRICAO = "descricaoMesa";
    public static final String CHAVE_CAMPO_ITEM_PRECO = "statusMesa";

    @Override
    public void validar(Object to) throws ValidacaoException {
        if (to == null) {
            throw new ValidacaoException("", "Item nulo");
        }

        if (!(to instanceof MesaTO)) {
            throw new ValidacaoException("", "Não é tipo Mesa");
        }

        MesaTO mesa = (MesaTO) to;

        if (mesa.getId() == null) {
            throw new ValidacaoException(CHAVE_CAMPO_ITEM_ID, "A id da Mesa é vazia");
        }

        if (mesa.getDescricao() == null || mesa.getDescricao().trim().equals("")) {
            throw new ValidacaoException(CHAVE_CAMPO_ITEM_NOME, "Nome do Mesa é obrigatório.");
        }
    }
    
}
