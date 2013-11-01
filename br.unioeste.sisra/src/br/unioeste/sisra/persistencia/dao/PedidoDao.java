/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package br.unioeste.sisra.persistencia.dao;

import br.unioeste.sisra.modelo.entidade.Conta;
import br.unioeste.sisra.modelo.entidade.Pedido;
import br.unioeste.sisra.modelo.execao.DaoException;
import static br.unioeste.sisra.persistencia.dao.PostgresDao.getConnection;
import static br.unioeste.sisra.persistencia.dao.PostgresDao.log;
import static br.unioeste.sisra.persistencia.dao.PostgresDao.logger;
import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author Charlinho
 */
public class PedidoDao extends PostgresDao {

    protected static final String NAME_ENTITY = "pedido";
    //Colunas
    protected static final String COLUMN_ID = "id_pedido";
    protected static final String COLUMN_HR_PEDIDO = "hr_pedido";
    protected static final String COLUMN_ATENDIDO = "atendido";
    protected static final String COLUMN_ID_CONTA = "id_conta";
    //Consultas
    protected static final String SQL_SELECT = "SELECT " + COLUMN_ID + ", "
            + COLUMN_HR_PEDIDO + ", " + COLUMN_ATENDIDO + ", " + COLUMN_ID_CONTA 
            + " FROM " + NAME_ENTITY;
    protected static final String SQL_MAX_ID = "SELECT MAX(" + COLUMN_ID + ") FROM " + NAME_ENTITY;
    //Inserção
    protected static final String SQL_INSERT = ""
            + "INSERT INTO "
            + NAME_ENTITY
            + "("  + COLUMN_HR_PEDIDO + ", " + COLUMN_ATENDIDO + ", " + COLUMN_ID_CONTA + ")"
            + "    VALUES (?, ?, ?)";
    //Atualização
    protected static final String SQL_UPDATE = ""
            + "UPDATE " + NAME_ENTITY + " SET " 
            + COLUMN_HR_PEDIDO + " = ?, " + COLUMN_ATENDIDO + " = ?, "
            + COLUMN_ID_CONTA + " = ? " + "WHERE " + COLUMN_ID + " = ?";
    //Remoção
    protected static final String SQL_DELETE = "DELETE FROM " + NAME_ENTITY + " WHERE " + COLUMN_ID + " = ?";

    public PedidoDao() {
    }

    //--------------------------------------------------------------------------
    // INSERÇÃO, REMOÇÃO E ALTERAÇÃO
    //--------------------------------------------------------------------------
    public Long insert(Pedido entidadeBanco, Long idEntidade) throws DaoException {
        Pedido pedido = (Pedido) entidadeBanco;
        Connection con = null;
        PreparedStatement ps = null;
        ResultSet rs = null;
        try {
            String sql = SQL_INSERT;
            con = getConnection();
            con.setAutoCommit(false);
            ps = con.prepareStatement(sql);
            int paramCount = 1;
            ps.setDate(paramCount++, pedido.getHoraPedido() == null? null : new Date(pedido.getHoraPedido().getTime()));
            ps.setBoolean(paramCount++, pedido.isAtendido());
            ps.setLong(paramCount++, pedido.getConta().getId());

            ps.executeUpdate();
            log.trace("SQL: " + sql);
            con.commit();
        } catch (SQLException e) {
            logger.error("SQLException: " + e.getMessage(), e);
            throw new DaoException("SQLException: " + e.getMessage(), e);
        } catch (Exception e) {
            logger.error("Exception: " + e.getMessage(), e);
            throw new DaoException("Exception: " + e.getMessage(), e);
        } finally {
            try {
                if (rs != null) {
                    rs.close();
                }

                if (ps != null) {
                    ps.close();
                }
                if (con != null) {
                    con.rollback();
                    con.setAutoCommit(true);
                    con.close();
                }
            } catch (Exception e) {
                logger.error("Exception: " + e.getMessage(), e);
                throw new DaoException("Exception: " + e.getMessage(), e);
            }
            return pedido.getIdPedido();
        }
    }

    public int update(Long pk, Pedido pedido) throws DaoException {
        Connection con = null;
        PreparedStatement ps = null;
        int numRows = -1;
        int paramCount = 1;

        try {
            String sql = SQL_UPDATE;
            con = getConnection();
            ps = con.prepareStatement(sql);

            //paremetros
            ps.setDate(paramCount++, pedido.getHoraPedido() == null? null : new Date(pedido.getHoraPedido().getTime()));
            ps.setBoolean(paramCount++, pedido.isAtendido());
            ps.setLong(paramCount++, pedido.getConta().getId());

            //Setando id a ser atualizado
            ps.setLong(paramCount++, pk);
            numRows = ps.executeUpdate();
        } catch (SQLException e) {
            logger.error("SQLException: " + e.getMessage(), e);
            throw new DaoException("SQLException: " + e.getMessage(), e);
        } catch (Exception e) {
            logger.error("Exception: " + e.getMessage(), e);
            throw new DaoException("Exception: " + e.getMessage(), e);
        } finally {
            try {
                if (ps != null) {
                    ps.close();
                }
                if (con != null) {
                    con.close();
                }
            } catch (Exception e) {
            }
        }
        return numRows;
    }

    public int delete(Long pk) throws DaoException {
        Connection con = null;
        PreparedStatement ps = null;
        int numRows = -1;

        try {
            String sql = SQL_DELETE;
            con = getConnection();
            ps = con.prepareStatement(sql);
            ps.setLong(1, pk);
            numRows = ps.executeUpdate();
        } catch (SQLException e) {
            logger.error("SQLException: " + e.getMessage(), e);
            throw new DaoException("SQLException: " + e.getMessage(), e);
        } catch (Exception e) {
            logger.error("Exception: " + e.getMessage(), e);
            throw new DaoException("Exception: " + e.getMessage(), e);
        } finally {
            try {
                if (ps != null) {
                    ps.close();
                }
                if (con != null) {
                    con.close();
                }
            } catch (Exception e) {
            }
        }
        return numRows;
    }

    //--------------------------------------------------------------------------
    // CONSULTAS
    //--------------------------------------------------------------------------
    public Pedido[] findAll() throws DaoException {
        Connection con = null;
        PreparedStatement ps = null;
        ResultSet rs = null;

        try {
            String sql = SQL_SELECT;
            sql += getOrderByClause();
            if (limit != null && limit.intValue() > 0) {
                sql += " LIMIT " + limit;
            }
            if (offset != null && offset.intValue() > 0) {
                sql += "offset " + offset;
            }
            con = getConnection();
            ps = con.prepareStatement(sql);
            log.trace("SQL: " + sql);
            rs = ps.executeQuery();
            return fetchMultipleResults(rs);
        } catch (SQLException e) {
            logger.error("SQLException: " + e.getMessage(), e);
            throw new DaoException("SQLException: " + e.getMessage(), e);
        } catch (Exception e) {
            logger.error("Exception: " + e.getMessage(), e);
            throw new DaoException("Exception: " + e.getMessage(), e);
        } finally {
            try {
                if (rs != null) {
                    rs.close();
                }
                if (ps != null) {
                    ps.close();
                }
                if (con != null) {
                    con.close();
                }
            } catch (Exception e) {
            }
        }
    }
    
    public Pedido[] findAbertos() throws DaoException {
        Connection con = null;
        PreparedStatement ps = null;
        ResultSet rs = null;

        try {
            String sql = SQL_SELECT + " WHERE " + COLUMN_ATENDIDO + " = ?";
            sql += getOrderByClause();
            if (limit != null && limit.intValue() > 0) {
                sql += " LIMIT " + limit;
            }
            if (offset != null && offset.intValue() > 0) {
                sql += "offset " + offset;
            }
            con = getConnection();
            ps = con.prepareStatement(sql);
            ps.setBoolean(1, false);
            log.trace("SQL: " + sql);
            rs = ps.executeQuery();
            return fetchMultipleResults(rs);
        } catch (SQLException e) {
            logger.error("SQLException: " + e.getMessage(), e);
            throw new DaoException("SQLException: " + e.getMessage(), e);
        } catch (Exception e) {
            logger.error("Exception: " + e.getMessage(), e);
            throw new DaoException("Exception: " + e.getMessage(), e);
        } finally {
            try {
                if (rs != null) {
                    rs.close();
                }
                if (ps != null) {
                    ps.close();
                }
                if (con != null) {
                    con.close();
                }
            } catch (Exception e) {
            }
        }
    }

    public Pedido[] findWhereCodigoEquals(Long pk) throws DaoException {
        Connection con = null;
        PreparedStatement ps = null;
        ResultSet rs = null;

        try {
            String sql = SQL_SELECT + " WHERE " + COLUMN_ID + " = ?";
            sql += getOrderByClause();
            if (limit != null && limit.intValue() > 0) {
                sql += " LIMIT " + limit;
            }
            if (offset != null && offset.intValue() > 0) {
                sql += " OFFSET " + offset;
            }
            con = getConnection();
            ps = con.prepareStatement(sql);
            ps.setLong(1, pk);
            log.trace("SQL: " + sql);
            rs = ps.executeQuery();
            return fetchMultipleResults(rs);
        } catch (SQLException e) {
            logger.error("SQLException: " + e.getMessage(), e);
            throw new DaoException("SQLException: " + e.getMessage(), e);
        } catch (Exception e) {
            logger.error("Exception: " + e.getMessage(), e);
            throw new DaoException("Exception: " + e.getMessage(), e);
        } finally {
            try {
                if (rs != null) {
                    rs.close();
                }
                if (ps != null) {
                    ps.close();
                }
                if (con != null) {
                    con.close();
                }
            } catch (Exception e) {
            }
        }
    }

    public int countAll() throws DaoException {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    //--------------------------------------------------------------------------
    // MÉTODOS COMPLEMETARES
    //--------------------------------------------------------------------------
    public void populateDto(Pedido pedido, ResultSet rs) throws Exception {
        pedido.setIdPedido(rs.getLong(COLUMN_ID));
        pedido.setHoraPedido(rs.getDate(COLUMN_HR_PEDIDO));
        pedido.setAtendido(rs.getBoolean(COLUMN_ATENDIDO));
        
        long idConta = rs.getLong(COLUMN_ID_CONTA);
        ContaDao contaDao = new ContaDao();
        Conta[] contas = contaDao.findWhereCodigoEquals(idConta);
        if (contas.length > 0) {
            pedido.setConta(contas[0]);
        }
    }

    public Pedido[] fetchMultipleResults(ResultSet rs) throws Exception {
        ArrayList results = new ArrayList();
        while (rs.next()) {
            Pedido pedido = new Pedido();
            populateDto(pedido, rs);
            results.add(pedido);
        }
        Pedido retValue[] = new Pedido[results.size()];
        results.toArray(retValue);
        return retValue;
    }

    public Pedido[] findWhereIdConta(Long idConta) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }


   
}
