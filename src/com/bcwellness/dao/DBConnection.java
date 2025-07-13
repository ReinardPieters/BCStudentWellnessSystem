package com.bcwellness.dao;

import java.sql.*;

public final class DBConnection {
    private static final String URL  = "jdbc:postgresql://localhost:5432/wellness";
    private static final String USER = "postgres";
    private static final String PASS = "74269";

    static {
        try { Class.forName("org.postgresql.Driver"); }
        catch (ClassNotFoundException e) {
            throw new RuntimeException("PostgreSQL JDBC driver not found", e);
        }
    }

    private DBConnection() {}

    public static Connection get() throws SQLException {
        return DriverManager.getConnection(URL, USER, PASS);
    }
}
