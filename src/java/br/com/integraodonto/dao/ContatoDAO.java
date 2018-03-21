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

    public List<ContatoDTO> listar() {

        List<ContatoDTO> results = new ArrayList<>();
        String sql = "SELECT id, celular, fixo, email FROM contato;";
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

    private ContatoDTO popular(ResultSet rs) throws SQLException {
        ContatoDTO contatoDTO = new ContatoDTO();

        contatoDTO.setId(rs.getInt("id"));
        contatoDTO.setCelular(rs.getString("celular"));
        contatoDTO.setFixo(rs.getString("fixo"));
        contatoDTO.setEmail(rs.getString("email"));

        return contatoDTO;
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
            e.printStackTrace();
            throw new RuntimeException(e);
        } finally {

        }
        return id;
    }

    public void adiciona(ContatoDTO contato) throws SQLException {

        if (contato == null) {
            throw new IllegalArgumentException("adiciona contato esta vazio");
        }

        String sql = "INSERT INTO contato (celular, fixo, email) VALUES (?,?,?);";
        try (PreparedStatement stmt = connection.prepareStatement(sql);) {
            stmt.setString(1, contato.getCelular());
            stmt.setString(2, contato.getFixo());
            stmt.setString(3, contato.getEmail());
            stmt.execute();

        } catch (Exception e) {
            e.printStackTrace();
            throw new RuntimeException(e);
        } finally {

        }
    }

    public void alterar(ContatoDTO contato) throws SQLException {

        if (contato == null) {
            throw new IllegalArgumentException("altera contato esta vazio");
        }

        String sql = "UPDATE contato SET celular = ?, fixo = ?, email = ? WHERE id = " + contato.getId() + " ;";
        try (PreparedStatement stmt = connection.prepareStatement(sql);) {
            stmt.setString(1, contato.getCelular());
            stmt.setString(2, contato.getFixo());
            stmt.setString(3, contato.getEmail());
            stmt.execute();

        } catch (SQLException e) {
            e.printStackTrace();
            throw new RuntimeException(e);
        } finally {

        }
    }
}
