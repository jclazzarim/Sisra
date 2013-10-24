package br.unioeste.sisra.persistencia.factory;

import br.unioeste.sisra.persistencia.dao.ContaDao;
import br.unioeste.sisra.persistencia.dao.FuncionarioDao;
import br.unioeste.sisra.persistencia.dao.ItemDao;
import br.unioeste.sisra.persistencia.dao.MesaDao;


public abstract class DaoFactory {

    private static DaoFactory daoFactory;

    public static DaoFactory getDaoFactory() {

        try {
            if(daoFactory == null)
                daoFactory = (DaoFactory) Class.forName("br.unioeste.sisra.persistencia.factory.PostgresqlDaoFactory").newInstance();
            return daoFactory;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    public abstract FuncionarioDao getFuncionarioDao();
    public abstract ItemDao getItemDao();

    public abstract MesaDao getMesaDao();
    
    public abstract ContaDao getContaDao();
    
  
}

