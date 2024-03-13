package dev.cisnux.webservlet;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;
import java.util.concurrent.atomic.AtomicLong;

@WebServlet(value = "/counter")
public class CounterServlet extends HttpServlet {
    private final AtomicLong atomicLong = new AtomicLong(0L);

    @Override
    protected void service(HttpServletRequest req, HttpServletResponse resp) throws IOException {
        long total = atomicLong.incrementAndGet();
        String response = "Total Counter : " + total;
        resp.getWriter().println(response);
    }
}
