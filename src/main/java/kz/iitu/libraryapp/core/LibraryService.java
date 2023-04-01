package kz.iitu.libraryapp.core;

import kz.iitu.libraryapp.core.book.Book;
import kz.iitu.libraryapp.core.book.BookDAO;
import kz.iitu.libraryapp.core.bookLeasing.BookLeasing;
import kz.iitu.libraryapp.core.bookLeasing.BookLeasingDAO;
import kz.iitu.libraryapp.core.student.Student;
import kz.iitu.libraryapp.core.student.StudentDAO;

import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import java.util.stream.Collectors;

public class LibraryService {

    private static LibraryService INSTANCE;
    private final Logger logger = Logger.getLogger(LibraryService.class.getName());

    private final BookDAO bookDAO;
    private final StudentDAO studentDAO;
    private final BookLeasingDAO bookLeasingDAO;

    private LibraryService() {
        bookDAO = BookDAO.getInstance();
        studentDAO = StudentDAO.getInstance();
        bookLeasingDAO = BookLeasingDAO.getInstance();
    }

    public static LibraryService getInstance() {
        if (INSTANCE == null)
            INSTANCE = new LibraryService();

        return INSTANCE;
    }

    public void addNewBook(String title, String author, String isbn, Integer year, Integer quantity) {
        try {
            Book book = new Book(null, title, author, isbn, year, quantity);
            bookDAO.addBook(book);
            logger.log(Level.INFO, "Added new book: " + book);
        } catch (Exception e) {
            logger.log(Level.WARNING, "Failed to add book, details: " + e.getMessage(), e);
        }
    }

    public void addNewStudent(String name, String surname, String group) {
        try {
            Student student = new Student(null, name, surname, group);
            studentDAO.addStudent(student);
            logger.log(Level.INFO, "Added new student: " + student);
        } catch (Exception e) {
            logger.log(Level.WARNING, "Failed to add student, details: " + e.getMessage(), e);
        }
    }

    public void assignBook(Long studentId, Long bookId) {
        try {
            Student student = studentDAO.getById(studentId);
            Book book = bookDAO.getById(bookId);

            if (student == null || book == null)
                throw new Exception("Book or student does not exist");

            if (book.isNotAvailable())
                throw new Exception("Book is out of stock");

            bookLeasingDAO.addLeasing(new BookLeasing(
                    null,
                    book.getId(),
                    student.getId()
            ));

            book.setQuantity(book.getQuantity() - 1);
            bookDAO.updateBook(book);

            logger.log(Level.INFO, "Assigned book: " + book + " to " + student);
        } catch (Exception e) {
            logger.log(Level.WARNING, "Failed to assign book to student, details: " + e.getMessage(), e);
        }
    }

    public void returnBook(Long studentId, Long bookId) {
        try {
            Student student = studentDAO.getById(studentId);
            Book book = bookDAO.getById(bookId);
            BookLeasing leasing = bookLeasingDAO.getByStudentIdAndBookId(studentId, bookId);

            if (student == null || book == null || leasing == null)
                throw new Exception("Book or student does not exist, or student didn't take this book");

            bookLeasingDAO.removeLeasing(leasing.getId());

            book.setQuantity(book.getQuantity() + 1);
            bookDAO.updateBook(book);

            logger.log(Level.INFO, "Returned book: " + book + " from " + student);
        } catch (Exception e) {
            logger.log(Level.WARNING, "Failed to assign book to student, details: " + e.getMessage(), e);
        }
    }

    public void returnBook(Long leasingId) {
        try {
            BookLeasing leasing = bookLeasingDAO.getById(leasingId);

            if (leasing == null)
                throw new Exception("Leasing does not exist");

            Book book = bookDAO.getById(leasing.getBookId());
            bookLeasingDAO.removeLeasing(leasing.getId());

            book.setQuantity(book.getQuantity() + 1);
            bookDAO.updateBook(book);

            logger.log(Level.INFO, "Returned book: " + book);
        } catch (Exception e) {
            logger.log(Level.WARNING, "Failed to assign book to student, details: " + e.getMessage(), e);
        }
    }

    public List<Book> getAllTakenBooks(Long studentId) {
        List<Book> result = new ArrayList<>();

        bookLeasingDAO.getAllByStudentId(studentId)
                .forEach(leasing -> result.add(bookDAO.getById(leasing.getBookId())));

        return result;
    }

    public List<Book> getAllAvailableBooks() {
        return bookDAO.getAll().stream().filter(book -> !book.isNotAvailable()).collect(Collectors.toList());
    }

    public List<BookLeasingDTO> getAllLeasingDTO() {
        List<BookLeasing> leasingList = bookLeasingDAO.getAll();
        List<BookLeasingDTO> leasingDTOs = new ArrayList<>();

        leasingList.forEach(leasing -> {
            Book book = bookDAO.getById(leasing.getBookId());
            Student student = studentDAO.getById(leasing.getStudentId());
            leasingDTOs.add(new BookLeasingDTO(
                    leasing.getId(),
                    book.toString(),
                    student.toString()
            ));
        });

        return leasingDTOs;
    }

    public List<Book> filterBooksByIsbn(String isbn, List<Book> initial) {
        return initial.stream().filter(book -> book.getIsbn().contains(isbn)).collect(Collectors.toList());
    }

    public List<Book> filterBooksByAuthor(String author, List<Book> initial) {
        return initial.stream().filter(book -> book.getAuthor().contains(author)).collect(Collectors.toList());
    }

    public List<Student> getAllStudents() {
        return studentDAO.getAll();
    }

    public List<Book> getAllBooks() {
        return bookDAO.getAll();
    }

    public List<BookLeasing> getAllLeasing() {
        return bookLeasingDAO.getAll();
    }
}
