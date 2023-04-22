package kz.iitu.libraryapp.web;

import kz.iitu.libraryapp.core.exception.auth.RegisterException;
import kz.iitu.libraryapp.core.user.User;
import kz.iitu.libraryapp.core.user.UserRepository;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet(name = "registerServlet", value = "/register")
public class RegisterServlet extends HttpServlet {

    private UserRepository userRepository;

    @Override
    public void init() throws ServletException {
        userRepository = UserRepository.getInstance();
        super.init();
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        RequestDispatcher dispatcher = req.getRequestDispatcher("register.jsp");
        dispatcher.forward(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        try {
            String username = req.getParameter("username");
            String password = req.getParameter("password");

            userRepository.addUser(new User(null, username, password));
            req.removeAttribute("errorMessage");

            resp.sendRedirect("/login");
        } catch (RegisterException e) {
            RequestDispatcher dispatcher = req.getRequestDispatcher("register.jsp");
            req.setAttribute("errorMessage", e.getErrorMessage());
            dispatcher.forward(req, resp);
        }
    }
}
