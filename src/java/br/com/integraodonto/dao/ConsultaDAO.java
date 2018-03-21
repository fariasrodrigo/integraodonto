package br.com.integraodonto.dao;

import br.com.integraodonto.dto.ConsultaDTO;
import br.com.integraodonto.features.FormataDataMysql;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class ConsultaDAO {

    private final Connection connection;
    FormataDataMysql formataDataMysql;

    public ConsultaDAO(Connection connection) throws SQLException {
        this.connection = connection;
        this.formataDataMysql = new FormataDataMysql();
    }

    public List<ConsultaDTO> listar() {

        List<ConsultaDTO> results = new ArrayList<>();
        String sql = "SELECT \n"
                + "id,\n"
                + "DATE_FORMAT( data_consulta, \"%d/%m/%Y\" ) as data_consulta,\n"
                + "left(horario_inicio_consulta, 5) as horario_inicio_consulta,\n"
                + "left(horario_fim_consulta, 5) as horario_fim_consulta,\n"
                + "DATE_FORMAT( consulta.agendamento_feito_dia, \"%d/%m/%Y %H:%i:%s\" ) as agendamento_feito_dia,\n"
                + "quem_agendou,\n"
                + "stats_consulta,\n"
                + "obs,\n"
                + "paciente,\n"
                + "profissional,\n"
                + "consultorio,\n"
                + "lembrete_sms,\n"
                + "lembrete_email,\n"
                + "deletado\n"
                + "FROM consulta;";

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

    private ConsultaDTO popular(ResultSet rs) throws SQLException {
        ConsultaDTO consulta = new ConsultaDTO();

        consulta.setId(rs.getLong("id"));
        consulta.setDataConsulta(rs.getString("data_consulta"));
        consulta.setHorarioInicioConsulta(rs.getString("horario_inicio_consulta"));
        consulta.setHorarioFimConsulta(rs.getString("horario_fim_consulta"));
        consulta.setAgendamentoFeitoDia(rs.getString("agendamento_feito_dia"));
        consulta.setQuemAgendou(rs.getLong("quem_agendou"));
        consulta.setStatusConsulta(rs.getString("stats_consulta"));
        consulta.setObs(rs.getString("obs"));
        consulta.setPacienteID(rs.getLong("paciente"));
        consulta.setProfissionalID(rs.getLong("profissional"));
        consulta.setConsultorioID(rs.getLong("consultorio"));
        consulta.setLembreteSMS(rs.getString("lembrete_sms"));
        consulta.setLembreteEMAIL(rs.getString("lembrete_email"));
        consulta.setDeletado(rs.getString("deletado"));

        return consulta;
    }

    public void agendandoConsultaPainel(ConsultaDTO consulta) throws SQLException {

        if (consulta == null) {
            throw new IllegalArgumentException("consulta esta vazio");
        }

        Date dt = new Date();
        DateFormat dateFormat = new SimpleDateFormat("yyyy/MM/dd HH:mm:ss");
        String agora = dateFormat.format(dt);

        connection.setAutoCommit(false);

        String sql = "INSERT INTO consulta (data_consulta, horario_inicio_consulta, horario_fim_consulta, agendamento_feito_dia, quem_agendou, stats_consulta, obs, "
                + "paciente, profissional, consultorio, lembrete_sms, lembrete_email, deletado) "
                + "VALUES (?,?,?,?,?,?,?,?,?,?,?,?,?);";
        try (PreparedStatement stmt = connection.prepareStatement(sql);) {
            stmt.setString(1, formataDataMysql.formataDataMysql(consulta.getDataConsulta()));
            stmt.setString(2, consulta.getHorarioInicioConsulta());
            stmt.setString(3, consulta.getHorarioFimConsulta());
            stmt.setString(4, agora);
            stmt.setLong(5, consulta.getQuemAgendou());
            stmt.setString(6, consulta.getStatusConsulta());
            stmt.setString(7, consulta.getObs());
            stmt.setLong(8, consulta.getPacienteID());
            stmt.setLong(9, consulta.getProfissionalID());
            stmt.setLong(10, consulta.getConsultorioID());
            stmt.setString(11, consulta.getLembreteSMS());
            stmt.setString(12, consulta.getLembreteEMAIL());
            stmt.setString(13, "nao");

            stmt.execute();

            connection.commit();
        } catch (Exception e) {
            connection.rollback();
            e.printStackTrace();
            throw new RuntimeException(e);
        } finally {

        }
    }

    public void reagendarConsulta(ConsultaDTO consulta) throws SQLException {

        String sql = "UPDATE consulta SET data_consulta = ?, horario_inicio_consulta = ?, horario_fim_consulta = ?, obs = ?, stats_consulta = ?, lembrete_sms = ?, lembrete_sms = ? WHERE id = " + consulta.getId() + " and consultorio = " + consulta.getConsultorioID() + " ;";
        try (PreparedStatement stmt = connection.prepareStatement(sql);) {
            stmt.setString(1, formataDataMysql.formataDataMysql(consulta.getDataConsulta()));
            stmt.setString(2, consulta.getHorarioInicioConsulta());
            stmt.setString(3, consulta.getHorarioFimConsulta());
            stmt.setString(4, consulta.getObs());
            stmt.setString(5, consulta.getStatusConsulta());
            stmt.setString(6, consulta.getLembreteSMS());
            stmt.setString(7, consulta.getLembreteEMAIL());
            stmt.execute();
        } catch (SQLException e) {
            e.printStackTrace();
            throw new RuntimeException(e);
        } finally {

        }
    }

    public void deletarConsultasPacienteDeletado(long id) throws SQLException {

        String sql = "UPDATE consulta SET deletado = ? WHERE paciente = " + id + ";";
        try (PreparedStatement stmt = connection.prepareStatement(sql);) {
            stmt.setString(1, "sim");

            stmt.execute();
        } catch (SQLException e) {
            e.printStackTrace();
            throw new RuntimeException(e);
        } finally {

        }
    }
}
