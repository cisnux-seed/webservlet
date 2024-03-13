package dev.cisnux.webservlet;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;
import java.util.Enumeration;

@WebServlet(value = "/header")
public class RequestHeaderServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws IOException {
        final Enumeration<String> names = req.getHeaderNames();

        String name;
        while((name = names.nextElement()) != null){
            final var value = req.getHeader(name);
            resp.getWriter().println("Header " + name + " with value : " + value);
        }
    }
}
