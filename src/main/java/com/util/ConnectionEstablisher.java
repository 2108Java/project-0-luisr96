package com.util;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class ConnectionEstablisher {

	private static final String URL = "jdbc:postgresql://bank.cap91ijbfbvc.us-east-2.rds.amazonaws.com/postgres";
	private static final String USERNAME = "postgres";
	private static final String PASSWORD = "Lr0694275";
	
	public Connection connect() {
        Connection conn = null;
        try {
            conn = DriverManager.getConnection(URL, USERNAME, PASSWORD);
            System.out.println("Connected to the server successfully.");
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
        return conn;
    }

}