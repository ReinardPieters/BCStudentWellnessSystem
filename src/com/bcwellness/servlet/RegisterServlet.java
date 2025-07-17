package com.bcwellness.servlet;

import com.bcwellness.model.Student;
import com.bcwellness.service.AuthService;
import com.bcwellness.service.AuthServiceImpl;

import javax.servlet.annotation.WebServlet;
import javax.servlet.http.*;
import javax.servlet.ServletException;
import java.io.IOException;

@WebServlet("/register")
public class RegisterServlet extends HttpServlet {

    /* ── Dependencies ── */
    private final AuthService auth = new AuthServiceImpl();   // business-logic gateway

    /* ── Handle POST /register ── */
    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse res)
            throws ServletException, IOException {

        /* ── Collect form data ── */
        String sn   = req.getParameter("student_number");
        String name = req.getParameter("name");
        String sur  = req.getParameter("surname");
        String email= req.getParameter("email");
        String phone= req.getParameter("phone");
        String pw   = req.getParameter("password");

        /* ── Model to validate & persist ── */
        Student stu = new Student(sn, name, sur, email, phone, pw);

        /* ── Delegated validation / insert ── */
        var errors = auth.register(stu);        // returns list; empty = success

        /* ── On validation failure ── */
        if (!errors.isEmpty()) {
            // 1) Echo errors & original inputs back to the JSP
            req.setAttribute("errors", errors);
            req.setAttribute("student_number", sn);
            req.setAttribute("name", name);
            req.setAttribute("surname", sur);
            req.setAttribute("email", email);
            req.setAttribute("phone", phone);

            // server-side forward: keep form data & errors, render JSP without a client redirect
            req.getRequestDispatcher("register.jsp").forward(req, res);

            return;
        }

        /* ── Success: prompt login ── */
        res.sendRedirect("login.jsp?msg=registered");
    }
}
