package br.com.integraodonto.dao;

import br.com.integraodonto.dto.EnderecoDTO;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class EnderecoDAO {

    private final Connection connection;

    public EnderecoDAO(Connection connection) throws SQLException {
        this.connection = connection;
    }

    public List<EnderecoDTO> listar(int sessionID) {

        List<EnderecoDTO> results = new ArrayList<>();
        String sql = "SELECT id, cep, endereco, numero, compl, bairro, cidade, estado FROM endereco WHERE id = " + sessionID + ";";
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

    private EnderecoDTO popular(ResultSet rs) throws SQLException {
        EnderecoDTO enderecoDTO = new EnderecoDTO();

        enderecoDTO.setId(rs.getInt("id"));
        enderecoDTO.setCep(rs.getString("cep"));
        enderecoDTO.setEndereco(rs.getString("endereco"));
        enderecoDTO.setNumero(rs.getInt("numero"));
        enderecoDTO.setCompl(rs.getString("compl"));
        enderecoDTO.setBairro(rs.getString("bairro"));
        enderecoDTO.setCidade(rs.getString("cidade"));
        enderecoDTO.setEstado(rs.getString("estado"));

        return enderecoDTO;
    }

    public EnderecoDTO buscaPorId(long id) throws SQLException {

        if (id == 0) {
            throw new IllegalArgumentException("sessionID não deve ser nulo");
        }

        EnderecoDTO enderecoDTO = new EnderecoDTO();
        String sql = "SELECT id, cep, endereco, numero, compl, bairro, cidade, estado FROM endereco WHERE id = " + id + ";";
        try (PreparedStatement stmt = connection.prepareStatement(sql);) {
            try (ResultSet rs = stmt.executeQuery()) {
                if (rs.next()) {
                    enderecoDTO.setId(rs.getInt("id"));
                    enderecoDTO.setCep(rs.getString("cep"));
                    enderecoDTO.setEndereco(rs.getString("endereco"));
                    enderecoDTO.setNumero(rs.getInt("numero"));
                    enderecoDTO.setCompl(rs.getString("compl"));
                    enderecoDTO.setBairro(rs.getString("bairro"));
                    enderecoDTO.setCidade(rs.getString("cidade"));
                    enderecoDTO.setEstado(rs.getString("estado"));
                    return enderecoDTO;
                }
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        } finally {

        }
        return null;
    }
}
