package org.sequence;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

public interface IDatabase {
    public Connection getConnection (String hostname, String portNum, String databaseName, String username, String password);
    public ResultSet readQuery(Connection connection, String sqlQuery);
    public PreparedStatement updateQuery(Connection connection, String sqlQuery);
}
