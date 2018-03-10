package br.com.integraodonto.dao;

import br.com.integraodonto.dto.ContatoDTO;
import br.com.integraodonto.dto.ProfissionalDTO;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class ProfissionalDAO {

    private final Connection connection;

    public ProfissionalDAO(Connection connection) throws SQLException {
        this.connection = connection;
    }

    public ProfissionalDTO retornaDadosProfissionalLogado(ProfissionalDTO profissional) throws SQLException {

        if (profissional == null) {
            throw new IllegalArgumentException("profissional não deve ser nulo");
        }

        String sql = "select id, nome, sexo, especializacao, cro, cpf, rg, stats, deletado, nivel, login, senha, contato, consultorio from profissional where login = ? and senha = ? ;";
        try (PreparedStatement stmt = connection.prepareStatement(sql);) {
            stmt.setString(1, profissional.getLogin());
            stmt.setString(2, profissional.getSenha());

            try (ResultSet rs = stmt.executeQuery()) {
                if (rs.next()) {
                    profissional.setId(rs.getInt("id"));
                    profissional.setNome(rs.getString("nome"));
                    profissional.setSexo(rs.getString("sexo"));
                    profissional.setEspecializacao(rs.getString("especializacao"));
                    profissional.setCro(rs.getString("cro"));
                    profissional.setCpf(rs.getString("cpf"));
                    profissional.setRg(rs.getString("rg"));
                    profissional.setStats(rs.getString("stats"));
                    profissional.setDeletado(rs.getString("deletado"));
                    profissional.setNivel(rs.getString("nivel"));
                    profissional.setLogin(rs.getString("login"));
                    profissional.setSenha(rs.getString("senha"));
                    profissional.setContatoID(rs.getInt("contato"));
                    profissional.setConsultorioID(rs.getInt("consultorio"));
                    return profissional;
                }
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        } finally {

        }
        return null;
    }

    public void adiciona(ProfissionalDTO profissional, ContatoDTO contato) throws SQLException {

        if (profissional == null) {
            throw new IllegalArgumentException("adiciona profissional esta vazio");
        }

        ContatoDAO contatoDAO = new ContatoDAO(connection);

        String sql = "INSERT INTO profissional (nome, sexo, especializacao, cro, cpf, rg, stats, deletado, nivel, login, senha, contato, consultorio) VALUES (?,?,?,?,?,?,?,?,?,?,?,?,?);";
        try (PreparedStatement stmt = connection.prepareStatement(sql);) {
            stmt.setString(1, profissional.getNome());
            stmt.setString(2, profissional.getSexo());
            stmt.setString(3, profissional.getEspecializacao());
            stmt.setString(4, profissional.getCro());
            stmt.setString(5, profissional.getCpf());
            stmt.setString(6, profissional.getRg());
            stmt.setString(7, "ativo");
            stmt.setString(8, "nao");
            stmt.setString(9, "usuario");
            stmt.setString(10, profissional.getLogin());
            stmt.setString(11, profissional.getSenha());
            stmt.setInt(12, contatoDAO.adicionaRetornandoID(contato));
            stmt.setInt(13, profissional.getConsultorioID());
            stmt.execute();

        } catch (Exception e) {

            throw new RuntimeException(e);
        } finally {

        }
    }

    public ProfissionalDTO buscaPorId(long id) throws SQLException {

        if (id == 0) {
            throw new IllegalArgumentException("sessionID não deve ser nulo");
        }

        ProfissionalDTO profissional = new ProfissionalDTO();
        String sql = "SELECT\n"
                + "profissional.id, \n"
                + "profissional.nome, \n"
                + "profissional.sexo, \n"
                + "profissional.especializacao, \n"
                + "profissional.cro, \n"
                + "profissional.cpf, \n"
                + "profissional.rg, \n"
                + "profissional.stats, \n"
                + "profissional.deletado, \n"
                + "profissional.nivel, \n"
                + "profissional.login, \n"
                + "profissional.senha, \n"
                + "profissional.contato,\n"
                + "profissional.consultorio,\n"
                + "\n"
                + "contato.celular, contato.fixo, contato.email\n"
                + "\n"
                + "FROM profissional INNER JOIN contato ON (profissional.contato = contato.id)\n"
                + "WHERE profissional.id = " + id + ";";

        try (PreparedStatement stmt = connection.prepareStatement(sql);) {
            try (ResultSet rs = stmt.executeQuery()) {
                if (rs.next()) {
                    profissional.setId(rs.getInt("id"));
                    profissional.setNome(rs.getString("nome"));
                    profissional.setSexo(rs.getString("sexo"));
                    profissional.setEspecializacao(rs.getString("especializacao"));
                    profissional.setCro(rs.getString("cro"));
                    profissional.setCpf(rs.getString("cpf"));
                    profissional.setRg(rs.getString("rg"));
                    profissional.setStats(rs.getString("stats"));
                    profissional.setDeletado(rs.getString("deletado"));
                    profissional.setNivel(rs.getString("nivel"));
                    profissional.setLogin(rs.getString("login"));
                    profissional.setSenha(rs.getString("senha"));
                    profissional.setContatoID(rs.getInt("contato"));
                    profissional.setConsultorioID(rs.getInt("consultorio"));
                    profissional.setCelular(rs.getString("celular"));
                    profissional.setFixo(rs.getString("fixo"));
                    profissional.setEmail(rs.getString("email"));
                    return profissional;
                }
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        } finally {

        }
        return null;
    }

    public List<ProfissionalDTO> listar(long id) {

        List<ProfissionalDTO> results = new ArrayList<>();
        String sql = "SELECT\n"
                + "profissional.id, \n"
                + "profissional.nome, \n"
                + "profissional.sexo, \n"
                + "profissional.especializacao, \n"
                + "profissional.cro, \n"
                + "profissional.cpf, \n"
                + "profissional.rg, \n"
                + "profissional.stats, \n"
                + "profissional.deletado, \n"
                + "profissional.nivel, \n"
                + "profissional.login, \n"
                + "profissional.senha, \n"
                + "profissional.contato,\n"
                + "profissional.consultorio,\n"
                + "\n"
                + "contato.celular, contato.fixo, contato.email\n"
                + "\n"
                + "FROM profissional INNER JOIN contato ON (profissional.contato = contato.id)\n"
                + "WHERE consultorio = " + id + ";";

        try (PreparedStatement stmt = connection.prepareStatement(sql);
                ResultSet resultSet = stmt.executeQuery()) {

            while (resultSet.next()) {
                results.add(popularTodosProfissionais(resultSet));
            }
        } catch (SQLException e) {

            throw new RuntimeException(e);
        } finally {

        }
        return results;
    }

    private ProfissionalDTO popularTodosProfissionais(ResultSet rs) throws SQLException {
        ProfissionalDTO profissional = new ProfissionalDTO();

        profissional.setId(rs.getInt("id"));
        profissional.setNome(rs.getString("nome"));
        profissional.setSexo(rs.getString("sexo"));
        profissional.setEspecializacao(rs.getString("especializacao"));
        profissional.setCro(rs.getString("cro"));
        profissional.setCpf(rs.getString("cpf"));
        profissional.setRg(rs.getString("rg"));
        profissional.setStats(rs.getString("stats"));
        profissional.setDeletado(rs.getString("deletado"));
        profissional.setNivel(rs.getString("nivel"));
        profissional.setLogin(rs.getString("login"));
        profissional.setSenha(rs.getString("senha"));
        profissional.setContatoID(rs.getInt("contato"));
        profissional.setConsultorioID(rs.getInt("consultorio"));
        profissional.setCelular(rs.getString("celular"));
        profissional.setFixo(rs.getString("fixo"));
        profissional.setEmail(rs.getString("email"));

        return profissional;
    }

    public void alterarPerfilAdmin(ProfissionalDTO profissional, long id) throws SQLException {

        String sql = "UPDATE profissional SET nome = ?, sexo = ?, cpf = ?, rg = ?, login = ?, senha = ? WHERE id = " + id + " ;";
        try (PreparedStatement stmt = connection.prepareStatement(sql);) {
            stmt.setString(1, profissional.getNome());
            stmt.setString(2, profissional.getSexo());
            stmt.setString(3, profissional.getCpf());
            stmt.setString(4, profissional.getRg());
            stmt.setString(5, profissional.getLogin());
            stmt.setString(6, profissional.getSenha());
            stmt.execute();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        } finally {

        }
    }

    public void alterarPerfilUsuario(ProfissionalDTO profissional, long id) throws SQLException {

        String sql = "UPDATE profissional SET nome = ?, sexo = ?, login = ?, senha = ? WHERE id = " + id + " ;";
        try (PreparedStatement stmt = connection.prepareStatement(sql);) {
            stmt.setString(1, profissional.getNome());
            stmt.setString(2, profissional.getSexo());
            stmt.setString(3, profissional.getLogin());
            stmt.setString(4, profissional.getSenha());
            stmt.execute();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        } finally {

        }
    }
}
