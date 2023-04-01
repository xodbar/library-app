package kz.iitu.libraryapp.core.bookLeasing;

import java.util.Objects;

public class BookLeasing {

    private Long id;
    private Long bookId;
    private Long studentId;

    public BookLeasing() {
    }

    public BookLeasing(Long id, Long bookId, Long studentId) {
        this.id = id;
        this.bookId = bookId;
        this.studentId = studentId;
    }

    @Override
    public boolean equals(Object obj) {
        if (obj == null)
            return false;

        if (!(obj instanceof BookLeasing))
            return false;

        return Objects.equals(this.bookId, ((BookLeasing) obj).bookId) &&
                Objects.equals(this.studentId, ((BookLeasing) obj).studentId);
    }

    @Override
    public String toString() {
        return "BookLeasing{" +
                "id=" + id +
                ", bookId=" + bookId +
                ", studentId=" + studentId +
                '}';
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getBookId() {
        return bookId;
    }

    public void setBookId(Long bookId) {
        this.bookId = bookId;
    }

    public Long getStudentId() {
        return studentId;
    }

    public void setStudentId(Long studentId) {
        this.studentId = studentId;
    }
}
