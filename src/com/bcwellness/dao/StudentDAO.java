package com.bcwellness.dao;

import com.bcwellness.model.Student;
import java.sql.*;

/**
 * DAO-layer helper for the <students> table.
 * Keeps SQL in one place and hides JDBC boiler-plate from the rest of the app.
 */
public class StudentDAO {

    /* ── Insert ── */
    // Adds a new student row; returns true on success
    public boolean insertStudent(Student s) throws SQLException {
        String sql = """
            INSERT INTO students
                   (student_number, name, surname, email, phone, password)
            VALUES (?, ?, ?, ?, ?, ?)
        """;

        try (Connection conn = DBConnection.get();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setString(1, s.getStudentNumber());
            stmt.setString(2, s.getName());
            stmt.setString(3, s.getSurname());
            stmt.setString(4, s.getEmail());
            stmt.setString(5, s.getPhone());
            stmt.setString(6, s.getPassword());

            return stmt.executeUpdate() == 1;
        }
    }

    /* ── Existence checks ── */
    public boolean studentNumberExists(String studentNumber) throws SQLException {
        String sql = "SELECT 1 FROM students WHERE student_number = ?";
        try (Connection c = DBConnection.get();
             PreparedStatement p = c.prepareStatement(sql)) {

            p.setString(1, studentNumber);
            return p.executeQuery().next();
        }
    }

    public boolean emailExists(String email) throws SQLException {
        String sql = "SELECT 1 FROM students WHERE email = ?";
        try (Connection c = DBConnection.get();
             PreparedStatement p = c.prepareStatement(sql)) {

            p.setString(1, email);
            return p.executeQuery().next();
        }
    }

    /* ── Fetch single row ── */
    // Returns a populated Student object, or null if not found
    public Student findByEmail(String email) {
        String sql = """
            SELECT student_number, name, surname, email, phone, password
            FROM   students
            WHERE  email = ?
        """;

        try (Connection con = DBConnection.get();
             PreparedStatement ps = con.prepareStatement(sql)) {

            ps.setString(1, email);
            ResultSet rs = ps.executeQuery();

            if (rs.next()) {
                return new Student(
                        rs.getString("student_number"),
                        rs.getString("name"),
                        rs.getString("surname"),
                        rs.getString("email"),
                        rs.getString("phone"),
                        rs.getString("password"));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }
}
