package com.bcwellness.servlet;

import com.bcwellness.dao.StudentDAO;
import com.bcwellness.model.Student;
import com.bcwellness.service.AuthService;
import com.bcwellness.service.AuthServiceImpl;
import com.bcwellness.util.PasswordUtil;
import java.util.ArrayList;
import java.util.List;
import java.io.IOException;
import java.sql.SQLException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;;

@WebServlet("/register")
public class RegisterServlet extends HttpServlet {

    private final AuthService auth = new AuthServiceImpl();

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String studentNumber = request.getParameter("student_number");
        String name = request.getParameter("name");
        String surname = request.getParameter("surname");
        String email = request.getParameter("email");
        String phone = request.getParameter("phone");
        String password = request.getParameter("password");

        Student student = new Student(studentNumber, name, surname, email, phone, password);

        var errors = auth.register(student);

        // If any errors, send them back
        if (!errors.isEmpty()) {
            request.setAttribute("errors", errors);

            // Set the original input values back to the request attributes
            request.setAttribute("student_number", request.getParameter("student_number"));
            request.setAttribute("name", request.getParameter("name"));
            request.setAttribute("surname", request.getParameter("surname"));
            request.setAttribute("email", request.getParameter("email"));
            request.setAttribute("phone", request.getParameter("phone"));

            // Also set error message
            request.setAttribute("error", "Registration failed: something went wrong.");

            request.getRequestDispatcher("register.jsp").forward(request, response);
            return;
        }
        response.sendRedirect("login.jsp?msg=registered");
    }
}