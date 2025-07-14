package com.bcwellness.servlet;

import javax.servlet.annotation.WebServlet;
import javax.servlet.http.*;
import java.io.IOException;

@WebServlet("/logout")
public class LogoutServlet extends HttpServlet {

    // Handles both GET and POST
    @Override
    protected void service(HttpServletRequest req, HttpServletResponse res) throws IOException {

        HttpSession session = req.getSession(false);   // don’t create new
        if (session != null) {
            session.invalidate();
        }

        res.sendRedirect("login.jsp?msg=loggedout");
    }
}
