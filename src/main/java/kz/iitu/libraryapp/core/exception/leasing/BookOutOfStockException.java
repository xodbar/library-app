package kz.iitu.libraryapp.core.exception.leasing;

public class BookOutOfStockException extends LeasingException {
    private final String bookName;

    public BookOutOfStockException(String bookName) {
        super("Book \"" + bookName + "\" is out of stock");
        this.bookName = bookName;
    }

    public String getBookName() {
        return bookName;
    }
}
