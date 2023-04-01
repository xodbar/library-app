package kz.iitu.libraryapp.core.book;

import java.io.Serializable;
import java.util.Objects;

public class Book implements Serializable {

    private Long id;
    private String title;
    private String author;
    private String isbn;
    private Integer year;
    private Integer quantity;

    public Book() {
    }

    public Book(Long id, String title, String author, String isbn, Integer year, Integer quantity) {
        this.id = id;
        this.title = title;
        this.author = author;
        this.isbn = isbn;
        this.year = year;
        this.quantity = quantity;
    }

    @Override
    public boolean equals(Object obj) {
        if (obj == null)
            return false;

        if (!(obj instanceof Book))
            return false;

        return Objects.equals(this.id, ((Book) obj).id) &&
                Objects.equals(this.isbn, ((Book) obj).isbn);
    }

    public boolean isNotAvailable() {
        return this.quantity <= 0;
    }

    @Override
    public String toString() {
        return "Book{" +
                "id=" + id +
                ", title='" + title + '\'' +
                ", author='" + author + '\'' +
                ", isbn='" + isbn + '\'' +
                ", year=" + year +
                ", quantity=" + quantity +
                '}';
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getAuthor() {
        return author;
    }

    public void setAuthor(String author) {
        this.author = author;
    }

    public String getIsbn() {
        return isbn;
    }

    public void setIsbn(String isbn) {
        this.isbn = isbn;
    }

    public Integer getYear() {
        return year;
    }

    public void setYear(Integer year) {
        this.year = year;
    }

    public Integer getQuantity() {
        return quantity;
    }

    public void setQuantity(Integer quantity) {
        this.quantity = quantity;
    }
}
