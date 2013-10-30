/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package br.unioeste.sisra.persistencia.dao;

import br.unioeste.sisra.modelo.entidade.Item;
import br.unioeste.sisra.modelo.entidade.PedidoItem;
import br.unioeste.sisra.modelo.entidade.Mesa;
import br.unioeste.sisra.modelo.entidade.Pedido;
import br.unioeste.sisra.modelo.execao.DaoException;
import static br.unioeste.sisra.persistencia.dao.PostgresDao.getConnection;
import static br.unioeste.sisra.persistencia.dao.PostgresDao.log;
import static br.unioeste.sisra.persistencia.dao.PostgresDao.logger;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

/**
 *
 * @author Charlinho
 */
public class PedidoItemDao extends PostgresDao {

    protected static final String NAME_ENTITY = "pedido_item";
    //Colunas
    protected static final String COLUMN_ID = "id_pedido_item";
    protected static final String COLUMN_STATUS = "status";
    protected static final String COLUMN_QUANTIDADE = "quantidade";
    protected static final String COLUMN_ID_ITEM = "id_item";
    protected static final String COLUMN_ID_PEDIDO = "id_pedido";
    
    //Consultas
    protected static final String SQL_SELECT = "SELECT " + COLUMN_ID + ", "
            + COLUMN_STATUS + ", " + COLUMN_QUANTIDADE + ", " + COLUMN_ID_ITEM + ","+COLUMN_ID_PEDIDO
            + " FROM " + NAME_ENTITY;
    protected static final String SQL_MAX_ID = "SELECT MAX(" + COLUMN_ID + ") FROM " + NAME_ENTITY;
    //Inserção
    protected static final String SQL_INSERT = ""
            + "INSERT INTO "
            + NAME_ENTITY
            + "(" + COLUMN_STATUS + ", " + COLUMN_QUANTIDADE + ", " + COLUMN_ID_ITEM
            + ", "+COLUMN_ID_PEDIDO+")"
            + "    VALUES (?, ?, ?, ?, ?)";
    //Atualização
    protected static final String SQL_UPDATE = ""
            + "UPDATE " + NAME_ENTITY + " SET "
            + COLUMN_STATUS + " = ?, " + COLUMN_QUANTIDADE + " = ?"
            + COLUMN_ID_ITEM + " = ?, "+COLUMN_ID_PEDIDO+" = ? " + "WHERE " + COLUMN_ID + " = ?";
    //Remoção
    protected static final String SQL_DELETE = "DELETE FROM " + NAME_ENTITY + " WHERE " + COLUMN_ID + " = ?";

    public PedidoItemDao() {
    }

    //--------------------------------------------------------------------------
    // INSERÇÃO, REMOÇÃO E ALTERAÇÃO
    //--------------------------------------------------------------------------
    public Long insert(PedidoItem entidadeBanco, Long idEntidade) throws DaoException {
        PedidoItem pedidoItem = (PedidoItem) entidadeBanco;
        Connection con = null;
        PreparedStatement ps = null;
        ResultSet rs = null;
        try {
            String sql = SQL_INSERT;
            con = getConnection();
            con.setAutoCommit(false);
            ps = con.prepareStatement(sql);
            int paramCount = 1;
            ps.setInt(paramCount++, pedidoItem.getStatus());
            ps.setInt(paramCount++, pedidoItem.getQuantidade());
            ps.setLong(paramCount++, pedidoItem.getItem().getId());
            ps.setLong(paramCount++, pedidoItem.getPedido().getIdPedido());

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
            return pedidoItem.getIdPedidoItem();
        }
    }

    public int update(Long pk, PedidoItem pedidoItem) throws DaoException {
        Connection con = null;
        PreparedStatement ps = null;
        int numRows = -1;
        int paramCount = 1;

        try {
            String sql = SQL_UPDATE;
            con = getConnection();
            ps = con.prepareStatement(sql);

            //paremetros
             ps.setInt(paramCount++, pedidoItem.getStatus());
            ps.setInt(paramCount++, pedidoItem.getQuantidade());
            ps.setLong(paramCount++, pedidoItem.getItem().getId());
            ps.setLong(paramCount++, pedidoItem.getPedido().getIdPedido());

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
    public PedidoItem[] findAll() throws DaoException {
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

    public PedidoItem[] findWhereCodigoEquals(Long pk) throws DaoException {
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
    public void populateDto(PedidoItem pedidoItem, ResultSet rs) throws Exception {
        pedidoItem.setIdPedidoItem(rs.getLong(COLUMN_ID));
        pedidoItem.setStatus(rs.getInt(COLUMN_STATUS));
        pedidoItem.setQuantidade(rs.getInt(COLUMN_QUANTIDADE));
        
        

        
        //Item
        Long idItem = rs.getLong(COLUMN_ID_ITEM);
        ItemDao itemDao = new ItemDao();
        Item[] items = itemDao.findWhereCodigoEquals(idItem);
        if (items.length > 0) {
            pedidoItem.setItem(items[0]);
        }
        
        //Pedido
        Long idPedido = rs.getLong(COLUMN_ID_PEDIDO);
        PedidoDao pedidoDao = new PedidoDao();
        Pedido[] pedidos = pedidoDao.findWhereCodigoEquals(idPedido);
        if (pedidos.length > 0) {
            pedidoItem.setPedido(pedidos[0]);
        }
        
    }

    public PedidoItem[] fetchMultipleResults(ResultSet rs) throws Exception {
        ArrayList results = new ArrayList();
        while (rs.next()) {
            PedidoItem entidade = new PedidoItem();
            populateDto(entidade, rs);
            results.add(entidade);
        }
        PedidoItem retValue[] = new PedidoItem[results.size()];
        results.toArray(retValue);
        return retValue;
    }

    public PedidoItem[] findWhereIdMesa(Long idMesa, boolean abertas) throws DaoException {
        Connection con = null;
        PreparedStatement ps = null;
        ResultSet rs = null;

        try {
            String sql = SQL_SELECT + " WHERE "+COLUMN_ID_PEDIDO+" = "+idMesa+" AND "+COLUMN_QUANTIDADE+" IS NULL";
            sql += getOrderByClause();
            if (limit != null && limit.intValue() > 0) {
                sql += " LIMIT " + limit;
            }
            if (offset != null && offset.intValue() > 0) {
                sql += " OFFSET " + offset;
            }
            con = getConnection();
            ps = con.prepareStatement(sql);
            //ps.setLong(1, pk);
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

   
}
