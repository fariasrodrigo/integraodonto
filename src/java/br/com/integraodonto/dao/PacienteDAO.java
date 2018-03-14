package br.com.integraodonto.dao;

import br.com.integraodonto.dto.ContatoDTO;
import br.com.integraodonto.dto.EnderecoDTO;
import br.com.integraodonto.dto.PacienteDTO;
import br.com.integraodonto.dto.PlanosDTO;
import br.com.integraodonto.dto.ProfissionalDTO;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class PacienteDAO {

    private final Connection connection;

    public PacienteDAO(Connection connection) throws SQLException {
        this.connection = connection;
    }

    public void adiciona(PacienteDTO paciente, ContatoDTO contato, EnderecoDTO endereco, PlanosDTO planos) throws SQLException {

        if (paciente == null) {
            throw new IllegalArgumentException("adiciona profissional esta vazio");
        }

        connection.setAutoCommit(false);

        ContatoDAO contatoDAO = new ContatoDAO(connection);
        EnderecoDAO enderecoDAO = new EnderecoDAO(connection);
        PlanosDAO planosDAO = new PlanosDAO(connection);

        String sql = "INSERT INTO paciente (prontuario, nome, sexo, nascimento, cpf, rg, deletado, contato, endereco, consultorio, plano) VALUES (?,?,?,?,?,?,?,?,?,?,?);";
        try (PreparedStatement stmt = connection.prepareStatement(sql);) {
            stmt.setString(1, paciente.getProntuario());
            stmt.setString(2, paciente.getNome());
            stmt.setString(3, paciente.getSexo());
            stmt.setString(4, paciente.getNascimento());
            stmt.setString(5, paciente.getCpf());
            stmt.setString(6, paciente.getRg());
            stmt.setString(7, "nao");
            stmt.setLong(8, contatoDAO.adicionaRetornandoID(contato));
            stmt.setLong(9, enderecoDAO.adicionaRetornandoID(endereco));
            stmt.setLong(10, paciente.getConsultorioID());
            stmt.setLong(11, planosDAO.adicionaRetornandoID(planos));
            stmt.execute();

            connection.commit();

        } catch (Exception e) {
            connection.rollback();
            e.printStackTrace();
            throw new RuntimeException(e);
        } finally {

        }
    }

    public List<PacienteDTO> listar(long id) {

        List<PacienteDTO> results = new ArrayList<>();
        String sql = "SELECT\n"
                + "paciente.id,\n"
                + "paciente.prontuario,\n"
                + "paciente.nome,\n"
                + "paciente.sexo,\n"
                + "paciente.nascimento,\n"
                + "paciente.cpf,\n"
                + "paciente.rg,\n"
                + "paciente.deletado,\n"
                + "paciente.contato,\n"
                + "paciente.endereco,\n"
                + "paciente.consultorio,\n"
                + "paciente.plano,\n"
                + "\n"
                + "contato.celular, contato.fixo, contato.email,\n"
                + "endereco.cep, endereco.endereco, endereco.numero, endereco.compl, endereco.bairro, endereco.cidade, endereco.estado,\n"
                + "plano.plano_de_saude, plano.numero_cartao, plano.nome_do_plano, plano.tipo_de_usuario, plano.titular_do_plano,\n"
                + "consultorio.id, consultorio.nome\n"
                + "\n"
                + "FROM paciente \n"
                + "INNER JOIN contato ON (paciente.contato = contato.id)\n"
                + "INNER JOIN endereco ON (paciente.endereco = endereco.id)\n"
                + "INNER JOIN plano ON (paciente.plano = plano.id)\n"
                + "INNER JOIN consultorio ON (paciente.consultorio = consultorio.id)\n"
                + "WHERE paciente.consultorio = 6 AND paciente.deletado = 'nao';";

        try (PreparedStatement stmt = connection.prepareStatement(sql);
                ResultSet resultSet = stmt.executeQuery()) {

            while (resultSet.next()) {
                results.add(popularTodosPaciente(resultSet));
            }
        } catch (SQLException e) {

            throw new RuntimeException(e);
        } finally {

        }
        return results;
    }

    private PacienteDTO popularTodosPaciente(ResultSet rs) throws SQLException {
        PacienteDTO paciente = new PacienteDTO();

        paciente.setId(rs.getLong("id"));
        paciente.setProntuario(rs.getString("prontuario"));
        paciente.setNome(rs.getString("nome"));
        paciente.setSexo(rs.getString("sexo"));
        paciente.setNascimento(rs.getString("nascimento"));
        paciente.setCpf(rs.getString("cpf"));
        paciente.setRg(rs.getString("rg"));
        paciente.setDeletado(rs.getString("deletado"));
        paciente.setContatoID(rs.getInt("contato"));
        paciente.setEnderecoID(rs.getInt("endereco"));
        paciente.setConsultorioID(rs.getInt("consultorio"));
        paciente.setPlanoID(rs.getInt("plano"));

        paciente.setCelular(rs.getString("celular"));
        paciente.setFixo(rs.getString("fixo"));
        paciente.setEmail(rs.getString("email"));

        paciente.setCep(rs.getString("cep"));
        paciente.setEndereco(rs.getString("endereco"));
        paciente.setNumero(rs.getString("numero"));
        paciente.setCompl(rs.getString("compl"));
        paciente.setBairro(rs.getString("bairro"));
        paciente.setCidade(rs.getString("cidade"));
        paciente.setEstado(rs.getString("estado"));

        paciente.setPlanoDeSaude(rs.getString("plano_de_saude"));
        paciente.setNumeroDoCartao(rs.getString("numero_cartao"));
        paciente.setNomeDoPlano(rs.getString("nome_do_plano"));
        paciente.setTipoDeUsuario(rs.getString("tipo_de_usuario"));
        paciente.setTitularDoPlano(rs.getString("titular_do_plano"));

        return paciente;
    }

    public void alterarDeletadoPaciente(PacienteDTO paciente, long consultorio) throws SQLException {

        String sql = "UPDATE paciente SET deletado = ? WHERE id = " + paciente.getId() + " AND consultorio = " + consultorio + " ;";
        try (PreparedStatement stmt = connection.prepareStatement(sql);) {
            stmt.setString(1, "sim");
            stmt.execute();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        } finally {

        }
    }
}
