package dev.cisnux.webservlet.filter;

import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebFilter;
import jakarta.servlet.http.HttpFilter;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.extern.slf4j.Slf4j;

import javax.swing.text.html.Option;
import java.io.IOException;
import java.util.Optional;

@Slf4j
@WebFilter(urlPatterns = "/*")
public class LoginFilter extends HttpFilter {

    @Override
    protected void doFilter(HttpServletRequest request, HttpServletResponse response, FilterChain chain) throws IOException, ServletException {
        if (request.getRequestURI().equals("/session/login")) {
            chain.doFilter(request, response);
        } else {
            final var session = request.getSession(true);
            final var username = (String) session.getAttribute("username");

            Optional.ofNullable(
                    username
            ).ifPresentOrElse(
                    ignored -> {
                        try {
                            chain.doFilter(request, response);
                        } catch (IOException | ServletException e) {
                            throw new RuntimeException(e);
                        }
                    },

                    () -> {
                        response.setStatus(401);
                        try {
                            response.getWriter().println("You need to login first");
                        } catch (IOException e) {
                            throw new RuntimeException(e);
                        }
                    }
            );
        }
    }
}
