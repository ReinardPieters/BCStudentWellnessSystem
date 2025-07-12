package com.bcwellness.dao;

import com.bcwellness.model.Student;
import java.sql.*;

public class StudentDAO {

    public Student findByEmail(String email) {
        String sql = """
        SELECT student_number,
               name,
               surname,
               email,
               phone,
               password_hash
        FROM   users
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
                        rs.getString("password_hash")
                );
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

}
