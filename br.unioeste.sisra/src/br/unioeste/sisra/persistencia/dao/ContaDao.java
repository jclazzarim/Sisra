/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package br.unioeste.sisra.persistencia.dao;

import br.unioeste.sisra.modelo.entidade.Conta;
import br.unioeste.sisra.modelo.entidade.Mesa;
import br.unioeste.sisra.modelo.execao.DaoException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

/**
 *
 * @author Charlinho
 */
public class ContaDao extends PostgresDao {

    protected static final String NAME_ENTITY = "conta";
    //Colunas
    protected static final String COLUMN_ID = "id_conta";
    protected static final String COLUMN_HR_ABERTURA = "hr_abertura";
    protected static final String COLUMN_HR_FECHAMENTO = "hr_fechamento";
    protected static final String COLUMN_TOTAL = "total_conta";
    protected static final String COLUMN_ID_MESA = "id_mesa";
    //Consultas
    protected static final String SQL_SELECT = "SELECT " + COLUMN_ID + ", "
            + COLUMN_HR_ABERTURA + ", " + COLUMN_HR_FECHAMENTO + ", " + COLUMN_TOTAL
            + " FROM " + NAME_ENTITY;
    protected static final String SQL_MAX_ID = "SELECT MAX(" + COLUMN_ID + ") FROM " + NAME_ENTITY;
    //Inserção
    protected static final String SQL_INSERT = ""
            + "INSERT INTO "
            + NAME_ENTITY
            + "(" + COLUMN_HR_ABERTURA + ", " + COLUMN_HR_FECHAMENTO + ", " + COLUMN_TOTAL + ""
            + ", "+COLUMN_ID_MESA+")"
            + "    VALUES (?, ?, ?, ?)";
    //Atualização
    protected static final String SQL_UPDATE = ""
            + "UPDATE " + NAME_ENTITY + " SET "
            + COLUMN_HR_ABERTURA + " = ?, " + COLUMN_HR_FECHAMENTO + " = ?"
            + COLUMN_TOTAL + " = ?, " + "WHERE " + COLUMN_ID + " = ?";
    //Remoção
    protected static final String SQL_DELETE = "DELETE FROM " + NAME_ENTITY + " WHERE " + COLUMN_ID + " = ?";

    public ContaDao() {
    }

    //--------------------------------------------------------------------------
    // INSERÇÃO, REMOÇÃO E ALTERAÇÃO
    //--------------------------------------------------------------------------
    public Long insert(Conta entidadeBanco, Long idEntidade) throws DaoException {
        Conta conta = (Conta) entidadeBanco;
        Connection con = null;
        PreparedStatement ps = null;
        ResultSet rs = null;
        try {
            String sql = SQL_INSERT;
            con = getConnection();
            con.setAutoCommit(false);
            ps = con.prepareStatement(sql);
            int paramCount = 1;
            ps.setTimestamp(paramCount++, conta.getHoraAbertura());
            ps.setTimestamp(paramCount++, conta.getHoraFechamento());
            ps.setDouble(paramCount++, conta.getTotal());
            ps.setLong(paramCount++, conta.getMesa().getId());

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
            return conta.getId();
        }
    }

    public int update(Long pk, Conta conta) throws DaoException {
        Connection con = null;
        PreparedStatement ps = null;
        int numRows = -1;
        int paramCount = 1;

        try {
            String sql = SQL_UPDATE;
            con = getConnection();
            ps = con.prepareStatement(sql);

            //paremetros
            ps.setTimestamp(paramCount++, conta.getHoraAbertura());
            ps.setTimestamp(paramCount++, conta.getHoraFechamento());
            ps.setDouble(paramCount++, conta.getTotal());
            ps.setLong(paramCount++, conta.getMesa().getId());

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
    public Conta[] findAll() throws DaoException {
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

    public Conta[] findWhereCodigoEquals(Long pk) throws DaoException {
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
    public void populateDto(Conta conta, ResultSet rs) throws Exception {
        conta.setId(rs.getLong(COLUMN_ID));
        conta.setHoraAbertura(rs.getTimestamp(COLUMN_HR_ABERTURA));
        conta.setHoraFechamento(rs.getTimestamp(COLUMN_HR_FECHAMENTO));
        conta.setTotal(rs.getDouble(COLUMN_TOTAL));

        long idMesa = rs.getLong(COLUMN_ID_MESA);
        MesaDao mesaDao = new MesaDao();
        Mesa[] mesas = mesaDao.findWhereCodigoEquals(idMesa);
        if (mesas.length > 0) {
            conta.setMesa(mesas[0]);
        }
    }

    public Conta[] fetchMultipleResults(ResultSet rs) throws Exception {
        ArrayList results = new ArrayList();
        while (rs.next()) {
            Conta mesa = new Conta();
            populateDto(mesa, rs);
            results.add(mesa);
        }
        Conta retValue[] = new Conta[results.size()];
        results.toArray(retValue);
        return retValue;
    }
}
