package com.javadev.electricitybillingsystem;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.Statement;

public class DataBaseConnectivityMySQL {

    private Connection connection;
    private Statement statement;

    public DataBaseConnectivityMySQL() {
        connectDatabase();
    }

    private void connectDatabase() {
        try {
            connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/elecbillsys","root","plumbed778");
            statement = connection.createStatement();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public Statement getStatement() {
        return statement;
    }
}
