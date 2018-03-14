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

        String sql = "select id, nome, sexo, especializacao, cpf, rg, stats, agenda, deletado, nivel, login, senha, contato, consultorio from profissional where login = ? and senha = ? ;";
        try (PreparedStatement stmt = connection.prepareStatement(sql);) {
            stmt.setString(1, profissional.getLogin());
            stmt.setString(2, profissional.getSenha());

            try (ResultSet rs = stmt.executeQuery()) {
                if (rs.next()) {
                    profissional.setId(rs.getInt("id"));
                    profissional.setNome(rs.getString("nome"));
                    profissional.setSexo(rs.getString("sexo"));
                    profissional.setEspecializacao(rs.getString("especializacao"));
                    profissional.setCpf(rs.getString("cpf"));
                    profissional.setRg(rs.getString("rg"));
                    profissional.setStats(rs.getString("stats"));
                    profissional.setAgenda(rs.getString("agenda"));
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

        connection.setAutoCommit(false);

        ContatoDAO contatoDAO = new ContatoDAO(connection);

        String sql = "INSERT INTO profissional (nome, sexo, especializacao, cpf, rg, stats, agenda, deletado, nivel, login, senha, contato, consultorio) VALUES (?,?,?,?,?,?,?,?,?,?,?,?,?);";
        try (PreparedStatement stmt = connection.prepareStatement(sql);) {
            stmt.setString(1, profissional.getNome());
            stmt.setString(2, profissional.getSexo());
            stmt.setString(3, profissional.getEspecializacao());
            stmt.setString(4, profissional.getCpf());
            stmt.setString(5, profissional.getRg());
            stmt.setString(6, "ativo");
            stmt.setString(7, profissional.getAgenda());
            stmt.setString(8, "nao");
            stmt.setString(9, "usuario");
            stmt.setString(10, profissional.getLogin());
            stmt.setString(11, profissional.getSenha());
            stmt.setInt(12, contatoDAO.adicionaRetornandoID(contato));
            stmt.setInt(13, profissional.getConsultorioID());

            stmt.execute();

            connection.commit();
        } catch (Exception e) {
            connection.rollback();
            e.printStackTrace();
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
                + "profissional.cpf, \n"
                + "profissional.rg, \n"
                + "profissional.stats, \n"
                + "profissional.agenda, \n"
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
                    profissional.setCpf(rs.getString("cpf"));
                    profissional.setRg(rs.getString("rg"));
                    profissional.setStats(rs.getString("stats"));
                    profissional.setAgenda(rs.getString("agenda"));
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
                + "profissional.cpf, \n"
                + "profissional.rg, \n"
                + "profissional.stats, \n"
                + "profissional.agenda, \n"
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
                + "WHERE consultorio = " + id + " and deletado = 'nao' ;";

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

        profissional.setId(rs.getLong("id"));
        profissional.setNome(rs.getString("nome"));
        profissional.setSexo(rs.getString("sexo"));
        profissional.setEspecializacao(rs.getString("especializacao"));
        profissional.setCpf(rs.getString("cpf"));
        profissional.setRg(rs.getString("rg"));
        profissional.setStats(rs.getString("stats"));
        profissional.setAgenda(rs.getString("agenda"));
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

    public void alterarMeuPerfilAdmin(ProfissionalDTO profissional, long id, long consultorio) throws SQLException {

        String sql = "UPDATE profissional SET nome = ?, sexo = ?, cpf = ?, rg = ?, login = ?, senha = ? WHERE id = " + id + " and consultorio = " + consultorio + " ;";
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

    public void alterarMeuPerfilUsuario(ProfissionalDTO profissional, long id, long consultorio) throws SQLException {

        String sql = "UPDATE profissional SET nome = ?, sexo = ?, login = ?, senha = ? WHERE id = " + id + " and consultorio = " + consultorio + " ;";
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

    public void alterarProfissional(ProfissionalDTO profissional, long consultorio) throws SQLException {

        String sql = "UPDATE profissional SET nome = ?, sexo = ?, especializacao = ?, cpf = ?, rg = ?, stats = ?, agenda = ?, nivel = ?, login = ?, senha = ? WHERE id = " + profissional.getId() + " and consultorio = " + consultorio + ";";
        try (PreparedStatement stmt = connection.prepareStatement(sql);) {
            stmt.setString(1, profissional.getNome());
            stmt.setString(2, profissional.getSexo());
            stmt.setString(3, profissional.getEspecializacao());
            stmt.setString(4, profissional.getCpf());
            stmt.setString(5, profissional.getRg());
            stmt.setString(6, profissional.getStats());
            stmt.setString(7, profissional.getAgenda());
            stmt.setString(8, profissional.getNivel());
            stmt.setString(9, profissional.getLogin());
            stmt.setString(10, profissional.getSenha());
            stmt.execute();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        } finally {

        }
    }

    public void alterarDeletadoProfissional(ProfissionalDTO profissional, long consultorio) throws SQLException {

        String sql = "UPDATE profissional SET deletado = ?, stats = ? WHERE id = " + profissional.getId() + " AND consultorio = " + consultorio + " ;";
        try (PreparedStatement stmt = connection.prepareStatement(sql);) {
            stmt.setString(1, "sim");
            stmt.setString(2, "inativo");
            stmt.execute();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        } finally {

        }
    }

    public boolean verificaSeEmailExisteProfissional(String email, long id) throws SQLException {

        if (email == null) {
            throw new IllegalArgumentException("email não deve ser nulo");
        }
        boolean encontrado;
        String sql = "select login from profissional where login = '" + email + "' and consultorio = " + id + ";";
        try (PreparedStatement stmt = connection.prepareStatement(sql);) {

            try (ResultSet rs = stmt.executeQuery()) {
                encontrado = rs.next();
            }
            return encontrado;
        } catch (SQLException e) {
            throw new RuntimeException(e);
        } finally {

        }
    }
}
