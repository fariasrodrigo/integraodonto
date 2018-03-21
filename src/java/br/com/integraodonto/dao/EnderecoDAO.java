package br.com.integraodonto.dao;

import br.com.integraodonto.dto.EnderecoDTO;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

public class EnderecoDAO {

    private final Connection connection;

    public EnderecoDAO(Connection connection) throws SQLException {
        this.connection = connection;
    }

    public List<EnderecoDTO> listar() {

        List<EnderecoDTO> results = new ArrayList<>();
        String sql = "SELECT id, cep, endereco, numero, compl, bairro, cidade, estado FROM endereco ;";
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

    private EnderecoDTO popular(ResultSet rs) throws SQLException {
        EnderecoDTO enderecoDTO = new EnderecoDTO();

        enderecoDTO.setId(rs.getInt("id"));
        enderecoDTO.setCep(rs.getString("cep"));
        enderecoDTO.setEndereco(rs.getString("endereco"));
        enderecoDTO.setNumero(rs.getString("numero"));
        enderecoDTO.setCompl(rs.getString("compl"));
        enderecoDTO.setBairro(rs.getString("bairro"));
        enderecoDTO.setCidade(rs.getString("cidade"));
        enderecoDTO.setEstado(rs.getString("estado"));

        return enderecoDTO;
    }

    public void alterar(EnderecoDTO enderecoDTO) throws SQLException {

        if (enderecoDTO == null) {
            throw new IllegalArgumentException("altera endereco esta vazio");
        }

        String sql = "UPDATE endereco SET cep = ?, endereco = ?, numero = ?, compl = ?, bairro = ?, cidade = ?, estado = ? WHERE id = " + enderecoDTO.getId() + " ;";
        try (PreparedStatement stmt = connection.prepareStatement(sql);) {
            stmt.setString(1, enderecoDTO.getCep());
            stmt.setString(2, enderecoDTO.getEndereco());
            stmt.setString(3, enderecoDTO.getNumero());
            stmt.setString(4, enderecoDTO.getCompl());
            stmt.setString(5, enderecoDTO.getBairro());
            stmt.setString(6, enderecoDTO.getCidade());
            stmt.setString(7, enderecoDTO.getEstado());
            stmt.execute();

        } catch (SQLException e) {
            e.printStackTrace();
            throw new RuntimeException(e);
        } finally {

        }
    }

    public int adicionaRetornandoID(EnderecoDTO enderecoDTO) throws SQLException {

        if (enderecoDTO == null) {
            throw new IllegalArgumentException("adiciona endereco esta vazio");
        }

        int id = 0;

        String sql = "INSERT INTO endereco (cep, endereco, numero, compl, bairro, cidade, estado) VALUES (?,?,?,?,?,?,?);";
        try (PreparedStatement stmt = connection.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);) {
            stmt.setString(1, enderecoDTO.getCep());
            stmt.setString(2, enderecoDTO.getEndereco());
            stmt.setString(3, enderecoDTO.getNumero());
            stmt.setString(4, enderecoDTO.getCompl());
            stmt.setString(5, enderecoDTO.getBairro());
            stmt.setString(6, enderecoDTO.getCidade());
            stmt.setString(7, enderecoDTO.getEstado());
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

    public void adiciona(EnderecoDTO enderecoDTO) throws SQLException {

        if (enderecoDTO == null) {
            throw new IllegalArgumentException("adiciona endereco esta vazio");
        }

        String sql = "INSERT INTO endereco (cep, endereco, numero, compl, bairro, cidade, estado) VALUES (?,?,?,?,?,?,?);";
        try (PreparedStatement stmt = connection.prepareStatement(sql);) {
            stmt.setString(1, enderecoDTO.getCep());
            stmt.setString(2, enderecoDTO.getEndereco());
            stmt.setString(3, enderecoDTO.getNumero());
            stmt.setString(4, enderecoDTO.getCompl());
            stmt.setString(5, enderecoDTO.getBairro());
            stmt.setString(6, enderecoDTO.getCidade());
            stmt.setString(7, enderecoDTO.getEstado());
            stmt.execute();

        } catch (Exception e) {
            e.printStackTrace();
            throw new RuntimeException(e);
        } finally {

        }
    }
}
