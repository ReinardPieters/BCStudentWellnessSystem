package com.bcwellness.servlet;

import javax.servlet.annotation.WebServlet;
import javax.servlet.http.*;
import java.io.IOException;

@WebServlet("/logout")
public class LogoutServlet extends HttpServlet {

    /* ── End-session & redirect ── */
    @Override
    protected void service(HttpServletRequest req, HttpServletResponse res) throws IOException {

        HttpSession session = req.getSession(false);   // existing session only
        if (session != null) session.invalidate();     // clear user data

        res.sendRedirect("login.jsp?msg=loggedout");   // back to login
    }
}
