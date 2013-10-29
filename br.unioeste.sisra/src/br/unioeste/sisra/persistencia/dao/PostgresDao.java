package br.unioeste.sisra.persistencia.dao;


import java.sql.*;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;





public abstract class PostgresDao {
    private static Connection connection;
    public String[][] orderByColumns = null;
    public Integer limit;
    public Integer offset;
    public static String ORDER_BY;
    public static Log logger = LogFactory.getLog(PostgresDao.class);
    public static Log log = LogFactory.getLog(PostgresDao.class);

    public static Connection getConnection() throws Exception {
        try {
            Class.forName("org.postgresql.Driver");
            if (connection == null || connection.isClosed()) {
                String strConexao = "jdbc:postgresql://" + ConfiguracaoBanco.IP + ":"+ConfiguracaoBanco.PORTA+"/"+ConfiguracaoBanco.NOME_BANCO;
                connection = DriverManager.getConnection(strConexao, ConfiguracaoBanco.USUARIO, ConfiguracaoBanco.SENHA);
            }
            return connection;
        } catch (Exception e) {
            throw new Exception(e.fillInStackTrace());
        }
    }

    public void setLimit(Integer limit) {
        this.limit = limit;
    }

    public void setOffset(Integer offset) {
        this.offset = offset;
    }

    public String getOrderByClause() {
        if (orderByColumns == null || orderByColumns.length <= 0) {
            return "";
        }
        StringBuilder sb = new StringBuilder(ORDER_BY);
        boolean first = true;
        for (int i = 0; i < orderByColumns.length; i++) {
            if (!first) {
                sb.append(", ");
            } else {
                first = false;
            }
            sb.append(orderByColumns[i][0]);
            if (orderByColumns[i][1] != null) {
                sb.append(" ");
                sb.append(orderByColumns[i][1]);
            }
        }
        sb.append(" ");
        return sb.toString();
    }

  
}
