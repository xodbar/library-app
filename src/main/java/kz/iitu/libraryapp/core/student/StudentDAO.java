package kz.iitu.libraryapp.core.student;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

public class StudentDAO {

    private static StudentDAO INSTANCE;

    private final List<Student> students;
    private Long id;

    private StudentDAO() {
        students = new ArrayList<>();
        id = 0L;
    }

    public static StudentDAO getInstance() {
        if (INSTANCE == null)
            INSTANCE = new StudentDAO();

        return INSTANCE;
    }

    public void addStudent(Student student) {
        id += 1L;
        student.setId(id);
        students.add(student);
    }

    public Student getById(Long id) {
        try {
            return students.stream()
                    .filter(student -> Objects.equals(student.getId(), id))
                    .collect(Collectors.toList())
                    .get(0);
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    public List<Student> getAll() {
        return students;
    }

    public void updateStudent(Student student) {
        if (getById(student.getId()) == null)
            return;

        int index = students.indexOf(student);
        students.set(index, student);
    }

    public void removeStudent(Long id) {
        if (getById(id) == null)
            return;

        students.removeIf(getById(id)::equals);
    }
}
