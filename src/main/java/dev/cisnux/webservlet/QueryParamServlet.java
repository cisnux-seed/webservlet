package dev.cisnux.webservlet;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;
import java.util.stream.Stream;

@WebServlet(value = "/query")
public class QueryParamServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        final var numbers = req.getParameterValues("number");
        int sum = Stream.of(numbers).mapToInt(Integer::parseInt).sum();

        resp.getWriter().println("Total numbers is " + sum);
    }
}
