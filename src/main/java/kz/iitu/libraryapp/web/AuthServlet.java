package kz.iitu.libraryapp.web;

import kz.iitu.libraryapp.core.exception.auth.AuthenticationException;
import kz.iitu.libraryapp.core.jwt.AuthService;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet(name = "authServlet", value = "/login")
public class AuthServlet extends HttpServlet {

    private AuthService authService;

    @Override
    public void init() throws ServletException {
        authService = AuthService.getInstance();
        super.init();
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        RequestDispatcher dispatcher = req.getRequestDispatcher("login.jsp");
        dispatcher.forward(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        try {
            String username = req.getParameter("username");
            String password = req.getParameter("password");

            String token = authService.issueToken(username, password);
            Cookie authCookie = new Cookie("jwt", token);
            authCookie.setMaxAge(authService.getExpirationMinutes() * 60);
            authCookie.setPath("/");

            req.removeAttribute("errorMessage");

            resp.addCookie(authCookie);
            resp.sendRedirect("/books");
        } catch (AuthenticationException e) {
            e.printStackTrace();
            RequestDispatcher dispatcher = req.getRequestDispatcher("login.jsp");
            req.setAttribute("errorMessage", e.getErrorMessage());
            dispatcher.forward(req, resp);
        }
    }
}
