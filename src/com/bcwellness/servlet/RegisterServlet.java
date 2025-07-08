package com.bcwellness.servlet;
import java.util.ArrayList;
import java.util.List;
import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;


public class RegisterServlet extends HttpServlet {
    // Overrides the HttpServlet's doPost method to handle form submissions
    @Override
    //Backend Validation
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String studentNumber = request.getParameter("student_number");
        String name = request.getParameter("name");
        String surname = request.getParameter("surname");
        String email = request.getParameter("email");
        String phone = request.getParameter("phone");
        String password = request.getParameter("password");

        List<String> errors = new ArrayList<>();

        // Validate inputs
        if (studentNumber == null || !studentNumber.matches("\\d{9}")) {
            errors.add("Student number must be exactly 9 digits.");
        }

        if (name == null || !name.matches("[a-zA-Z]{2,}")) {
            errors.add("Invalid name. Use letters only (min 2 characters).");
        }

        if (surname == null || !surname.matches("[a-zA-Z]{2,}")) {
            errors.add("Invalid surname. Use letters only (min 2 characters).");
        }

        if (email == null || !email.matches("^[a-zA-Z0-9._%+-]+@[a-zA-Z0-9.-]+\\.[a-zA-Z]{2,}$")) {
            errors.add("Invalid email address format.");
        }

        if (phone == null || !phone.matches("^0[6-8][0-9]{8}$")) {
            errors.add("Phone number must be a valid SA mobile number (e.g. 0821234567).");
        }

        if (password == null || password.length() < 8 ||
                !password.matches(".*[A-Z].*") ||
                !password.matches(".*[a-z].*") ||
                !password.matches(".*\\d.*") ||
                !password.matches(".*[^A-Za-z0-9].*")) {
            errors.add("Password must be at least 8 characters and include uppercase, lowercase, number, and symbol.");
        }

        // If any errors, send them back
        if (!errors.isEmpty()) {
            request.setAttribute("errors", errors);
            request.getRequestDispatcher("register.jsp").forward(request, response);
            return;
        }

        // Save user with DAO (after hashing password) — TODO

        response.sendRedirect("login.jsp");
    }
}