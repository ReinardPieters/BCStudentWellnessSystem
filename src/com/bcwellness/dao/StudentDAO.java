package com.bcwellness.dao;

import com.bcwellness.model.Student;
import java.sql.*;

public class StudentDAO {

    /**
     * Returns a Student containing email + stored hash,
     * or null if the user is not found.
     */
    public Student findByEmail(String email) {
        String sql = "SELECT password_hash FROM users WHERE email = ?";

        try (Connection con = DBConnection.get();
             PreparedStatement ps = con.prepareStatement(sql)) {

            ps.setString(1, email);
            ResultSet rs = ps.executeQuery();

            if (rs.next()) {
                String hash = rs.getString("password_hash");
                return new Student(email, hash);
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }
}
