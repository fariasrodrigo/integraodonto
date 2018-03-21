package br.com.integraodonto.dao;

import br.com.integraodonto.dto.ContatoDTO;
import br.com.integraodonto.dto.EnderecoDTO;
import br.com.integraodonto.dto.PacienteDTO;
import br.com.integraodonto.dto.PlanosDTO;
import br.com.integraodonto.features.FormataDataMysql;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class PacienteDAO {

    private final Connection connection;
    FormataDataMysql formataDataMysql;

    public PacienteDAO(Connection connection) throws SQLException {
        this.connection = connection;
        this.formataDataMysql = new FormataDataMysql();
    }

    public void adiciona(PacienteDTO paciente, ContatoDTO contato, EnderecoDTO endereco, PlanosDTO planos) throws SQLException {

        if (paciente == null) {
            throw new IllegalArgumentException("adiciona profissional esta vazio");
        }

        connection.setAutoCommit(false);

        ContatoDAO contatoDAO = new ContatoDAO(connection);
        EnderecoDAO enderecoDAO = new EnderecoDAO(connection);
        PlanosDAO planosDAO = new PlanosDAO(connection);

        String sql = "INSERT INTO paciente (prontuario, nome, sexo, nascimento, cpf, rg, descricao, deletado, contato, endereco, consultorio, plano) VALUES (?,?,?,?,?,?,?,?,?,?,?,?);";
        try (PreparedStatement stmt = connection.prepareStatement(sql);) {
            stmt.setString(1, paciente.getProntuario());
            stmt.setString(2, paciente.getNome());
            stmt.setString(3, paciente.getSexo());
            stmt.setString(4, paciente.getNascimento());
            stmt.setString(5, paciente.getCpf());
            stmt.setString(6, paciente.getRg());
            stmt.setString(7, paciente.getDescricao());
            stmt.setString(8, "nao");
            stmt.setLong(9, contatoDAO.adicionaRetornandoID(contato));
            stmt.setLong(10, enderecoDAO.adicionaRetornandoID(endereco));
            stmt.setLong(11, paciente.getConsultorioID());
            stmt.setLong(12, planosDAO.adicionaRetornandoID(planos));
            stmt.execute();

            connection.commit();

        } catch (Exception e) {
            connection.rollback();
            e.printStackTrace();
            throw new RuntimeException(e);
        } finally {

        }
    }

    public List<PacienteDTO> listar() {

        List<PacienteDTO> results = new ArrayList<>();
        String sql = "SELECT\n"
                + "paciente.id,\n"
                + "paciente.prontuario,\n"
                + "paciente.nome,\n"
                + "paciente.sexo,\n"
                + "DATE_FORMAT( nascimento, \"%d/%m/%Y\" ) as nascimento,\n"
                + "paciente.cpf,\n"
                + "paciente.rg,\n"
                + "paciente.descricao,\n"
                + "paciente.deletado,\n"
                + "paciente.contato,\n"
                + "paciente.endereco,\n"
                + "paciente.consultorio,\n"
                + "paciente.plano\n"
                + "FROM paciente ;";

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

    private PacienteDTO popular(ResultSet rs) throws SQLException {
        PacienteDTO paciente = new PacienteDTO();

        paciente.setId(rs.getLong("id"));
        paciente.setProntuario(rs.getString("prontuario"));
        paciente.setNome(rs.getString("nome"));
        paciente.setSexo(rs.getString("sexo"));
        paciente.setNascimento(rs.getString("nascimento"));
        paciente.setCpf(rs.getString("cpf"));
        paciente.setRg(rs.getString("rg"));
        paciente.setDescricao(rs.getString("descricao"));
        paciente.setDeletado(rs.getString("deletado"));
        paciente.setContatoID(rs.getInt("contato"));
        paciente.setEnderecoID(rs.getInt("endereco"));
        paciente.setConsultorioID(rs.getInt("consultorio"));
        paciente.setPlanoID(rs.getInt("plano"));

        return paciente;
    }

    public void alterarDeletadoPaciente(PacienteDTO paciente) throws SQLException {

        String sql = "UPDATE paciente SET deletado = ? WHERE id = " + paciente.getId() + " AND consultorio = " + paciente.getConsultorioID() + " ;";
        try (PreparedStatement stmt = connection.prepareStatement(sql);) {
            stmt.setString(1, "sim");
            stmt.execute();
        } catch (SQLException e) {
            e.printStackTrace();
            throw new RuntimeException(e);
        } finally {

        }
    }

    public void alterar(PacienteDTO paciente, ContatoDTO contato, EnderecoDTO endereco, PlanosDTO plano) throws SQLException {

        if (paciente == null) {
            throw new IllegalArgumentException("adiciona profissional esta vazio");
        }

        connection.setAutoCommit(false);

        ContatoDAO contatoDAO = new ContatoDAO(connection);
        EnderecoDAO enderecoDAO = new EnderecoDAO(connection);
        PlanosDAO planosDAO = new PlanosDAO(connection);

        contatoDAO.alterar(contato);
        enderecoDAO.alterar(endereco);
        planosDAO.alterar(plano);

        String sql = "UPDATE paciente SET prontuario = ?, nome = ?, sexo = ?, nascimento = ?, cpf = ?, rg = ?, descricao = ? WHERE id = " + paciente.getId() + " AND consultorio = " + paciente.getConsultorioID() + " ;";
        try (PreparedStatement stmt = connection.prepareStatement(sql);) {
            stmt.setString(1, paciente.getProntuario());
            stmt.setString(2, paciente.getNome());
            stmt.setString(3, paciente.getSexo());
            stmt.setString(4, formataDataMysql.formataDataMysql(paciente.getNascimento()));
            stmt.setString(5, paciente.getCpf());
            stmt.setString(6, paciente.getRg());
            stmt.setString(7, paciente.getDescricao());
            stmt.execute();

            connection.commit();

        } catch (Exception e) {
            connection.rollback();
            e.printStackTrace();
            throw new RuntimeException(e);
        } finally {

        }
    }
}
