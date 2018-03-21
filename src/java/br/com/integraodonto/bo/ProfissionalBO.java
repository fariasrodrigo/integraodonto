package br.com.integraodonto.bo;

import br.com.integraodonto.dao.ContatoDAO;
import br.com.integraodonto.dao.ProfissionalDAO;
import br.com.integraodonto.dto.ContatoDTO;
import br.com.integraodonto.dto.ProfissionalDTO;
import br.com.integraodonto.pool.MysqlConnectionPool;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class ProfissionalBO {

    public ProfissionalDTO retornaDadosProfissionalLogado(ProfissionalDTO p) throws SQLException {

        Connection connection = new MysqlConnectionPool().getConnection();
        ProfissionalDTO profissionalDTO = new ProfissionalDTO();

        try {
            ProfissionalDAO profissionalDAO = new ProfissionalDAO(connection);
            List<ProfissionalDTO> profissional = profissionalDAO.listar();

            for (int i = 0; i < profissional.size(); i++) {

                if (p.getLogin().equals(profissional.get(i).getLogin()) && p.getSenha().equals(profissional.get(i).getSenha())) {
                    profissionalDTO = profissional.get(i);
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            if (connection != null) {
                connection.close();
            }
        }
        return profissionalDTO;
    }

    public List<ProfissionalDTO> todosProfissionaisConsultorio(long consultorio) throws SQLException {

        Connection connection = new MysqlConnectionPool().getConnection();
        List<ProfissionalDTO> profissionalDTO = new ArrayList<>();

        try {
            ProfissionalDAO profissionalDAO = new ProfissionalDAO(connection);
            ContatoDAO contatoDAO = new ContatoDAO(connection);

            List<ProfissionalDTO> profissional = profissionalDAO.listar();
            List<ContatoDTO> contato = contatoDAO.listar();

            for (int i = 0; i < profissional.size(); i++) {
                for (int j = 0; j < contato.size(); j++) {

                    if (profissional.get(i).getContatoID() == contato.get(j).getId()) {
                        profissional.get(i).setCelular(contato.get(j).getCelular());
                        profissional.get(i).setFixo(contato.get(j).getFixo());
                        profissional.get(i).setEmail(contato.get(j).getEmail());
                    }
                }
            }

            for (int i = 0; i < profissional.size(); i++) {

                if ("nao".equals(profissional.get(i).getDeletado()) && profissional.get(i).getConsultorioID() == consultorio && "ativo".equals(profissional.get(i).getStats())) {
                    profissionalDTO.add(profissional.get(i));
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            if (connection != null) {
                connection.close();
            }
        }
        return profissionalDTO;
    }

    public List<ProfissionalDTO> comboBoxProfissionaisComAgenda(long consultorio) throws SQLException {

        Connection connection = new MysqlConnectionPool().getConnection();
        List<ProfissionalDTO> profissionalDTO = new ArrayList<>();

        try {
            ProfissionalDAO profissionalDAO = new ProfissionalDAO(connection);

            List<ProfissionalDTO> profissional = profissionalDAO.listar();

            for (int i = 0; i < profissional.size(); i++) {

                if ("nao".equals(profissional.get(i).getDeletado())
                        && profissional.get(i).getConsultorioID() == consultorio
                        && "sim".equals(profissional.get(i).getAgenda())
                        && "ativo".equals(profissional.get(i).getStats())) {
                    profissionalDTO.add(profissional.get(i));
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            if (connection != null) {
                connection.close();
            }
        }
        return profissionalDTO;
    }

    public ProfissionalDTO buscaPorId(long id) throws SQLException {

        Connection connection = new MysqlConnectionPool().getConnection();
        ProfissionalDTO profissionalDTO = new ProfissionalDTO();

        try {
            ProfissionalDAO profissionalDAO = new ProfissionalDAO(connection);
            ContatoDAO contatoDAO = new ContatoDAO(connection);

            List<ProfissionalDTO> profissional = profissionalDAO.listar();
            List<ContatoDTO> contato = contatoDAO.listar();

            for (int i = 0; i < profissional.size(); i++) {
                for (int j = 0; j < contato.size(); j++) {

                    if (profissional.get(i).getContatoID() == contato.get(j).getId()) {
                        profissional.get(i).setCelular(contato.get(j).getCelular());
                        profissional.get(i).setFixo(contato.get(j).getFixo());
                        profissional.get(i).setEmail(contato.get(j).getEmail());
                    }
                }
            }

            for (int i = 0; i < profissional.size(); i++) {

                if (profissional.get(i).getId() == id) {
                    profissionalDTO = profissional.get(i);
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            if (connection != null) {
                connection.close();
            }
        }
        return profissionalDTO;
    }

    public void adiciona(ProfissionalDTO profissional, ContatoDTO contato) throws SQLException {

        Connection connection = new MysqlConnectionPool().getConnection();
        try {
            ProfissionalDAO profissionalDAO = new ProfissionalDAO(connection);
            profissionalDAO.adiciona(profissional, contato);

        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            if (connection != null) {
                connection.close();
            }
        }
    }

    public void alterarMeuPerfilAdmin(ProfissionalDTO profissional) throws SQLException {

        Connection connection = new MysqlConnectionPool().getConnection();
        try {
            ProfissionalDAO profissionalDAO = new ProfissionalDAO(connection);
            profissionalDAO.alterarMeuPerfilAdmin(profissional);

        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            if (connection != null) {
                connection.close();
            }
        }
    }

    public void alterarMeuPerfilUsuario(ProfissionalDTO profissional) throws SQLException {

        Connection connection = new MysqlConnectionPool().getConnection();
        try {
            ProfissionalDAO profissionalDAO = new ProfissionalDAO(connection);
            profissionalDAO.alterarMeuPerfilUsuario(profissional);

        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            if (connection != null) {
                connection.close();
            }
        }
    }

    public void alterarProfissional(ProfissionalDTO profissional, ContatoDTO contato) throws SQLException {

        Connection connection = new MysqlConnectionPool().getConnection();
        try {
            ProfissionalDAO profissionalDAO = new ProfissionalDAO(connection);
            profissionalDAO.alterarMeuPerfilUsuario(profissional);

        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            if (connection != null) {
                connection.close();
            }
        }
    }

    public void alterarDeletadoProfissional(ProfissionalDTO profissional) throws SQLException {

        Connection connection = new MysqlConnectionPool().getConnection();
        try {
            ProfissionalDAO profissionalDAO = new ProfissionalDAO(connection);
            profissionalDAO.alterarDeletadoProfissional(profissional);

        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            if (connection != null) {
                connection.close();
            }
        }
    }
}
