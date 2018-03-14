package br.com.integraodonto.dao;

import br.com.integraodonto.dto.PlanosDTO;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

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

            throw new RuntimeException(e);
        } finally {

        }
        return id;
    }
}
