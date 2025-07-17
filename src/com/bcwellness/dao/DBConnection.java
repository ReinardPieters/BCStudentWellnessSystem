package com.bcwellness.dao;

import java.sql.*;

public final class DBConnection {
    private static final String URL  = "jdbc:postgresql://localhost:5432/wellness";/**JDBC URL pointing PostgreSQL database welness*/
    private static final String USER = "postgres";/*Database username for authentication**/
    private static final String PASS = "74269";/**Password associated with database user*/

    static {
        try { Class.forName("org.postgresql.Driver"); } /**Loads PostgreSQL JDBC driver class into memory*/
        catch (ClassNotFoundException e) {
            throw new RuntimeException("PostgreSQL JDBC driver not found", e);/**Throws runtime exception if driver class is not found*/
        }
    }

    private DBConnection() {} /**Private constructor to prevent instantiation of the utility class*/

    public static Connection get() throws SQLException {
        return DriverManager.getConnection(URL, USER, PASS);/**Returns established connection using driver*/
    }
}
