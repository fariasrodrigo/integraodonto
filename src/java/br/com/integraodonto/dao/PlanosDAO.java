package br.com.integraodonto.dao;

import br.com.integraodonto.dto.PlanosDTO;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

public class PlanosDAO {

    private final Connection connection;

    public PlanosDAO(Connection connection) throws SQLException {
        this.connection = connection;
    }

    public int adicionaRetornandoID(PlanosDTO planos) throws SQLException {

        if (planos == null) {
            throw new IllegalArgumentException("adiciona contato esta vazio");
        }

        int id = 0;

        String sql = "INSERT INTO plano (plano_de_saude, numero_cartao, nome_do_plano, tipo_de_usuario, titular_do_plano) VALUES (?,?,?,?,?);";
        try (PreparedStatement stmt = connection.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);) {
            stmt.setString(1, planos.getPlanoDeSaude());
            stmt.setString(2, planos.getNumeroDoCartao());
            stmt.setString(3, planos.getNomeDoPlano());
            stmt.setString(4, planos.getTipoDeUsuario());
            stmt.setString(5, planos.getTitularDoPlano());
            stmt.execute();

            final ResultSet rs = stmt.getGeneratedKeys();
            if (rs.next()) {
                id = rs.getInt(1);
            }

        } catch (Exception e) {
            e.printStackTrace();
            throw new RuntimeException(e);
        } finally {

        }
        return id;
    }

    public List<PlanosDTO> listar() {

        List<PlanosDTO> results = new ArrayList<>();
        String sql = "SELECT id, plano_de_saude, numero_cartao, nome_do_plano, tipo_de_usuario, titular_do_plano FROM plano ;";
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

    private PlanosDTO popular(ResultSet rs) throws SQLException {
        PlanosDTO planosDTO = new PlanosDTO();

        planosDTO.setId(rs.getInt("id"));
        planosDTO.setPlanoDeSaude(rs.getString("plano_de_saude"));
        planosDTO.setNumeroDoCartao(rs.getString("numero_cartao"));
        planosDTO.setNomeDoPlano(rs.getString("nome_do_plano"));
        planosDTO.setTipoDeUsuario(rs.getString("tipo_de_usuario"));
        planosDTO.setTitularDoPlano(rs.getString("titular_do_plano"));

        return planosDTO;
    }

    public void adiciona(PlanosDTO planos) throws SQLException {

        if (planos == null) {
            throw new IllegalArgumentException("adiciona plano esta vazio");
        }

        String sql = "INSERT INTO endereco (plano_de_saude, numero_cartao, nome_do_plano, tipo_de_usuario, titular_do_plano) VALUES (?,?,?,?,?);";
        try (PreparedStatement stmt = connection.prepareStatement(sql);) {
            stmt.setString(1, planos.getPlanoDeSaude());
            stmt.setString(2, planos.getNumeroDoCartao());
            stmt.setString(3, planos.getNomeDoPlano());
            stmt.setString(4, planos.getTipoDeUsuario());
            stmt.setString(5, planos.getTitularDoPlano());
            stmt.execute();

        } catch (Exception e) {
            e.printStackTrace();
            throw new RuntimeException(e);
        } finally {

        }
    }

    public void alterar(PlanosDTO planos) throws SQLException {

        if (planos == null) {
            throw new IllegalArgumentException("altera plano esta vazio");
        }

        String sql = "UPDATE plano SET plano_de_saude = ?, numero_cartao = ?, nome_do_plano = ?, tipo_de_usuario = ?, titular_do_plano = ?  WHERE id = " + planos.getId() + " ;";
        try (PreparedStatement stmt = connection.prepareStatement(sql);) {
            stmt.setString(1, planos.getPlanoDeSaude());
            stmt.setString(2, planos.getNumeroDoCartao());
            stmt.setString(3, planos.getNomeDoPlano());
            stmt.setString(4, planos.getTipoDeUsuario());
            stmt.setString(5, planos.getTitularDoPlano());
            stmt.execute();

        } catch (SQLException e) {
            e.printStackTrace();
            throw new RuntimeException(e);
        } finally {

        }
    }
}
