package kz.iitu.libraryapp.web;

import kz.iitu.libraryapp.core.LibraryService;
import kz.iitu.libraryapp.core.exception.leasing.LeasingException;
import kz.iitu.libraryapp.core.jwt.AuthService;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet(name = "leasingServlet", value = "/leasing")
public class LeasingServlet extends HttpServlet {

    private LibraryService libraryService;
    private AuthService authService;

    @Override
    public void init() throws ServletException {
        this.libraryService = LibraryService.getInstance();
        this.authService = AuthService.getInstance();
        super.init();
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        authService.ensureHasAccess(req, resp);
        RequestDispatcher dispatcher = req.getRequestDispatcher("leasing.jsp");
        req.setAttribute("leasingList", libraryService.getAllLeasingDTO());
        req.setAttribute("availableBooks", libraryService.getAllAvailableBooks());
        req.setAttribute("students", libraryService.getAllStudents());
        dispatcher.forward(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        try {
            authService.ensureHasAccess(req, resp);
            Long studentId = Long.valueOf(req.getParameter("studentId"));
            Long bookId = Long.valueOf(req.getParameter("bookId"));

            libraryService.assignBook(studentId, bookId);
            resp.sendRedirect("/leasing");
        } catch (LeasingException e) {
            RequestDispatcher dispatcher = req.getRequestDispatcher("register.jsp");
            req.setAttribute("errorMessage", e.getErrorMessage());
            dispatcher.forward(req, resp);
        }
    }
}
