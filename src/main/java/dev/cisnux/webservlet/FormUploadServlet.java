package dev.cisnux.webservlet;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.MultipartConfig;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.Cleanup;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.UUID;

@WebServlet(urlPatterns = "/form-upload")
@MultipartConfig
public class FormUploadServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws IOException {
        @Cleanup final var inputStream = FormServlet.class.getResourceAsStream("/html/form-upload.html");
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
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        final var name = req.getParameter("name");
        final var profile = req.getPart("profile");

        Files.createDirectories(Path.of("upload"));

        final var uploadLocation = Path.of("upload/" + UUID.randomUUID() + profile.getSubmittedFileName());
        Files.copy(profile.getInputStream(), uploadLocation);
        String html = """
                <html>
                <body>
                Name : $name
                <br>
                Profile : <img width="400px" height="400px" src="/download?file=$profile" />
                </body>
                <html>
                """
                .replace("$name", name)
                .replace("$profile", uploadLocation.getFileName().toString());

        resp.getWriter().println(html);
    }
}
