package dev.cisnux.webservlet;


import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;
import java.util.Optional;

@WebServlet(urlPatterns = "/unsafe")
public class UnsafeServlet extends HttpServlet {
    // private String response = "";

    @Override
    protected void service(HttpServletRequest req, HttpServletResponse resp) throws IOException {
        final var name = req.getParameter("name");
        final var sleep = Long.parseLong(Optional.ofNullable(req.getParameter("sleep")).orElseGet(() -> "1000"));

        final var response = "Hello " + name;
        try {
            Thread.sleep(sleep);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }

        resp.getWriter().println(response);
    }
}
