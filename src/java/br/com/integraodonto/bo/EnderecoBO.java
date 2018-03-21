package br.com.integraodonto.bo;

import br.com.integraodonto.dao.EnderecoDAO;
import br.com.integraodonto.dto.EnderecoDTO;
import br.com.integraodonto.pool.MysqlConnectionPool;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;

public class EnderecoBO {

    public EnderecoDTO buscaPorId(long id) throws SQLException {

        Connection connection = new MysqlConnectionPool().getConnection();

        EnderecoDAO enderecoDAO = new EnderecoDAO(connection);

        EnderecoDTO enderecoDTO = new EnderecoDTO();

        List<EnderecoDTO> endereco = enderecoDAO.listar();

        for (int i = 0; i < endereco.size(); i++) {
            if (endereco.get(i).getId() == id) {
                enderecoDTO = endereco.get(i);
            }
        }
        return enderecoDTO;
    }
}
