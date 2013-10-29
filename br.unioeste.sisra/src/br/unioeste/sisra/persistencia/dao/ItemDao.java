/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package br.unioeste.sisra.persistencia.dao;

import br.unioeste.sisra.modelo.entidade.Item;
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
public class ItemDao extends PostgresDao{
     protected static final String NAME_ENTITY = "item";
    //Colunas
    protected static final String COLUMN_ID = "id_item";
    protected static final String COLUMN_CODIGO = "codigo";
    protected static final String COLUMN_NOME = "nome";
    protected static final String COLUMN_DESCRICAO = "descricao";
    protected static final String COLUMN_PRECO = "preco";
    //Consultas
    protected static final String SQL_SELECT = "SELECT " + COLUMN_ID + ", "
            + COLUMN_CODIGO + ", " + COLUMN_NOME + ", " + COLUMN_DESCRICAO + ", " + COLUMN_PRECO
            + " FROM " + NAME_ENTITY;
    protected static final String SQL_MAX_ID = "SELECT MAX(" + COLUMN_ID + ") FROM " + NAME_ENTITY;
    //Inserção
    protected static final String SQL_INSERT = ""
            + "INSERT INTO "
            + NAME_ENTITY
            + "(" + COLUMN_CODIGO + ", " + COLUMN_NOME + ", " + COLUMN_DESCRICAO + ", " + COLUMN_PRECO + ")"
            + "    VALUES (?, ?, ?, ?)";
    //Atualização
    protected static final String SQL_UPDATE = ""
            + "UPDATE " + NAME_ENTITY + " SET " + COLUMN_CODIGO + " = ?, " + COLUMN_NOME + " = ?, "
            + COLUMN_DESCRICAO + " = ?, " + COLUMN_PRECO
            + " = ? WHERE " + COLUMN_ID + " = ?";
    //Remoção
    protected static final String SQL_DELETE = "DELETE FROM " + NAME_ENTITY + " WHERE " + COLUMN_ID + " = ?";

    public ItemDao() {
    }

    //--------------------------------------------------------------------------
    // INSERÇÃO, REMOÇÃO E ALTERAÇÃO
    //--------------------------------------------------------------------------
    public Long insert(Item entidadeBanco, Long idEntidade) throws DaoException {
        Item item = (Item) entidadeBanco;
        Connection con = null;
        PreparedStatement ps = null;
        ResultSet rs = null;
        try {
            String sql = SQL_INSERT;
            con = getConnection();
            con.setAutoCommit(false);
            ps = con.prepareStatement(sql);
            int paramCount = 1;
            ps.setString(paramCount++, item.getCodigo());
            ps.setString(paramCount++, item.getNome());
            ps.setString(paramCount++, item.getDescricao());
            ps.setDouble(paramCount++, item.getPreco());

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
            return item.getId();
        }
    }

    public int update(Long pk, Item item) throws DaoException {
        Connection con = null;
        PreparedStatement ps = null;
        int numRows = -1;
        int paramCount = 1;

        try {
            String sql = SQL_UPDATE;
            con = getConnection();
            ps = con.prepareStatement(sql);

            //paremetros
            ps.setString(paramCount++,item.getCodigo());
            ps.setString(paramCount++, item.getNome());
            ps.setString(paramCount++, item.getDescricao());
            ps.setDouble(paramCount++, item.getPreco());

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
    public Item[] findAll() throws DaoException {
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

    public Item[] findWhereCodigoEquals(Long pk) throws DaoException {
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
    public void populateDto(Item item, ResultSet rs) throws SQLException {
        item.setId(rs.getLong(COLUMN_ID));
        item.setCodigo(rs.getString(COLUMN_CODIGO));
        item.setNome(rs.getString(COLUMN_NOME));
        item.setDescricao(rs.getString(COLUMN_DESCRICAO));
        item.setPreco(rs.getDouble(COLUMN_PRECO));
    }

    public Item[] fetchMultipleResults(ResultSet rs) throws SQLException {
        ArrayList results = new ArrayList();
        while (rs.next()) {
            Item item = new Item();
            populateDto(item, rs);
            results.add(item);
        }
        Item retValue[] = new Item[results.size()];
        results.toArray(retValue);
        return retValue;
    }
    
}
