package br.unioeste.sisra.persistencia.factory;

import br.unioeste.sisra.persistencia.dao.ContaDao;
import br.unioeste.sisra.persistencia.dao.FuncionarioDao;
import br.unioeste.sisra.persistencia.dao.ItemDao;
import br.unioeste.sisra.persistencia.dao.MesaDao;

public class PostgresqlDaoFactory extends DaoFactory {

    public FuncionarioDao funcionarioDao;
    public ItemDao itemDao;
    public MesaDao mesaDao;
    public ContaDao contaDao;

    @Override
    public FuncionarioDao getFuncionarioDao() {
        if (funcionarioDao == null) {
            funcionarioDao = new FuncionarioDao();
        }
        return funcionarioDao;
    }

    @Override
    public ItemDao getItemDao() {
        if (itemDao == null) {
            itemDao = new ItemDao();
        }
        return itemDao;
    }

    @Override
    public MesaDao getMesaDao() {
        if (mesaDao == null) {
            mesaDao = new MesaDao();
        }
        return mesaDao;
    }

    @Override
    public ContaDao getContaDao() {
        if(contaDao == null){
            contaDao = new ContaDao();
        }
        return contaDao;
    }
}
