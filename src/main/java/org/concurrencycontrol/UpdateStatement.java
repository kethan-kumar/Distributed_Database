package org.concurrencycontrol;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class UpdateStatement implements Runnable {
    private Connection connection;
    private PreparedStatement preparedStatement;
    private String sqlQuery;
    public UpdateStatement(Connection connection, String sqlQuery) {
        this.connection = connection;
        this.sqlQuery = sqlQuery;
    }
    @Override
    public void run() {
        try {
            preparedStatement = connection.prepareStatement(sqlQuery);
            preparedStatement.executeUpdate();
            System.out.println("Successfully updated!");
        } catch (SQLException throwable) {
            System.out.println("SQLException occurred during update!");
            throwable.printStackTrace();
        }
    }
}
