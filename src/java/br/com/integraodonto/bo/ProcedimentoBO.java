package br.com.integraodonto.bo;

import br.com.integraodonto.dao.ProcedimentoDAO;
import br.com.integraodonto.dto.ProcedimentoDTO;
import br.com.integraodonto.pool.MysqlConnectionPool;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class ProcedimentoBO {

    public List<ProcedimentoDTO> todosProcedimentosConsultorio(long consultorio) throws SQLException {

        Connection connection = new MysqlConnectionPool().getConnection();
        List<ProcedimentoDTO> procedimentoDTO = new ArrayList<>();

        try {
            ProcedimentoDAO procedimentoDAO = new ProcedimentoDAO(connection);

            List<ProcedimentoDTO> procedimento = procedimentoDAO.listar();

            for (int i = 0; i < procedimento.size(); i++) {

                if ("nao".equals(procedimento.get(i).getDeletado()) && procedimento.get(i).getConsultorioID() == consultorio) {
                    procedimentoDTO.add(procedimento.get(i));
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            if (connection != null) {
                connection.close();
            }
        }
        return procedimentoDTO;
    }

    public void adiciona(ProcedimentoDTO procedimento) throws SQLException {

        Connection connection = new MysqlConnectionPool().getConnection();
        try {
            ProcedimentoDAO procedimentoDAO = new ProcedimentoDAO(connection);
            procedimentoDAO.adiciona(procedimento);

        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            if (connection != null) {
                connection.close();
            }
        }

    }

    public void alterar(ProcedimentoDTO procedimento) throws SQLException {

        Connection connection = new MysqlConnectionPool().getConnection();
        try {
            ProcedimentoDAO procedimentoDAO = new ProcedimentoDAO(connection);
            procedimentoDAO.alterar(procedimento);

        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            if (connection != null) {
                connection.close();
            }
        }

    }

    public void alterarDeletadoProcedimento(ProcedimentoDTO procedimento) throws SQLException {

        Connection connection = new MysqlConnectionPool().getConnection();
        try {
            ProcedimentoDAO procedimentoDAO = new ProcedimentoDAO(connection);
            procedimentoDAO.alterarDeletadoProcedimento(procedimento);

        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            if (connection != null) {
                connection.close();
            }
        }
    }
}
