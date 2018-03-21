package br.com.integraodonto.dao;

import br.com.integraodonto.dto.ConsultorioDTO;
import br.com.integraodonto.dto.ProfissionalDTO;
import br.com.integraodonto.features.FormataSqlProcedimento;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

public class ConsultorioDAO {

    private final Connection connection;
    FormataSqlProcedimento formataSqlProcedimento;

    public ConsultorioDAO(Connection connection) throws SQLException {
        this.connection = connection;
        this.formataSqlProcedimento = new FormataSqlProcedimento();
    }

    public void adicionaConsultorio(ConsultorioDTO consultorio, ProfissionalDTO profissional) throws SQLException {

        if (consultorio == null || profissional == null) {
            throw new IllegalArgumentException("adiciona consultorio esta vazio");
        }

        connection.setAutoCommit(false);

        ProcedimentoDAO procedimentoDAO = new ProcedimentoDAO(connection);

        try {

            int idConsultorio = 0;
            int idEndereco = 0;
            int idContatoC = 0;
            int idContatoP = 0;

            String sqlConsultorio = "INSERT INTO consultorio (nome, cnpj, stats, deletado, contato, endereco) VALUES (?,?,?,?,?,?);";
            String sqlEndereco = "INSERT INTO endereco (compl) VALUES (?);";
            String sqlContato = "INSERT INTO contato (email) VALUES (?);";
            String sqlProfissional = "INSERT INTO profissional (nome, stats, deletado, nivel, login, senha, contato, consultorio) VALUES (?,?,?,?,?,?,?,?);";

            //adicionando e recuperando id endereço
            try (PreparedStatement psEndereco = connection.prepareStatement(sqlEndereco, Statement.RETURN_GENERATED_KEYS);) {
                psEndereco.setString(1, "");
                psEndereco.execute();

                final ResultSet rsEndereco = psEndereco.getGeneratedKeys();
                if (rsEndereco.next()) {
                    idEndereco = rsEndereco.getInt(1);
                }
            } catch (Exception e) {
                throw new RuntimeException(e);
            } finally {

            }

            //adicionando e recuperando id contato Consultório
            try (PreparedStatement psContatoC = connection.prepareStatement(sqlContato, Statement.RETURN_GENERATED_KEYS);) {
                psContatoC.setString(1, "");
                psContatoC.execute();

                final ResultSet rsContatoC = psContatoC.getGeneratedKeys();
                if (rsContatoC.next()) {
                    idContatoC = rsContatoC.getInt(1);
                }
            } catch (Exception e) {
                throw new RuntimeException(e);
            } finally {

            }

            //adicionando e recuperando id contato Profissional
            try (PreparedStatement psContatoP = connection.prepareStatement(sqlContato, Statement.RETURN_GENERATED_KEYS);) {
                psContatoP.setString(1, "");
                psContatoP.execute();

                final ResultSet rsContatoP = psContatoP.getGeneratedKeys();
                if (rsContatoP.next()) {
                    idContatoP = rsContatoP.getInt(1);
                }
            } catch (Exception e) {
                throw new RuntimeException(e);
            } finally {

            }

            //adicionando e recuperando id consultório
            try (PreparedStatement psConsultorio = connection.prepareStatement(sqlConsultorio, Statement.RETURN_GENERATED_KEYS);) {
                psConsultorio.setString(1, consultorio.getNome());
                psConsultorio.setString(2, consultorio.getCnpj());
                psConsultorio.setString(3, "ativo");
                psConsultorio.setString(4, "nao");
                psConsultorio.setLong(5, idContatoC);
                psConsultorio.setLong(6, idEndereco);
                psConsultorio.execute();

                final ResultSet rsConsultorio = psConsultorio.getGeneratedKeys();
                if (rsConsultorio.next()) {
                    idConsultorio = rsConsultorio.getInt(1);
                }
            } catch (Exception e) {
                throw new RuntimeException(e);
            } finally {

            }

            //adicionando profissional do consultório
            try (PreparedStatement psProfissional = connection.prepareStatement(sqlProfissional);) {
                psProfissional.setString(1, profissional.getNome());
                psProfissional.setString(2, "ativo");
                psProfissional.setString(3, "nao");
                psProfissional.setString(4, "admin");
                psProfissional.setString(5, profissional.getLogin());
                psProfissional.setString(6, profissional.getSenha());
                psProfissional.setLong(7, idContatoP);
                psProfissional.setLong(8, idConsultorio);
                psProfissional.execute();

            } catch (Exception e) {
                throw new RuntimeException(e);
            } finally {

            }

            //adicionando procedimentos iniciais
            procedimentoDAO.adicionaProcedimentosIniciais(formataSqlProcedimento.FormataSqlProcedimento(idConsultorio), idConsultorio);

            connection.commit();
        } catch (RuntimeException e) {
            connection.rollback();
            e.printStackTrace();
        } finally {

        }

    }

    public List<ConsultorioDTO> listar() {

        List<ConsultorioDTO> results = new ArrayList<>();
        String sql = "SELECT id, nome, nome_responsavel, cnpj, stats, deletado, contato, endereco FROM consultorio ;";
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

    private ConsultorioDTO popular(ResultSet rs) throws SQLException {
        ConsultorioDTO consultorioDTO = new ConsultorioDTO();

        consultorioDTO.setId(rs.getInt("id"));
        consultorioDTO.setNome(rs.getString("nome"));
        consultorioDTO.setNomeResponsavel(rs.getString("nome_responsavel"));
        consultorioDTO.setCnpj(rs.getString("cnpj"));
        consultorioDTO.setStats(rs.getString("stats"));
        consultorioDTO.setDeletado(rs.getString("deletado"));
        consultorioDTO.setContatoID(rs.getInt("contato"));
        consultorioDTO.setEnderecoID(rs.getInt("endereco"));

        return consultorioDTO;
    }

    public ConsultorioDTO recuperaConsultorio(long id) throws SQLException {

        ConsultorioDTO consultorioDTO = new ConsultorioDTO();
        String sql = "SELECT id, nome, nome_responsavel, cnpj, stats, deletado, contato, endereco FROM consultorio WHERE id = " + id + ";";
        try (PreparedStatement stmt = connection.prepareStatement(sql);) {
            try (ResultSet rs = stmt.executeQuery()) {
                if (rs.next()) {
                    consultorioDTO.setId(rs.getInt("id"));
                    consultorioDTO.setNome(rs.getString("nome"));
                    consultorioDTO.setNomeResponsavel(rs.getString("nome_responsavel"));
                    consultorioDTO.setCnpj(rs.getString("cnpj"));
                    consultorioDTO.setStats(rs.getString("stats"));
                    consultorioDTO.setDeletado(rs.getString("deletado"));
                    consultorioDTO.setContatoID(rs.getInt("contato"));
                    consultorioDTO.setEnderecoID(rs.getInt("endereco"));
                    return consultorioDTO;
                }
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        } finally {

        }
        return null;
    }

    public void alterar(ConsultorioDTO consultorioDTO, long id) throws SQLException {

        String sql = "UPDATE consultorio SET nome = ?, nome_responsavel = ?, cnpj = ? WHERE id = " + id + " ;";
        try (PreparedStatement stmt = connection.prepareStatement(sql);) {
            stmt.setString(1, consultorioDTO.getNome());
            stmt.setString(2, consultorioDTO.getNomeResponsavel());
            stmt.setString(3, consultorioDTO.getCnpj());
            stmt.execute();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        } finally {

        }
    }
}
