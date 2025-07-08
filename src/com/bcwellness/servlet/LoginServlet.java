package com.bcwellness.servlet;
import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import com.bcwellness.model.Student;

public class LoginServlet extends HttpServlet {
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        // Test data
        String testEmail = "test@test.com";
        String testPassword = "test";

        // Get form inputs
        String email = request.getParameter("email");
        String password = request.getParameter("password");

        //Validate if a password or email was sent
        if (email.isEmpty() || password.isEmpty()) {
            request.setAttribute("error", "Please enter both email and password");
            request.getRequestDispatcher("login.jsp").forward(request, response);
            return;
        }

        // Fake validation
        if (testEmail.equals(email) && testPassword.equals(password)) {
            // Create session
            HttpSession session = request.getSession();
            session.setAttribute("studentNumber", "1234567890");
            session.setAttribute("name", "John Doe");

            // Redirect to dashboard
            response.sendRedirect("dashboard.jsp");
        } else {
            request.setAttribute("error", "Invalid login details");
            request.getRequestDispatcher("login.jsp").forward(request, response);
        }
    }
}