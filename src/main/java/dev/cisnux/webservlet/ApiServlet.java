package dev.cisnux.webservlet;

import dev.cisnux.webservlet.data.Greeting;
import dev.cisnux.webservlet.utils.JsonUtil;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;
import java.util.Map;

@WebServlet(value = "/api/greeting")
public class ApiServlet extends HttpServlet {
    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        final var sayHelloRequest = JsonUtil.getObjectMapper().readValue(req.getReader(), Greeting.class);
        final var sayHello = "Hello " + sayHelloRequest.firstName() + " " + sayHelloRequest.lastName();

        final var response = Map.of(
                "data", sayHello
        );
        String jsonResponse = JsonUtil.getObjectMapper().writeValueAsString(response);

        resp.setHeader("Content-Type", "application/json");
        resp.getWriter().println(jsonResponse);

    }
}
