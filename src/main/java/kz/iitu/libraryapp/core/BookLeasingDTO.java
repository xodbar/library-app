package kz.iitu.libraryapp.core;

public class BookLeasingDTO {

    private Long leasingId;
    private String bookName;
    private String studentFullName;

    public BookLeasingDTO() {
    }

    public BookLeasingDTO(Long leasingId, String bookName, String studentFullName) {
        this.leasingId = leasingId;
        this.bookName = bookName;
        this.studentFullName = studentFullName;
    }

    public Long getLeasingId() {
        return leasingId;
    }

    public void setLeasingId(Long leasingId) {
        this.leasingId = leasingId;
    }

    public String getBookName() {
        return bookName;
    }

    public void setBookName(String bookName) {
        this.bookName = bookName;
    }

    public String getStudentFullName() {
        return studentFullName;
    }

    public void setStudentFullName(String studentFullName) {
        this.studentFullName = studentFullName;
    }
}
