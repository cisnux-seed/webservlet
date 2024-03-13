package dev.cisnux.webservlet;

import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.Cookie;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;

@WebServlet(value = "/cookie")
public class CookieServlet extends HttpServlet {
    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws IOException {
        final var cookieName = req.getParameter("name");
        final var cookieValue = req.getParameter("value");

        final var cookie = new Cookie(cookieName, cookieValue);
        cookie.setPath("/");

        resp.addCookie(cookie);
        resp.getWriter().println("Success add cookie " + cookieName + ":" + cookieValue);
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws IOException {
        if (req.getCookies() != null) {
            for (final var cookie : req.getCookies()) {
                resp.getWriter().println("Cookie " + cookie.getName() + ":" + cookie.getValue());
            }
        }
    }
}
