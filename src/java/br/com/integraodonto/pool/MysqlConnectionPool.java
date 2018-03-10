package br.com.integraodonto.pool;

import br.com.integraodonto.password.PasswordBox;
import java.sql.Connection;
import java.sql.SQLException;
import javax.sql.DataSource;
import com.mysql.jdbc.jdbc2.optional.MysqlConnectionPoolDataSource;

public class MysqlConnectionPool {

    private final DataSource dataSource;
    PasswordBox passwordBox = new PasswordBox();

    public MysqlConnectionPool() {
        MysqlConnectionPoolDataSource pool = new MysqlConnectionPoolDataSource();
        pool.setUrl(passwordBox.getDatabaseUrl());
        pool.setUser(passwordBox.getDatabaseUsuario());
        pool.setPassword(passwordBox.getDatabaseSenha());

        this.dataSource = pool;
    }

    public Connection getConnection() throws SQLException {
        Connection connection = dataSource.getConnection();
        return connection;
    }
}
