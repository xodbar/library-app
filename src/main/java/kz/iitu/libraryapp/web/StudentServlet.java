package kz.iitu.libraryapp.web;

import kz.iitu.libraryapp.core.LibraryService;
import kz.iitu.libraryapp.core.jwt.AuthService;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet(name = "studentServlet", value = "/students")
public class StudentServlet extends HttpServlet {

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
        RequestDispatcher dispatcher = req.getRequestDispatcher("students.jsp");
        req.setAttribute("students", libraryService.getAllStudents());
        dispatcher.forward(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        authService.ensureHasAccess(req, resp);
        String name = req.getParameter("name");
        String surname = req.getParameter("surname");
        String group = req.getParameter("group");

        libraryService.addNewStudent(name, surname, group);
        resp.sendRedirect("/students");
    }
}
