package com.bcwellness.dao;

import java.sql.*;

public final class DBConnection {
    private static final String URL  = "jdbc:postgresql://localhost:5432/wellness";
    private static final String USER = "postgres";
    private static final String PASS = "secret";

    private DBConnection() {}                     // no instances

    public static Connection get() throws SQLException {
        return DriverManager.getConnection(URL, USER, PASS);
    }
}
