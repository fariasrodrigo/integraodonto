package br.com.integraodonto.bo;

import br.com.integraodonto.dao.ConsultaDAO;
import br.com.integraodonto.dao.ContatoDAO;
import br.com.integraodonto.dao.EnderecoDAO;
import br.com.integraodonto.dao.PacienteDAO;
import br.com.integraodonto.dao.PlanosDAO;
import br.com.integraodonto.dto.ConsultaDTO;
import br.com.integraodonto.dto.ContatoDTO;
import br.com.integraodonto.dto.EnderecoDTO;
import br.com.integraodonto.dto.PacienteDTO;
import br.com.integraodonto.dto.PlanosDTO;
import br.com.integraodonto.pool.MysqlConnectionPool;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class PacienteBO {

    public List<PacienteDTO> comboBoxPacientesPainel(long consultorio) throws SQLException {

        Connection connection = new MysqlConnectionPool().getConnection();

        PacienteDAO pacienteDAO = new PacienteDAO(connection);

        List<PacienteDTO> pacienteDTO = new ArrayList<>();
        List<PacienteDTO> paciente = pacienteDAO.listar();

        for (int i = 0; i < paciente.size(); i++) {

            if ("nao".equals(paciente.get(i).getDeletado())
                    && paciente.get(i).getConsultorioID() == consultorio) {
                pacienteDTO.add(paciente.get(i));
            }
        }
        return pacienteDTO;
    }

    public List<PacienteDTO> todosPacientesConsultorio(long consultorio) throws SQLException {

        Connection connection = new MysqlConnectionPool().getConnection();
        List<PacienteDTO> pacienteDTO = new ArrayList<>();

        try {
            PacienteDAO pacienteDAO = new PacienteDAO(connection);
            ContatoDAO contatoDAO = new ContatoDAO(connection);
            EnderecoDAO enderecoDAO = new EnderecoDAO(connection);
            PlanosDAO planosDAO = new PlanosDAO(connection);

            List<PacienteDTO> paciente = pacienteDAO.listar();
            List<ContatoDTO> contato = contatoDAO.listar();
            List<EnderecoDTO> endereco = enderecoDAO.listar();
            List<PlanosDTO> planos = planosDAO.listar();

            for (int i = 0; i < paciente.size(); i++) {
                for (int j = 0; j < contato.size(); j++) {

                    if (paciente.get(i).getContatoID() == contato.get(j).getId()) {

                        paciente.get(i).setCelular(contato.get(j).getCelular());
                        paciente.get(i).setFixo(contato.get(j).getFixo());
                        paciente.get(i).setEmail(contato.get(j).getEmail());
                    }
                }
            }

            for (int i = 0; i < paciente.size(); i++) {
                for (int j = 0; j < endereco.size(); j++) {

                    if (paciente.get(i).getEnderecoID() == endereco.get(j).getId()) {

                        paciente.get(i).setCep(endereco.get(j).getCep());
                        paciente.get(i).setEndereco(endereco.get(j).getEndereco());
                        paciente.get(i).setNumero(endereco.get(j).getNumero());
                        paciente.get(i).setCompl(endereco.get(j).getCompl());
                        paciente.get(i).setBairro(endereco.get(j).getBairro());
                        paciente.get(i).setCidade(endereco.get(j).getCidade());
                        paciente.get(i).setEstado(endereco.get(j).getEstado());
                    }
                }
            }

            for (int i = 0; i < paciente.size(); i++) {
                for (int j = 0; j < planos.size(); j++) {

                    if (paciente.get(i).getPlanoID() == planos.get(j).getId()) {

                        paciente.get(i).setPlanoDeSaude(planos.get(j).getPlanoDeSaude());
                        paciente.get(i).setNumeroDoCartao(planos.get(j).getNumeroDoCartao());
                        paciente.get(i).setNomeDoPlano(planos.get(j).getNomeDoPlano());
                        paciente.get(i).setTipoDeUsuario(planos.get(j).getTipoDeUsuario());
                        paciente.get(i).setTitularDoPlano(planos.get(j).getTitularDoPlano());
                    }
                }
            }

            for (int i = 0; i < paciente.size(); i++) {

                if ("nao".equals(paciente.get(i).getDeletado()) && paciente.get(i).getConsultorioID() == consultorio) {
                    pacienteDTO.add(paciente.get(i));
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            if (connection != null) {
                connection.close();
            }
        }
        return pacienteDTO;
    }

    public PacienteDTO pacientePorId(long id, long consultorio) throws SQLException {

        Connection connection = new MysqlConnectionPool().getConnection();
        PacienteDTO pcnt = new PacienteDTO();
        List<PacienteDTO> pacienteDTO = new ArrayList<>();

        try {
            PacienteDAO pacienteDAO = new PacienteDAO(connection);
            ContatoDAO contatoDAO = new ContatoDAO(connection);
            EnderecoDAO enderecoDAO = new EnderecoDAO(connection);
            PlanosDAO planosDAO = new PlanosDAO(connection);

            List<PacienteDTO> paciente = pacienteDAO.listar();
            List<ContatoDTO> contato = contatoDAO.listar();
            List<EnderecoDTO> endereco = enderecoDAO.listar();
            List<PlanosDTO> planos = planosDAO.listar();

            for (int i = 0; i < paciente.size(); i++) {

                if ("nao".equals(paciente.get(i).getDeletado()) && paciente.get(i).getId() == id && paciente.get(i).getConsultorioID() == consultorio) {
                    pacienteDTO.add(paciente.get(i));
                }
            }

            for (int i = 0; i < pacienteDTO.size(); i++) {
                for (int j = 0; j < contato.size(); j++) {

                    if (pacienteDTO.get(i).getContatoID() == contato.get(j).getId()) {

                        pacienteDTO.get(i).setCelular(contato.get(j).getCelular());
                        pacienteDTO.get(i).setFixo(contato.get(j).getFixo());
                        pacienteDTO.get(i).setEmail(contato.get(j).getEmail());
                    }
                }
            }

            for (int i = 0; i < pacienteDTO.size(); i++) {
                for (int j = 0; j < endereco.size(); j++) {

                    if (pacienteDTO.get(i).getEnderecoID() == endereco.get(j).getId()) {

                        pacienteDTO.get(i).setCep(endereco.get(j).getCep());
                        pacienteDTO.get(i).setEndereco(endereco.get(j).getEndereco());
                        pacienteDTO.get(i).setNumero(endereco.get(j).getNumero());
                        pacienteDTO.get(i).setCompl(endereco.get(j).getCompl());
                        pacienteDTO.get(i).setBairro(endereco.get(j).getBairro());
                        pacienteDTO.get(i).setCidade(endereco.get(j).getCidade());
                        pacienteDTO.get(i).setEstado(endereco.get(j).getEstado());
                    }
                }
            }

            for (int i = 0; i < pacienteDTO.size(); i++) {
                for (int j = 0; j < planos.size(); j++) {

                    if (pacienteDTO.get(i).getPlanoID() == planos.get(j).getId()) {

                        pacienteDTO.get(i).setPlanoDeSaude(planos.get(j).getPlanoDeSaude());
                        pacienteDTO.get(i).setNumeroDoCartao(planos.get(j).getNumeroDoCartao());
                        pacienteDTO.get(i).setNomeDoPlano(planos.get(j).getNomeDoPlano());
                        pacienteDTO.get(i).setTipoDeUsuario(planos.get(j).getTipoDeUsuario());
                        pacienteDTO.get(i).setTitularDoPlano(planos.get(j).getTitularDoPlano());
                    }
                }
            }

        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            if (connection != null) {
                connection.close();
            }
        }

        for (int i = 0; i < pacienteDTO.size(); i++) {
            if ("nao".equals(pacienteDTO.get(i).getDeletado()) && pacienteDTO.get(i).getId() == id && pacienteDTO.get(i).getConsultorioID() == consultorio) {
                pcnt = pacienteDTO.get(i);
            }
        }
        return pcnt;
    }

    public void altera(PacienteDTO paciente) throws SQLException {

        Connection connection = new MysqlConnectionPool().getConnection();
        ContatoDTO contato = new ContatoDTO();
        EnderecoDTO endereco = new EnderecoDTO();
        PlanosDTO plano = new PlanosDTO();

        try {
            PacienteDAO pacienteDAO = new PacienteDAO(connection);
            List<PacienteDTO> p = pacienteDAO.listar();

            for (int i = 0; i < p.size(); i++) {

                if (p.get(i).getId() == paciente.getId()) {

                    contato.setId(p.get(i).getContatoID());
                    endereco.setId(p.get(i).getEnderecoID());
                    plano.setId(p.get(i).getPlanoID());
                }
            }

            contato.setCelular(paciente.getCelular());
            contato.setFixo(paciente.getFixo());
            contato.setEmail(paciente.getEmail());

            endereco.setCep(paciente.getCep());
            endereco.setEndereco(paciente.getEndereco());
            endereco.setNumero(paciente.getNumero());
            endereco.setCompl(paciente.getCompl());
            endereco.setBairro(paciente.getBairro());
            endereco.setCidade(paciente.getCidade());
            endereco.setEstado(paciente.getEstado());
            plano.setPlanoDeSaude(paciente.getPlanoDeSaude());
            plano.setNumeroDoCartao(paciente.getNumeroDoCartao());
            plano.setNomeDoPlano(paciente.getNomeDoPlano());
            plano.setTipoDeUsuario(paciente.getTipoDeUsuario());
            plano.setTitularDoPlano(paciente.getTitularDoPlano());

            pacienteDAO.alterar(paciente, contato, endereco, plano);

        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            if (connection != null) {
                connection.close();
            }
        }
    }

    public void alterarDeletadoPaciente(PacienteDTO paciente) throws SQLException {

        Connection connection = new MysqlConnectionPool().getConnection();
        try {
            PacienteDAO pacienteDAO = new PacienteDAO(connection);
            ConsultaDAO consultaDAO = new ConsultaDAO(connection);
            List<ConsultaDTO> consulta = consultaDAO.listar();
            boolean o = false;
            pacienteDAO.alterarDeletadoPaciente(paciente);

            for (int i = 0; i < consulta.size(); i++) {
                if (consulta.get(i).getPacienteID() == paciente.getId()) {
                    o = true;
                }
            }

            if (o == true) {
                consultaDAO.deletarConsultasPacienteDeletado(paciente.getId());
            }

        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            if (connection != null) {
                connection.close();
            }
        }
    }
}
