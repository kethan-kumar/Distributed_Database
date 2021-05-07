package org.concurrencycontrol;

import java.sql.*;

public class ReadStatement implements Runnable{
    private Connection connection;
    private String sqlQuery;

    public ReadStatement(Connection connection, String sqlQuery) {
        this.connection = connection;
        this.sqlQuery = sqlQuery;
    }
    @Override
    public void run() {
            synchronized (this) {
                this.read();
            }
    }
    public void read(){
        ResultSet resultSet = null;
        try {
            Statement statement = connection.createStatement();
            resultSet = statement.executeQuery(sqlQuery);
            displayResultSet(resultSet);
        } catch (SQLException throwable) {
            System.out.println("SQLException occurred during read!");
            throwable.printStackTrace();
        }
    }
    public void displayResultSet(ResultSet resultSet) {
        synchronized (this) {
            try {
                ResultSetMetaData metaData = resultSet.getMetaData();
                int columnCount = metaData.getColumnCount();
                System.out.println("+---------------------------------------------------------------------------------------------------+");
                while (resultSet.next()) {
                    for (int i = 1; i <= columnCount; i++) {
                        System.out.print("   |  " + resultSet.getString(i) + "   |  ");
                    }
                    System.out.println();
                }
                System.out.println("+---------------------------------------------------------------------------------------------------+");
            } catch (SQLException throwable) {
                throwable.printStackTrace();
            }
        }
    }
}
