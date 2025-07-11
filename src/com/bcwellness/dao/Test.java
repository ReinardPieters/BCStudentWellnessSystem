package com.bcwellness.dao;

import java.sql.*;

public class Test {
    public static void main(String[] args) throws Exception {
        try (Connection c = DBConnection.get();
             PreparedStatement ps = c.prepareStatement("SELECT 1");
             ResultSet rs = ps.executeQuery()) {

            System.out.println("DB reachable? " + rs.next());   // expect “true”
        }
    }
}
