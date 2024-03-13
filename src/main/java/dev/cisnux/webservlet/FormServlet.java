package dev.cisnux.webservlet;

import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.Cleanup;

import java.io.IOException;
import java.util.Optional;

@WebServlet("/form")
public class FormServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws IOException {
        @Cleanup final var inputStream = FormServlet.class.getResourceAsStream("/html/form.html");
        final var stringBuilder = new StringBuilder();
        final var buffer = new byte[1024];
        int length;
        if (inputStream == null)
            return;

        while ((length = inputStream.read(buffer)) != -1) {
            stringBuilder.append(new String(buffer, 0, length));
        }
        final var html = stringBuilder.toString();
        resp.getWriter().println(html);
    }


    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws IOException {
        final var firstName = req.getParameter("firstName");
        final var lastName = req.getParameter("lastName");
        final var fullName = firstName + " " + lastName;
        final var response = "Hello " + fullName;
        resp.getWriter().println(response);
    }
}
