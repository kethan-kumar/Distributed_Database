package org.sequence;

public class DBConfig {
    private String local_username = "root";
    private String local_password = "Password#01";
    private String local_hostname = "localhost";
    private String local_database = "ecommerce";
    private String local_portNum = "3306";

    private String remote_username = "root";
    private String remote_password = "Password01";
    private String remote_hostname = "34.93.247.145";
    private String remote_database = "e-commerce";
    private String remote_portNum = "3306";

    public String getLocal_username() {
        return local_username;
    }

    public String getLocal_password() {
        return local_password;
    }

    public String getLocal_hostname() {
        return local_hostname;
    }

    public String getLocal_database() {
        return local_database;
    }

    public String getLocal_portNum() {
        return local_portNum;
    }

    public String getRemote_username() {
        return remote_username;
    }

    public String getRemote_password() {
        return remote_password;
    }

    public String getRemote_hostname() {
        return remote_hostname;
    }

    public String getRemote_database() {
        return remote_database;
    }

    public String getRemote_portNum() {
        return remote_portNum;
    }
}
