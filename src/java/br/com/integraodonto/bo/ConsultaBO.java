package br.com.integraodonto.bo;

import br.com.integraodonto.dao.ConsultaDAO;
import br.com.integraodonto.dao.ContatoDAO;
import br.com.integraodonto.dao.PacienteDAO;
import br.com.integraodonto.dao.ProfissionalDAO;
import br.com.integraodonto.dto.ConsultaDTO;
import br.com.integraodonto.dto.ContatoDTO;
import br.com.integraodonto.dto.PacienteDTO;
import br.com.integraodonto.dto.ProfissionalDTO;
import br.com.integraodonto.pool.MysqlConnectionPool;
import java.sql.Connection;
import java.sql.SQLException;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class ConsultaBO {

    public List<ConsultaDTO> listarConsultasPainel(long consultorio) throws SQLException {

        Connection connection = new MysqlConnectionPool().getConnection();
        List<ConsultaDTO> painel = new ArrayList<>();

        try {
            Date dt = new Date();
            //dt.setDate(dt.getDate() + 1);
            DateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy");
            String agora = dateFormat.format(dt);

            ConsultaDAO consultaDAO = new ConsultaDAO(connection);
            ContatoDAO contatoDAO = new ContatoDAO(connection);
            PacienteDAO pacienteDAO = new PacienteDAO(connection);
            ProfissionalDAO profissionalDAO = new ProfissionalDAO(connection);

            List<PacienteDTO> pacienteTemp = new ArrayList<>();

            List<ConsultaDTO> consulta = consultaDAO.listar();
            List<ContatoDTO> contato = contatoDAO.listar();
            List<PacienteDTO> paciente = pacienteDAO.listar();
            List<ProfissionalDTO> profissional = profissionalDAO.listar();

            for (int i = 0; i < paciente.size(); i++) {
                if ("nao".equals(paciente.get(i).getDeletado())) {
                    pacienteTemp.add(paciente.get(i));
                }
            }

            for (int i = 0; i < consulta.size(); i++) {
                for (int j = 0; j < pacienteTemp.size(); j++) {

                    if (consulta.get(i).getConsultorioID() == consultorio
                            && consulta.get(i).getPacienteID() == pacienteTemp.get(j).getId()
                            && "nao".equals(consulta.get(i).getDeletado())) {
                        consulta.get(i).setPacienteTemp(pacienteTemp.get(j).getNome());
                    }
                }
            }

            for (int i = 0; i < consulta.size(); i++) {
                for (int j = 0; j < profissional.size(); j++) {

                    if (consulta.get(i).getConsultorioID() == consultorio
                            && consulta.get(i).getProfissionalID() == profissional.get(j).getId()
                            && "nao".equals(consulta.get(i).getDeletado())) {
                        consulta.get(i).setProfissionalTemp(profissional.get(j).getNome());
                    }
                }
            }

            for (int i = 0; i < consulta.size(); i++) {
                for (int j = 0; j < pacienteTemp.size(); j++) {

                    if (consulta.get(i).getConsultorioID() == consultorio
                            && "nao".equals(consulta.get(i).getDeletado())
                            && consulta.get(i).getPacienteID() == pacienteTemp.get(j).getId()) {

                        for (int k = 0; k < contato.size(); k++) {

                            if (pacienteTemp.get(j).getContatoID() == contato.get(k).getId()) {

                                consulta.get(i).setCelular(contato.get(k).getCelular());
                                consulta.get(i).setFixo(contato.get(k).getFixo());
                                consulta.get(i).setEmail(contato.get(k).getEmail());

                            }
                        }
                    }
                }
            }

            for (int i = 0; i < consulta.size(); i++) {
                for (int j = 0; j < pacienteTemp.size(); j++) {

                    if (consulta.get(i).getConsultorioID() == consultorio
                            && "nao".equals(consulta.get(i).getDeletado())
                            && consulta.get(i).getPacienteID() == pacienteTemp.get(j).getId()
                            && consulta.get(i).getDataConsulta().equals(agora)) {

                        painel.add(consulta.get(i));
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
        return painel;
    }

    public void reagendarConsulta(ConsultaDTO consulta) throws SQLException {

        Connection connection = new MysqlConnectionPool().getConnection();
        try {
            ConsultaDAO consultaDAO = new ConsultaDAO(connection);
            consultaDAO.reagendarConsulta(consulta);

        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            if (connection != null) {
                connection.close();
            }
        }

    }

    public void agendandoConsultaPainel(ConsultaDTO consulta) throws SQLException {

        Connection connection = new MysqlConnectionPool().getConnection();
        try {
            ConsultaDAO consultaDAO = new ConsultaDAO(connection);
            consultaDAO.agendandoConsultaPainel(consulta);

        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            if (connection != null) {
                connection.close();
            }
        }

    }
}
