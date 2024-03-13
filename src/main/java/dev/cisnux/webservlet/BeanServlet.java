package dev.cisnux.webservlet;

import dev.cisnux.webservlet.data.Person;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;
import java.util.Optional;
import java.util.concurrent.atomic.AtomicReference;

@WebServlet(value = "/bean")
public class BeanServlet extends HttpServlet {
    final AtomicReference<Person> person = new AtomicReference<>(new Person("fajra@gmail.com", "cisnux"));

    @Override
    protected void service(HttpServletRequest req, HttpServletResponse resp) throws IOException {
        final var email = req.getParameter("email");
        resp.getWriter().println(person.updateAndGet(person -> person.withEmail(Optional.ofNullable(email).orElseGet(() -> "no email"))));
    }
}
