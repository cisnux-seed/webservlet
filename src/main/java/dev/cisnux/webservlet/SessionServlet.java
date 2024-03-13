package dev.cisnux.webservlet;

import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;

@WebServlet("/sessions/get")
public class SessionServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws IOException {
        final var session = req.getSession(true);
        final var username = (String) session.getAttribute("username");

        if (username == null) {
            resp.getWriter().println("Your are not login");
        } else {
            resp.getWriter().println("Hello " + username);
        }
    }
}
