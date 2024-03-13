package dev.cisnux.webservlet;

import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;

@WebServlet("/session/login")
public class LoginServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws IOException {
        final var username = req.getParameter("username");

        final var session = req.getSession(true);
        session.setAttribute("username", username);

        resp.getWriter().println("Login success : " + username);
    }
}
