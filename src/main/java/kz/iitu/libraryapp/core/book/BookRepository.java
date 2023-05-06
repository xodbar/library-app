package kz.iitu.libraryapp.core.book;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

public class BookDAO {

    private static BookDAO INSTANCE;

    private final List<Book> books;
    private Long id;

    private BookDAO() {
        books = new ArrayList<>();
        id = 0L;
    }

    public static BookDAO getInstance() {
        if (INSTANCE == null)
            INSTANCE = new BookDAO();

        return INSTANCE;
    }

    public void addBook(Book book) {
        id += 1;
        book.setId(id);
        books.add(book);
    }

    public Book getById(Long id) {
        try {
            return books.stream()
                    .filter(book -> Objects.equals(book.getId(), id))
                    .collect(Collectors.toList())
                    .get(0);
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    public List<Book> getAll() {
        return books;
    }

    public void updateBook(Book book) {
        if (getById(book.getId()) == null)
            return;

        int index = books.indexOf(book);
        books.set(index, book);
    }

    public void removeBook(Long id) {
        if (getById(id) == null)
            return;

        books.removeIf(getById(id)::equals);
    }
}
