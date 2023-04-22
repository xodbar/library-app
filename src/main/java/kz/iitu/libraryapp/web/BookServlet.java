package kz.iitu.libraryapp.web;

import kz.iitu.libraryapp.core.LibraryService;
import kz.iitu.libraryapp.core.book.Book;
import kz.iitu.libraryapp.core.jwt.AuthService;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

@WebServlet(name = "bookServlet", value = "/books")
public class BookServlet extends HttpServlet {

    private LibraryService libraryService;
    private AuthService authService;

    @Override
    public void init() throws ServletException {
        libraryService = LibraryService.getInstance();
        authService = AuthService.getInstance();
        super.init();
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        authService.ensureHasAccess(req, resp);

        String isbnFilter = req.getParameter("isbnFilter");
        String authorFilter = req.getParameter("authorFilter");

        List<Book> books = libraryService.getAllBooks();

        if (authorFilter != null && !authorFilter.isEmpty())
            books = libraryService.filterBooksByAuthor(authorFilter, books);

        if (isbnFilter != null && !isbnFilter.isEmpty())
            books = libraryService.filterBooksByIsbn(isbnFilter, books);

        RequestDispatcher dispatcher = req.getRequestDispatcher("books.jsp");
        req.setAttribute("books", books);
        dispatcher.forward(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        authService.ensureHasAccess(req, resp);
        String title = req.getParameter("title");
        String author = req.getParameter("author");
        String isbn = req.getParameter("isbn");
        Integer year = Integer.valueOf(req.getParameter("year"));
        Integer quantity = Integer.valueOf(req.getParameter("quantity"));

        libraryService.addNewBook(title, author, isbn, year, quantity);
        resp.sendRedirect("/books");
    }
}
