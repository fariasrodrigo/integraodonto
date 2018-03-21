package br.com.integraodonto.bo;

import br.com.integraodonto.dao.ContatoDAO;
import br.com.integraodonto.dto.ContatoDTO;
import br.com.integraodonto.pool.MysqlConnectionPool;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;

public class ContatoBO {

    public ContatoDTO buscaPorId(long id) throws SQLException {

        Connection connection = new MysqlConnectionPool().getConnection();
        ContatoDTO contatoDTO = new ContatoDTO();

        try {
            ContatoDAO contatoDAO = new ContatoDAO(connection);

            List<ContatoDTO> contato = contatoDAO.listar();

            for (int i = 0; i < contato.size(); i++) {
                if (contato.get(i).getId() == id) {
                    contatoDTO = contato.get(i);
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            if (connection != null) {
                connection.close();
            }
        }
        return contatoDTO;
    }
}
