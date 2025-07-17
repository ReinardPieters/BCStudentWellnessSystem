package com.bcwellness.servlet;

import com.bcwellness.model.Student;
import com.bcwellness.service.AuthService;
import com.bcwellness.service.AuthServiceImpl;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.*;
import java.io.IOException;

@WebServlet("/login")
public class LoginServlet extends HttpServlet {

    /* ── Dependency ── */
    private final AuthService auth = new AuthServiceImpl();

    /* ── Handle POST /login ── */
    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse res) throws IOException {

        /* ── Read form fields ── */
        String email = req.getParameter("email");
        String pw    = req.getParameter("password");

        /* ── Empty-field guard ── */
        if (email == null || pw == null || email.isBlank() || pw.isBlank()) {
            res.sendRedirect("login.jsp?error=empty");   // “enter both fields”
            return;
        }

        /* ── Authenticate ── */
        Student student = auth.login(email, pw);

        if (student != null) {                           // ── Success ──
            HttpSession session = req.getSession(true);
            session.setAttribute("student", student);    // store user in session
            res.sendRedirect("dashboard.jsp");
        } else {                                         // ── Failure ──
            res.sendRedirect("login.jsp?error=invalid");
        }
    }
}
