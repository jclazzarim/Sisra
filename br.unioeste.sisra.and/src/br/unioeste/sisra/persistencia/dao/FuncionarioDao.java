/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package br.unioeste.sisra.persistencia.dao;

import br.unioeste.sisra.modelo.entidade.Funcionario;
import br.unioeste.sisra.modelo.execao.DaoException;
import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

/**
 *
 * @author Charlinho
 */
public class FuncionarioDao extends PostgresDao {

    protected static final String NAME_ENTITY = "funcionario";
    //Colunas
    protected static final String COLUMN_ID = "ID_Funcionario";
    protected static final String COLUMN_NOME = "Nome";
    protected static final String COLUMN_CPF = "CPF";
    protected static final String COLUMN_RUA = "Rua";
    protected static final String COLUMN_NR_RESIDENCIA = "Nr_residencia";
    protected static final String COLUMN_BAIRRO = "Bairro";
    protected static final String COLUMN_CIDADE = "Cidade";
    protected static final String COLUMN_ESTADO = "Estado";
    protected static final String COLUMN_PAIS = "Pais";
    protected static final String COLUMN_SEXO = "Sexo";
    protected static final String COLUMN_DT_NASC = "Dt_nascimento";
    protected static final String COLUMN_DT_CONTRATACAO = "Dt_contratacao";
    protected static final String COLUMN_RG = "RG";
    protected static final String COLUMN_DT_DEMISSAO = "Dt_demissao";
    //Consultas
    protected static final String SQL_SELECT = "SELECT " + COLUMN_ID + ", "
            + COLUMN_NOME + ", " + COLUMN_CPF + ", " + COLUMN_RUA + ", "
            + COLUMN_CIDADE + ", " + COLUMN_NR_RESIDENCIA + ", " + COLUMN_BAIRRO
            + ", " + COLUMN_ESTADO + ", " + COLUMN_PAIS
            + ", " + COLUMN_SEXO + ", " + COLUMN_DT_NASC + ", "
            + COLUMN_DT_CONTRATACAO + ", " + COLUMN_RG + ", " + COLUMN_DT_DEMISSAO
            + " FROM " + NAME_ENTITY;
    protected static final String SQL_MAX_ID = "SELECT MAX(" + COLUMN_ID + ") FROM " + NAME_ENTITY;
    //Inserção
    protected static final String SQL_INSERT = ""
            + "INSERT INTO "
            + NAME_ENTITY
            + "(" + COLUMN_NOME + ", " + COLUMN_CPF + ", " + COLUMN_RUA + ", "
            + COLUMN_CIDADE + ", " + COLUMN_NR_RESIDENCIA + ", " + COLUMN_BAIRRO
            + ", " + COLUMN_ESTADO + ", " + COLUMN_PAIS + ", " + COLUMN_SEXO
            + ", " + COLUMN_DT_NASC + ", " + COLUMN_DT_CONTRATACAO + ", "
            + COLUMN_RG + ", " + COLUMN_DT_DEMISSAO + ")"
            + "    VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?)";
    //Atualização
    protected static final String SQL_UPDATE = ""
            + "UPDATE " + NAME_ENTITY + " SET " + COLUMN_NOME + " = ?, "
            + COLUMN_CPF + " = ?, " + COLUMN_RUA + " = ?, "
            + COLUMN_CIDADE + " = ?," + COLUMN_NR_RESIDENCIA + " = ?," + COLUMN_BAIRRO
            + " = ?," + COLUMN_ESTADO + " = ?," + COLUMN_PAIS
            + " = ?," + COLUMN_SEXO + " = ?," + COLUMN_DT_NASC + " = ?,"
            + COLUMN_DT_CONTRATACAO + " = ?," + COLUMN_RG + " = ?," + COLUMN_DT_DEMISSAO
            + " = ? WHERE " + COLUMN_ID + " = ?";
    //Remoção
    protected static final String SQL_DELETE = "DELETE FROM " + NAME_ENTITY + " WHERE " + COLUMN_ID + " = ?";

    public FuncionarioDao() {
    }

    //--------------------------------------------------------------------------
    // INSERÇÃO, REMOÇÃO E ALTERAÇÃO
    //--------------------------------------------------------------------------
    public Long insert(Funcionario entidadeBanco, Long idEntidade) throws DaoException {
        Funcionario funcionario = (Funcionario) entidadeBanco;
        Connection con = null;
        PreparedStatement ps = null;
        ResultSet rs = null;
        try {
            String sql = SQL_INSERT;
            con = getConnection();
            con.setAutoCommit(false);
            ps = con.prepareStatement(sql);
            int paramCount = 1;
            ps.setString(paramCount++, funcionario.getNome());
            ps.setString(paramCount++, funcionario.getCpf());
            ps.setString(paramCount++, funcionario.getRua());
            ps.setString(paramCount++, funcionario.getCidade());
            ps.setInt(paramCount++, funcionario.getNumResidencia());
            ps.setString(paramCount++, funcionario.getBairro());
            ps.setString(paramCount++, funcionario.getEstado());
            ps.setString(paramCount++, funcionario.getPais());
            ps.setBoolean(paramCount++, funcionario.getSexo());
            ps.setDate(paramCount++, new Date(funcionario.getDtNasc().getTime()));
            ps.setDate(paramCount++, new Date(funcionario.getDtContrata().getTime()));
            ps.setString(paramCount++, funcionario.getRg());
            
            ps.setDate(paramCount++, funcionario.getDtDemite() == null ? null: new Date(funcionario.getDtDemite().getTime()));

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
            return funcionario.getId();
        }
    }

    public int update(Long pk, Funcionario entidadeBanco) throws DaoException {
        Funcionario f = (Funcionario) entidadeBanco;
        Connection con = null;
        PreparedStatement ps = null;
        int numRows = -1;
        int paramCount = 1;

        try {
            String sql = SQL_UPDATE;
            con = getConnection();
            ps = con.prepareStatement(sql);

            //paremetros
            ps.setString(paramCount++, f.getNome());
            ps.setString(paramCount++, f.getCpf());
            ps.setString(paramCount++, f.getRua());
            ps.setString(paramCount++, f.getCidade());
            ps.setInt(paramCount++, f.getNumResidencia());
            ps.setString(paramCount++, f.getBairro());
            ps.setString(paramCount++, f.getEstado());
            ps.setString(paramCount++, f.getPais());
            ps.setBoolean(paramCount++, f.getSexo());
            ps.setDate(paramCount++, f.getDtNasc() == null ? null : new Date(f.getDtNasc().getTime()));
            ps.setDate(paramCount++, f.getDtContrata() == null ? null : new Date(f.getDtContrata().getTime()));
            ps.setString(paramCount++, f.getRg());
            ps.setDate(paramCount++, f.getDtDemite() == null ? null : new Date(f.getDtDemite().getTime()));

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
    public Funcionario[] findAll() throws DaoException {
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

    public Funcionario[] findWhereCodigoEquals(Long pk) throws DaoException {
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
    public void populateDto(Funcionario f, ResultSet rs) throws SQLException {
        f.setId(rs.getLong(COLUMN_ID));
        f.setNome(rs.getString(COLUMN_NOME));
        f.setCidade(rs.getString(COLUMN_CIDADE));
        f.setCpf(rs.getString(COLUMN_CPF));
        f.setRua(rs.getString(COLUMN_RUA));
        f.setNumResidencia(rs.getInt(COLUMN_NR_RESIDENCIA));
        f.setBairro(rs.getString(COLUMN_BAIRRO));
        f.setEstado(rs.getString(COLUMN_ESTADO));
        f.setPais(rs.getString(COLUMN_PAIS));
        f.setSexo(rs.getBoolean(COLUMN_SEXO));
        f.setDtNasc(rs.getDate(COLUMN_DT_NASC));
        f.setDtContrata(rs.getDate(COLUMN_DT_CONTRATACAO));
        f.setRg(rs.getString(COLUMN_RG));
        f.setDtDemite(rs.getDate(COLUMN_DT_DEMISSAO));
    }

    public Funcionario[] fetchMultipleResults(ResultSet rs) throws SQLException {
        ArrayList results = new ArrayList();
        while (rs.next()) {
            Funcionario ator = new Funcionario();
            populateDto(ator, rs);
            results.add(ator);
        }
        Funcionario retValue[] = new Funcionario[results.size()];
        results.toArray(retValue);
        return retValue;
    }
}
