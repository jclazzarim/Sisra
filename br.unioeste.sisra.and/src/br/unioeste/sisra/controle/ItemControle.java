/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package br.unioeste.sisra.controle;

import br.unioeste.sisra.controle.validacao.FuncionarioValidacao;
import br.unioeste.sisra.controle.validacao.ItemValidacao;
import br.unioeste.sisra.modelo.entidade.Funcionario;
import br.unioeste.sisra.modelo.entidade.Item;
import br.unioeste.sisra.modelo.execao.DaoException;
import br.unioeste.sisra.modelo.listener.IItemListener;
import br.unioeste.sisra.modelo.to.FuncionarioTO;
import br.unioeste.sisra.modelo.to.ItemTO;
import br.unioeste.sisra.persistencia.dao.FuncionarioDao;
import br.unioeste.sisra.persistencia.dao.ItemDao;
import br.unioeste.sisra.persistencia.factory.PostgresqlDaoFactory;
import java.util.logging.Level;
import java.util.logging.Logger;


/**
 *
 * @author Mauriverti
 */
public class ItemControle {
    
    private IItemListener listener;

    public ItemControle(IItemListener listener) {
        this.listener = listener;
    }

    public void gravar(Object retorno, boolean novo) throws Exception {
        ItemTO to = (ItemTO) retorno;
        //TODO precisamos criar uma validação de tadas dentro do validate
        new ItemValidacao().validar(retorno);

        Item item = new Item();
        item.setId(to.getId());
        item.setNome(to.getNome());
        item.setDescricao(to.getDescricao());
        item.setPreco(Double.parseDouble(to.getPreco()));
        


        ItemDao itemDao = PostgresqlDaoFactory.getDaoFactory().getItemDao();
        try {
            if (novo) {
                itemDao.insert(item, item.getId());
            } else {
                itemDao.update(item.getId(), item);
                
            }
        } catch (DaoException ex) {
            Logger.getLogger(ItemControle.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    

    public void buscarItemsPorId(String text) throws Exception {
                ItemDao dao = PostgresqlDaoFactory.getDaoFactory().getItemDao();
        try {
            Item[] items;
            
            if (text.trim().length() == 0) {
                items = dao.findAll();
            } else {
                Long idItem = new ItemValidacao().validaLong(text);
                items = dao.findWhereCodigoEquals(idItem);
            }
            
            for (Item item : items) {
                System.out.println("- " + item.toString());
            }
            listener.exibirBusca(converterEntidadesEmTO(items));
        } catch (DaoException ex) {
            throw new Exception(ex);
        }
    }

    public void buscarItemsPorNome(String text) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    public void buscarItemsPorPRECO(String text) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    public ItemTO bucarItemPorChave(String pk) throws Exception {
        ItemDao dao = PostgresqlDaoFactory.getDaoFactory().getItemDao();

        Item[] itens = dao.findWhereCodigoEquals(new FuncionarioValidacao().validaLong(pk));
        if (itens.length > 0) {
            return itens[0].toTO();
        } else {
            return new ItemTO();
        }
    }

    public void excluirItem(String pk) throws Exception {
        ItemDao dao = PostgresqlDaoFactory.getDaoFactory().getItemDao();
        dao.delete(new ItemValidacao().validaLong(pk));
        listener.itemExcluidoSucesso(pk);
    }

    private ItemTO[] converterEntidadesEmTO(Item[] items) {
        ItemTO[] result = new ItemTO[items.length];
        int i = 0;
        for (Item item : items) {
            result[i++] = item.toTO();
        }

        return result;
    }
    
}
