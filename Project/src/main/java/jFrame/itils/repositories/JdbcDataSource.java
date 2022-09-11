package jFrame.itils.repositories;


import com.zaxxer.hikari.HikariConfig;
import com.zaxxer.hikari.HikariDataSource;

import java.sql.Connection;
import java.sql.SQLException;

public class JdbcDataSource {
    private HikariDataSource dataSource;

    public JdbcDataSource() {
        HikariConfig config = new HikariConfig();
        config.setJdbcUrl("jdbc:postgresql://localhost:5433/postgres");
        dataSource = new HikariDataSource(config);
    }

    public HikariDataSource getDataSource() {
        System.out.println(dataSource);
        return dataSource;
    }

    public Connection getConnection() {
        try {
            return dataSource.getConnection();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }
}
