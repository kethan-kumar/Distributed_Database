package org.sequence;

import org.concurrencycontrol.DBConfig;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;

public class Controller {

    public static void main(String[] args){
        String T1Read = "SELECT * FROM customers WHERE zip_code=01151";
        String T2Read = "SELECT * FROM customers WHERE zip_code=01151";
        String T1Update = "UPDATE customers SET city='T1 CITY' WHERE zip_code=01151";
        String T2Update = "UPDATE customers SET city='T2 CITY' WHERE zip_code=01151";
        IDatabase mysqlDB = new MySqlDB();
        DBConfig dbConfig = new DBConfig();

        Connection connectionT1 =  mysqlDB.getConnection(
                dbConfig.getLocal_hostname(),
                dbConfig.getLocal_portNum(),
                dbConfig.getLocal_database(),
                dbConfig.getLocal_username(),
                dbConfig.getLocal_password());
        Connection connectionT2 =  mysqlDB.getConnection(
                dbConfig.getLocal_hostname(),
                dbConfig.getLocal_portNum(),
                dbConfig.getLocal_database(),
                dbConfig.getLocal_username(),
                dbConfig.getLocal_password());

        display("Executing T1 read statement...");
        ResultSet resultSetT1 = mysqlDB.readQuery(connectionT1, T1Read);
        displayResultSet(resultSetT1);

        display("Executing T2 read statement...");
        ResultSet resultSetT2 = mysqlDB.readQuery(connectionT2, T2Read);
        displayResultSet(resultSetT2);

        display("Executing T1 update statement...");
        mysqlDB.updateQuery(connectionT1, T1Update);

        display("Executing T2 update statement...");
        mysqlDB.updateQuery(connectionT2, T2Update);

        try {
            display("Executing T2 commit!");
            connectionT2.commit();
            display("Executing T1 commit!");
            connectionT1.commit();

            connectionT1.close();
            connectionT2.close();
        } catch (SQLException throwable) {
            display("SQLException during transaction commit!");
            throwable.printStackTrace();
        }
    }
    public static void display(String message){
        System.out.println(message);
    }
    public static void displayResultSet(ResultSet resultSet){
        try {
            ResultSetMetaData metaData = resultSet.getMetaData();
            int columnCount = metaData.getColumnCount();
            System.out.println("+---------------------------------------------------------------------------------------------------+");
            while(resultSet.next()) {
                for (int i = 1; i <=columnCount; i++) {
                    System.out.print(resultSet.getString(i) + "   |  ");
                }
                System.out.println();
            }
            System.out.println("+---------------------------------------------------------------------------------------------------+");
        } catch (SQLException throwable) {
            throwable.printStackTrace();
        }
    }
}