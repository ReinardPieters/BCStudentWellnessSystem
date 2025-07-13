package com.bcwellness.dao;

import com.bcwellness.model.Student;
import com.bcwellness.dao.DBConnection;
import java.sql.*;

public class StudentDAO {

    public boolean insertStudent(Student student) throws SQLException {
        String sql = "INSERT INTO students (student_number, name, surname, email, phone, password) VALUES (?, ?, ?, ?, ?, ?)";

        try (Connection conn = DBConnection.get();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setString(1, student.getStudentNumber());
            stmt.setString(2, student.getName());
            stmt.setString(3, student.getSurname());
            stmt.setString(4, student.getEmail());
            stmt.setString(5, student.getPhone());
            stmt.setString(6, student.getPassword());

            int rowsInserted = stmt.executeUpdate();
            return rowsInserted > 0;
        }
    }

    public Student findByEmail(String email) {
        String sql = """
        SELECT student_number,
               name,
               surname,
               email,
               phone,
               password
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
                        rs.getString("password")
                );
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

}
