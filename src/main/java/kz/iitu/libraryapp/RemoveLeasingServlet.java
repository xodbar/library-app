package kz.iitu.libraryapp;

import kz.iitu.libraryapp.core.LibraryService;
import kz.iitu.libraryapp.core.jwt.AuthService;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet(name = "removeLeasing", value = "/removeLeasing")
public class RemoveLeasingServlet extends HttpServlet {

    private LibraryService libraryService;
    private AuthService authService;

    @Override
    public void init() throws ServletException {
        libraryService = LibraryService.getInstance();
        authService = AuthService.getInstance();
        super.init();
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        authService.ensureHasAccess(req, resp);
        Long leasingId = Long.valueOf(req.getParameter("leasingId"));
        libraryService.returnBook(leasingId);
        resp.sendRedirect("/leasing");
    }
}
