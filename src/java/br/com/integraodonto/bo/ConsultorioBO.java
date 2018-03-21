package br.com.integraodonto.bo;

import br.com.integraodonto.dao.ConsultorioDAO;
import br.com.integraodonto.dto.ConsultorioDTO;
import br.com.integraodonto.pool.MysqlConnectionPool;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;

public class ConsultorioBO {

    public ConsultorioDTO buscaPorId(long id) throws SQLException {

        Connection connection = new MysqlConnectionPool().getConnection();

        ConsultorioDAO consultorioDAO = new ConsultorioDAO(connection);

        ConsultorioDTO consultorioDTO = new ConsultorioDTO();

        List<ConsultorioDTO> consultorio = consultorioDAO.listar();

        for (int i = 0; i < consultorio.size(); i++) {
            if (consultorio.get(i).getId() == id) {
                consultorioDTO = consultorio.get(i);
            }
        }
        return consultorioDTO;
    }
}
