package org.concurrencycontrol;

import java.sql.Connection;
import java.sql.SQLException;

public class Controller {
    public static void main(String[] args){
        IDatabase mysqlDB = new MySqlDB();
        Queries embeddedTransactions = new Queries();
        DBConfig dbConfig = new DBConfig();

        Connection connection1 =  mysqlDB.getConnection(
                dbConfig.getLocal_hostname(),
                dbConfig.getLocal_portNum(),
                dbConfig.getLocal_database(),
                dbConfig.getLocal_username(),
                dbConfig.getLocal_password());
        Connection connection2 =  mysqlDB.getConnection(
                dbConfig.getRemote_hostname(),
                dbConfig.getRemote_portNum(),
                dbConfig.getRemote_database(),
                dbConfig.getRemote_username(),
                dbConfig.getRemote_password());

        Runnable Txn1Update1 = new UpdateStatement(connection1, embeddedTransactions.getT1Update1_local());
        Runnable Txn1Update2 = new UpdateStatement(connection1, embeddedTransactions.getT1Update2_local());
        Runnable Txn1Read = new ReadStatement(connection2, embeddedTransactions.getT1Read_remote());
        Runnable Txn1Insert = new InsertStatement(connection2, embeddedTransactions.getT1Insert_remote());
        Runnable Txn1Update3 = new UpdateStatement(connection2, embeddedTransactions.getT1Update_remote());

        Runnable Txn2Update1 = new UpdateStatement(connection1, embeddedTransactions.getT2Update1_local());
        Runnable Txn2Update2 = new UpdateStatement(connection1, embeddedTransactions.getT2Update2_local());
        Runnable Txn2Read = new ReadStatement(connection2, embeddedTransactions.getT2Read_remote());
        Runnable Txn2Insert = new InsertStatement(connection2, embeddedTransactions.getT2Insert_remote());
        Runnable Txn2Update3 = new UpdateStatement(connection2, embeddedTransactions.getT2Update_remote());

        try {
            System.out.println("#=============Concurrent Transaction Control==============#");
        /*Change the order of the below sequence to any combination and
         *still the transactions will be execute in serial fashion.
         *Make sure to update insert statement (in Queries.java) with new values to avoid duplicate entry
         *exception (SQLIntegrityConstraintViolationException)
         */
            begin(Txn1Update1);
            begin(Txn2Update1);
            begin(Txn1Read);
            begin(Txn1Update2);
            begin(Txn2Update2);
            begin(Txn2Update3);
            begin(Txn1Insert);
            begin(Txn1Update3);
            begin(Txn2Update1);
            begin(Txn2Update2);
            begin(Txn2Read);
            begin(Txn2Insert);

            connection1.commit();
            connection2.commit();

            connection1.close();
            connection2.close();
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
    }
    public static void begin(Runnable runnable) throws InterruptedException {
        Thread thread  = new Thread(runnable);
        thread.start();
        thread.join();
    }
}