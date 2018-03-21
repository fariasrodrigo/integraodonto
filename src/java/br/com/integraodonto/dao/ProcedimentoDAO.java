package br.com.integraodonto.dao;

import br.com.integraodonto.dto.ProcedimentoDTO;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class ProcedimentoDAO {

    private final Connection connection;

    public ProcedimentoDAO(Connection connection) throws SQLException {
        this.connection = connection;
    }

    public List<ProcedimentoDTO> listar() {

        List<ProcedimentoDTO> results = new ArrayList<>();
        String sql = "SELECT\n"
                + "procedimento.id, procedimento.nome, procedimento.valor, procedimento.obs,\n"
                + "procedimento.stats, procedimento.consultorio, procedimento.deletado\n"
                + "FROM procedimento ;";

        try (PreparedStatement stmt = connection.prepareStatement(sql);
                ResultSet resultSet = stmt.executeQuery()) {

            while (resultSet.next()) {
                results.add(popular(resultSet));
            }
        } catch (SQLException e) {
            e.printStackTrace();
            throw new RuntimeException(e);
        } finally {

        }
        return results;
    }

    private ProcedimentoDTO popular(ResultSet rs) throws SQLException {
        ProcedimentoDTO procedimento = new ProcedimentoDTO();

        procedimento.setId(rs.getLong("id"));
        procedimento.setNome(rs.getString("nome"));
        procedimento.setValor(rs.getFloat("valor"));
        procedimento.setObs(rs.getString("obs"));
        procedimento.setStats(rs.getString("stats"));
        procedimento.setConsultorioID(rs.getLong("consultorio"));
        procedimento.setDeletado(rs.getString("deletado"));

        return procedimento;
    }

    public void adiciona(ProcedimentoDTO procedimento) throws SQLException {

        if (procedimento == null) {
            throw new IllegalArgumentException("adiciona procedimento esta vazio");
        }

        connection.setAutoCommit(false);

        String sql = "INSERT INTO procedimento (nome, obs, valor, stats, consultorio, deletado) VALUES (?,?,?,?,?,?);";
        try (PreparedStatement stmt = connection.prepareStatement(sql);) {
            stmt.setString(1, procedimento.getNome());
            stmt.setString(2, procedimento.getObs());
            stmt.setFloat(3, procedimento.getValor());
            stmt.setString(4, procedimento.getStats());
            stmt.setLong(5, procedimento.getConsultorioID());
            stmt.setString(6, "nao");

            stmt.execute();

            connection.commit();
        } catch (Exception e) {
            connection.rollback();
            e.printStackTrace();
            throw new RuntimeException(e);
        } finally {

        }
    }

    public void adicionaProcedimentosIniciais(String sql, long consultorio) throws SQLException {

        connection.setAutoCommit(false);

        try (PreparedStatement stmt = connection.prepareStatement(sql);) {
            stmt.execute();

            connection.commit();
        } catch (Exception e) {
            connection.rollback();
            e.printStackTrace();
            throw new RuntimeException(e);
        } finally {

        }
    }

    public void alterar(ProcedimentoDTO procedimento) throws SQLException {

        String sql = "UPDATE procedimento SET nome = ?, valor = ?, obs = ?, stats = ? WHERE id = " + procedimento.getId() + " and consultorio = " + procedimento.getConsultorioID() + " ;";
        try (PreparedStatement stmt = connection.prepareStatement(sql);) {
            stmt.setString(1, procedimento.getNome());
            stmt.setFloat(2, procedimento.getValor());
            stmt.setString(3, procedimento.getObs());
            stmt.setString(4, procedimento.getStats());
            stmt.execute();
        } catch (SQLException e) {
            e.printStackTrace();
            throw new RuntimeException(e);
        } finally {

        }
    }

    public void alterarDeletadoProcedimento(ProcedimentoDTO procedimento) throws SQLException {

        String sql = "UPDATE procedimento SET deletado = ?, stats = ? WHERE id = " + procedimento.getId() + " AND consultorio = " + procedimento.getConsultorioID() + " ;";
        try (PreparedStatement stmt = connection.prepareStatement(sql);) {
            stmt.setString(1, "sim");
            stmt.setString(2, "inativo");
            stmt.execute();
        } catch (SQLException e) {
            e.printStackTrace();
            throw new RuntimeException(e);
        } finally {

        }
    }
}
