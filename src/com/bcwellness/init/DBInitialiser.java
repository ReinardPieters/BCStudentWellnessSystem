package com.bcwellness.init;

import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;
import javax.servlet.annotation.WebListener;

import java.sql.*;
import java.util.Enumeration;

@WebListener
public class DBInitialiser implements ServletContextListener {

    private static final String DB_NAME = "wellness";
    private static final String TABLE_NAME = "students";

    @Override
    public void contextInitialized(ServletContextEvent sce) {
        System.out.println("[DBInitialiser] Checking database and table...");

        try {
            Class.forName("org.postgresql.Driver");
            System.out.println("PostgreSQL driver manually loaded.");
        } catch (ClassNotFoundException e) {
            System.err.println("Failed to load PostgreSQL driver: " + e.getMessage());
        }

        // 1. Connect to 'postgres' and create the database if needed
        String systemURL = "jdbc:postgresql://localhost:5432/postgres";
        String dbURL = "jdbc:postgresql://localhost:5432/" + DB_NAME;
        String user = "postgres";
        String pass = "74269";

        try (Connection sysConn = DriverManager.getConnection(systemURL, user, pass);
             Statement sysStmt = sysConn.createStatement()) {

            ResultSet rs = sysStmt.executeQuery("SELECT 1 FROM pg_database WHERE datname = '" + DB_NAME + "'");
            if (!rs.next()) {
                sysStmt.executeUpdate("CREATE DATABASE " + DB_NAME);
                System.out.println("[DBInitialiser] Database '" + DB_NAME + "' created.");
            } else {
                System.out.println("[DBInitialiser] Database already exists.");
            }

        } catch (SQLException e) {
            System.err.println("[DBInitialiser] Failed to create/check database: " + e.getMessage());
            return;
        }

        // 2. Connect to target DB and create table if needed
        try (Connection dbConn = DriverManager.getConnection(dbURL, user, pass);
             Statement stmt = dbConn.createStatement()) {

            DatabaseMetaData meta = dbConn.getMetaData();
            ResultSet rs = meta.getTables(null, null, TABLE_NAME, null);

            if (!rs.next()) {
                String createSQL = """
                    CREATE TABLE students (
                        id SERIAL PRIMARY KEY,
                        student_number VARCHAR(6) UNIQUE NOT NULL,
                        name VARCHAR(50),
                        surname VARCHAR(50),
                        email VARCHAR(100) UNIQUE NOT NULL,
                        phone VARCHAR(20),
                        password TEXT NOT NULL
                    )
                    """;
                stmt.executeUpdate(createSQL);
                System.out.println("[DBInitialiser] Table 'students' created.");
            } else {
                System.out.println("[DBInitialiser] Table 'students' already exists.");
            }

        } catch (SQLException e) {
            System.err.println("[DBInitialiser] Failed to create/check table: " + e.getMessage());
        }

        Enumeration<Driver> drivers = DriverManager.getDrivers();
        while (drivers.hasMoreElements()) {
            System.out.println("🔍 Loaded driver: " + drivers.nextElement().getClass().getName());
        }
    }

    @Override
    public void contextDestroyed(ServletContextEvent sce) {
        System.out.println("[DBInitializer] Web app is shutting down.");
    }
}