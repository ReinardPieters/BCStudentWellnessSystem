package com.bcwellness;

import com.bcwellness.dao.DBConnection;
import com.bcwellness.dao.StudentDAO;
import com.bcwellness.model.Student;
import com.bcwellness.service.AuthService;
import com.bcwellness.service.AuthServiceImpl;

import java.sql.*;

public class Test {

    public static void main(String[] args) throws Exception {
        testDb();            // 1 – basic connection
        testDao();           // 2 – DAO lookup
        testAuthService();   // 3 – password check
    }
    /* ---------- individual mini-tests ---------- */

    private static void testDb() throws SQLException {
        try (Connection c = DBConnection.get();
             PreparedStatement ps = c.prepareStatement("SELECT 1");
             ResultSet rs = ps.executeQuery()) {

            System.out.println("DB reachable? " + rs.next());      // true
        }
    }

    private static void testDao() {
        StudentDAO dao = new StudentDAO();
        Student s = dao.findByEmail("test@test.com");
        System.out.println("Hash fetched: " + s.getPassword());

        System.out.println("DAO finds test user? " +
                (dao.findByEmail("test@test.com") != null));       // true
    }

    private static void testAuthService() {
        AuthService auth = new AuthServiceImpl();
        boolean ok  = auth.login("test@test.com", "test") != null;
        boolean bad = auth.login("test@test.com", "wrong") == null;
        System.out.println("AuthService good pw?  " + ok);         // true
        System.out.println("AuthService bad pw?   " + bad);        // true
    }
}
