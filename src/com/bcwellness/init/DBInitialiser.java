package com.bcwellness.init;

import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;
import javax.servlet.annotation.WebListener;

import java.sql.*;
import java.util.Enumeration;

/*  ── Auto-provision database & table on app start-up ────────────────
    • Runs once when the web-app boots (Servlet 3.0 @WebListener).
    • Ensures Postgres DB  ➜  “wellness”  and table  ➜  “students”
      exist before the rest of the app begins to use them.          */
@WebListener
public class DBInitialiser implements ServletContextListener {

    /* ── Config ── */
    // Identifiers
    private static final String DB_NAME = System.getenv().getOrDefault("WELLNESS_DB_NAME", "wellness");
    private static final String TABLE   = System.getenv().getOrDefault("WELLNESS_TABLE", "students");
    // JDBC URLs
    private static final String SYS_URL = System.getenv().getOrDefault("WELLNESS_SYS_URL", "jdbc:postgresql://localhost:5432/postgres");
    private static final String DB_URL  = System.getenv().getOrDefault("WELLNESS_DB_URL", "jdbc:postgresql://localhost:5432/" + DB_NAME);
    // Credentials
    private static final String USER = System.getenv().getOrDefault("WELLNESS_DB_USER", "postgres");
    private static final String PASS = System.getenv().getOrDefault("WELLNESS_DB_PASS", "74269");

    /* ── app boot ── */
    @Override
    public void contextInitialized(ServletContextEvent sce) {

        System.out.println("[DBInitialiser] verifying database + table …");

        /* ── make sure JDBC driver is present ── */
        try { Class.forName("org.postgresql.Driver"); }
        catch (ClassNotFoundException ex) {
            throw new IllegalStateException("PostgreSQL driver not on classpath", ex);
        }

        /* ── 1. create DB if missing ── */
        try (Connection sys = DriverManager.getConnection(SYS_URL, USER, PASS);
             Statement  st  = sys.createStatement()) {

            ResultSet rs = st.executeQuery(
                    "SELECT 1 FROM pg_database WHERE datname = '" + DB_NAME + "'");
            if (!rs.next()) {
                st.executeUpdate("CREATE DATABASE " + DB_NAME);
                System.out.println("  • database created");
            } else {
                System.out.println("  • database already present");
            }
        } catch (SQLException e) {
            System.err.println("  !! cannot create/check database → " + e.getMessage());
            return;                           // abort further checks
        }

        /* ── 2. create table if missing ── */
        try (Connection db = DriverManager.getConnection(DB_URL, USER, PASS);
             Statement  st = db.createStatement()) {

            ResultSet rs = db.getMetaData()
                    .getTables(null, null, TABLE, null);

            if (!rs.next()) {
                st.executeUpdate("""
                    CREATE TABLE students (
                        id             SERIAL PRIMARY KEY,
                        student_number VARCHAR(6)  UNIQUE NOT NULL,
                        name           VARCHAR(50),
                        surname        VARCHAR(50),
                        email          VARCHAR(100) UNIQUE NOT NULL,
                        phone          VARCHAR(20),
                        password       TEXT NOT NULL
                    )""");
                System.out.println("  • table created");
            } else {
                System.out.println("  • table already present");
            }
        } catch (SQLException e) {
            System.err.println("  !! cannot create/check table → " + e.getMessage());
        }

        /* ── log active JDBC drivers ── */
        Enumeration<Driver> drivers = DriverManager.getDrivers();
        while (drivers.hasMoreElements()) {
            System.out.println("  🔍 driver: " + drivers.nextElement().getClass().getName());
        }
    }

    /* ── app shutdown ── */
    @Override
    public void contextDestroyed(ServletContextEvent sce) {
        System.out.println("[DBInitialiser] application shutting down");
    }
}
