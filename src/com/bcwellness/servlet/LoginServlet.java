package com.bcwellness.servlet;

import com.bcwellness.model.Student;
import com.bcwellness.service.AuthService;
import com.bcwellness.service.AuthServiceImpl;

import javax.servlet.annotation.WebServlet;
import javax.servlet.http.*;
import java.io.IOException;

@WebServlet("/login")
public class LoginServlet extends HttpServlet {

    private final AuthService auth = new AuthServiceImpl();

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse res) throws IOException {

        String email = req.getParameter("email");
        String pw    = req.getParameter("password");

        if (email == null || pw == null || email.isBlank() || pw.isBlank()) {
            res.sendRedirect("login.jsp?error=empty");        // JSP shows “enter both fields”
            return;
        }

        Student student = auth.login(email, pw);

        if (student != null) {
            HttpSession session = req.getSession(true);
            session.setAttribute("student", student);
            res.sendRedirect("dashboard.jsp");
        } else {
            res.sendRedirect("login.jsp?error=invalid");
        }
    }
}
