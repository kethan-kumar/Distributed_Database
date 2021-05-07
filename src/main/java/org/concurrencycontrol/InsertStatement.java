package org.concurrencycontrol;

import java.sql.Connection;
import java.sql.SQLException;
import java.sql.Statement;

public class InsertStatement implements Runnable {
    private Connection connection;
    private String sqlQuery;
    public InsertStatement(Connection connection, String sqlQuery) {
        this.connection = connection;
        this.sqlQuery = sqlQuery;
    }
    @Override
    public void run() {
        try {
            Statement statement = connection.createStatement();
            statement.executeUpdate(sqlQuery);
            System.out.println("Successfully inserted!");
        } catch (SQLException throwable) {
            System.out.println("SQLException occurred during insert!");
            throwable.printStackTrace();
        }
    }
}
