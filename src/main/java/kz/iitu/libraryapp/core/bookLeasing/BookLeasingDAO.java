package kz.iitu.libraryapp.core.bookLeasing;

import kz.iitu.libraryapp.core.exception.leasing.LeasingException;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

public class BookLeasingDAO {

    private static BookLeasingDAO INSTANCE;

    private final List<BookLeasing> leasingList;
    private Long id;

    private BookLeasingDAO() {
        leasingList = new ArrayList<>();
        id = 0L;
    }

    public static BookLeasingDAO getInstance() {
        if (INSTANCE == null)
            INSTANCE = new BookLeasingDAO();

        return INSTANCE;
    }

    public void addLeasing(BookLeasing leasing) throws Exception {
        if (getByStudentIdAndBookId(leasing.getStudentId(), leasing.getBookId()) != null)
            throw new LeasingException("Already taken");

        id += 1;
        leasing.setId(id);
        leasingList.add(leasing);
    }

    public BookLeasing getById(Long id) {
        try {
            return leasingList.stream()
                    .filter(leasing -> Objects.equals(leasing.getId(), id))
                    .collect(Collectors.toList())
                    .get(0);
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    public BookLeasing getByStudentIdAndBookId(Long studentId, Long bookId) {
        try {
            return leasingList.stream()
                    .filter(leasing -> Objects.equals(leasing.getStudentId(), studentId) &&
                            Objects.equals(leasing.getBookId(), bookId))
                    .collect(Collectors.toList())
                    .get(0);
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    public List<BookLeasing> getAllByStudentId(Long studentId) {
        return leasingList.stream()
                .filter(leasing -> Objects.equals(leasing.getStudentId(), studentId))
                .collect(Collectors.toList());
    }

    public List<BookLeasing> getAllByBookId(Long bookId) {
        return leasingList.stream()
                .filter(leasing -> Objects.equals(leasing.getBookId(), bookId))
                .collect(Collectors.toList());
    }

    public List<BookLeasing> getAll() {
        return leasingList;
    }

    public void removeLeasing(Long id) {
        if (getById(id) == null)
            return;

        leasingList.removeIf(getById(id)::equals);
    }
}
