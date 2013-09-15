/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package br.unioeste.sisra.controle.validacao;

import static br.unioeste.sisra.controle.validacao.ItemValidacao.CHAVE_CAMPO_ITEM_ID;
import static br.unioeste.sisra.controle.validacao.ItemValidacao.CHAVE_CAMPO_ITEM_NOME;
import br.unioeste.sisra.modelo.execao.ValidacaoException;
import br.unioeste.sisra.modelo.to.ItemTO;

/**
 *
 * @author Mauriverti
 */
public class ItemValidacao extends Validacao{
    public static final String CHAVE_CAMPO_ITEM_ID = "idItem";
    public static final String CHAVE_CAMPO_ITEM_CODIGO = "codigoItem";
    public static final String CHAVE_CAMPO_ITEM_NOME = "nomeItem";
    public static final String CHAVE_CAMPO_ITEM_DESCRICAO = "descricaoItem";
    public static final String CHAVE_CAMPO_ITEM_PRECO = "precoItem";
   
    @Override
    public void validar(Object to) throws ValidacaoException {
        if (to == null) {
            throw new ValidacaoException("", "Item nulo");
        }

        if (!(to instanceof ItemTO)) {
            throw new ValidacaoException("", "Não é tipo item");
        }

        ItemTO item = (ItemTO) to;

        if (item.getId() == null) {
            throw new ValidacaoException(CHAVE_CAMPO_ITEM_ID, "A id do item é vazia");
        }

        if (item.getNome() == null || item.getNome().trim().equals("")) {
            throw new ValidacaoException(CHAVE_CAMPO_ITEM_NOME, "Nome do item é obrigatório.");
        }

    }
}
