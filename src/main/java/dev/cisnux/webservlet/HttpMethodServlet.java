package dev.cisnux.webservlet;

import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@WebServlet(value = "/todos")
public class HttpMethodServlet extends HttpServlet {
    private final List<String> todos = new ArrayList<>();

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws IOException {
        resp.getWriter().println(todos);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) {
        final var todo = req.getParameter("todo");
        Optional.ofNullable(todo)
                .ifPresentOrElse(
                        s -> {
                            todos.add(todo);
                            try {
                                resp.getWriter().println("Add todo\t: " + todo);
                            } catch (IOException e) {
                                throw new RuntimeException(e);
                            }
                        },
                        () -> {
                            try {
                                resp.getWriter().println("Todo parameter must exists");
                            } catch (IOException e) {
                                throw new RuntimeException(e);
                            }
                        }
                );
    }
}
