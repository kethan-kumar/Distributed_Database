package org.sequence;

import java.sql.*;

public class MySqlDB implements IDatabase{
    Connection connection = null;
    PreparedStatement preparedStatement = null;
    @Override
    public Connection getConnection(String hostname, String portNum, String databaseName, String username, String password) {
        try {
            connection = DriverManager.getConnection("jdbc:mysql://"+hostname+":3306/"+databaseName,
                    username,password);
            connection.setAutoCommit(false);
        } catch (SQLException throwable) {
            System.out.println("SQLException during connection establishment...");
            throwable.printStackTrace();
        }
        return connection;
    }
    @Override
    public ResultSet readQuery(Connection connection, String sqlQuery) {
        ResultSet resultSet = null;
        try {
            Statement statement = connection.createStatement();
            resultSet = statement.executeQuery(sqlQuery);
            return resultSet;
        } catch (SQLException throwable) {
            System.out.println("SQLException occurred during read!");
            throwable.printStackTrace();
        }
        return null;
    }
    @Override
    public PreparedStatement updateQuery(Connection connection, String sqlQuery) {
        try {
            preparedStatement = connection.prepareStatement(sqlQuery);
            preparedStatement.executeUpdate();
            System.out.println("Successfully updated!");
        } catch (SQLException throwable) {
            System.out.println("SQLException occurred during update!");
            throwable.printStackTrace();
        }
        return preparedStatement;
    }
}
