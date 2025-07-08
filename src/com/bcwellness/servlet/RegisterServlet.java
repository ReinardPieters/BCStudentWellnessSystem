package com.bcwellness.servlet;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class RegisterServlet extends HttpServlet {
    //Backend Validation
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String studentNumber = request.getParameter("student_number");
        String name = request.getParameter("name");
        String surname = request.getParameter("surname");
        String email = request.getParameter("email");
        String phone = request.getParameter("phone");
        String password = request.getParameter("password");

        // Validation Flags
        String errorMessage = null;

        //Student number: digits only, e.g., 9 digits
        if (studentNumber == null || !studentNumber.matches("\\d{9}")) {
            errorMessage = "Student number must be exactly 9 digits.";
        }

        //Name & Surname: not empty, letters only
        else if (name == null || !name.matches("[a-zA-Z]{2,}")) {
            errorMessage = "Invalid name. Use letters only.";
        }
        else if (surname == null || !surname.matches("[a-zA-Z]{2,}")) {
            errorMessage = "Invalid surname. Use letters only.";
        }

        // Email format check
        else if (email == null || !email.matches("^[a-zA-Z0-9._%+-]+@[a-zA-Z0-9.-]+\\.[a-zA-Z]{2,}$")) {
            errorMessage = "Invalid email address format.";
        }

        // South African phone number
        else if (phone == null || !phone.matches("^0[6-8][0-9]{8}$")) {
            errorMessage = "Phone number must be a valid SA mobile number (e.g. 0821234567).";
        }

        //Password: at least 6 characters
        else if (password == null || password.length() < 6) {
            errorMessage = "Password must be at least 6 characters.";
        }

        // If there's an error, redirect back with error message
        if (errorMessage != null) {
            request.setAttribute("error", errorMessage);
            request.getRequestDispatcher("register.jsp").forward(request, response);
            return;
        }

        // If valid, hash password and save user use DAO


        // Redirect to login page if success
        response.sendRedirect("login.jsp");
    }
}