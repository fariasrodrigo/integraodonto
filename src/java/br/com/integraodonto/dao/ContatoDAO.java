package br.com.integraodonto.dao;

import br.com.integraodonto.dto.ContatoDTO;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

public class ContatoDAO {

    private final Connection connection;

    public ContatoDAO(Connection connection) throws SQLException {
        this.connection = connection;
    }

    public List<ContatoDTO> listar(int sessionID) {

        List<ContatoDTO> results = new ArrayList<>();
        String sql = "SELECT id, celular, fixo, email FROM contato WHERE id = " + sessionID + ";";
        try (PreparedStatement stmt = connection.prepareStatement(sql);
                ResultSet resultSet = stmt.executeQuery()) {

            while (resultSet.next()) {
                results.add(popular(resultSet));
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        } finally {

        }
        return results;
    }

    private ContatoDTO popular(ResultSet rs) throws SQLException {
        ContatoDTO contatoDTO = new ContatoDTO();

        contatoDTO.setId(rs.getInt("id"));
        contatoDTO.setCelular(rs.getString("celular"));
        contatoDTO.setFixo(rs.getString("fixo"));
        contatoDTO.setEmail(rs.getString("email"));

        return contatoDTO;
    }

    public ContatoDTO buscaPorId(long id) throws SQLException {

        if (id == 0) {
            throw new IllegalArgumentException("sessionID não deve ser nulo");
        }

        ContatoDTO contatoDTO = new ContatoDTO();
        String sql = "SELECT id, celular, fixo, email FROM contato WHERE id = " + id + ";";
        try (PreparedStatement stmt = connection.prepareStatement(sql);) {
            try (ResultSet rs = stmt.executeQuery()) {
                if (rs.next()) {
                    contatoDTO.setId(rs.getInt("id"));
                    contatoDTO.setCelular(rs.getString("celular"));
                    contatoDTO.setFixo(rs.getString("fixo"));
                    contatoDTO.setEmail(rs.getString("email"));
                    return contatoDTO;
                }
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        } finally {

        }
        return null;
    }

    public int adicionaRetornandoID(ContatoDTO contato) throws SQLException {

        if (contato == null) {
            throw new IllegalArgumentException("adiciona contato esta vazio");
        }

        int id = 0;

        String sql = "INSERT INTO contato (celular, fixo, email) VALUES (?,?,?);";
        try (PreparedStatement stmt = connection.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);) {
            stmt.setString(1, contato.getCelular());
            stmt.setString(2, contato.getFixo());
            stmt.setString(3, contato.getEmail());
            stmt.execute();

            final ResultSet rs = stmt.getGeneratedKeys();
            if (rs.next()) {
                id = rs.getInt(1);
            }

        } catch (Exception e) {

            throw new RuntimeException(e);
        } finally {

        }
        return id;
    }

    public void alterar(ContatoDTO contato, long id) throws SQLException {

        String sql = "UPDATE contato SET celular = ?, fixo = ?, email = ? WHERE id = " + id + " ;";
        try (PreparedStatement stmt = connection.prepareStatement(sql);) {
            stmt.setString(1, contato.getCelular());
            stmt.setString(2, contato.getFixo());
            stmt.setString(3, contato.getEmail());
            stmt.execute();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        } finally {

        }
    }
}
