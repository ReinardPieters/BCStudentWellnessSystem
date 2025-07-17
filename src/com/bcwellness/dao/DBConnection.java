package com.bcwellness.dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

/*
 * Utility class for creating JDBC connections to the Wellness PostgreSQL DB.
 * Kept `final` + private constructor so it can’t be instantiated.
 */
public final class DBConnection {
    // ── Configuration (read from environment first, else default) ──
    private static final String URL  = System.getenv().getOrDefault("WELLNESS_DB_URL", "jdbc:postgresql://localhost:5432/wellness");
    private static final String USER = System.getenv().getOrDefault("WELLNESS_DB_USER", "postgres");
    private static final String PASS = System.getenv().getOrDefault("WELLNESS_DB_PASS", "74269");

    // ── Load the PostgreSQL driver once when the class is first used ──
    static {
        try {
            Class.forName("org.postgresql.Driver");     /*Loads PostgreSQL JDBC driver class into memory*/
        } catch (ClassNotFoundException e) {
            throw new RuntimeException("PostgreSQL JDBC driver not found", e);      /*Throws runtime exception if driver class is not found*/
        }
    }

    // ── Prevent instantiation ──
    private DBConnection() {}   /*Private constructor to prevent instantiation of the utility class*/

    // ── Provide a new DB connection on demand ──
    public static Connection get() throws SQLException {
        return DriverManager.getConnection(URL, USER, PASS);        /*Returns established connection using driver*/
    }
}
