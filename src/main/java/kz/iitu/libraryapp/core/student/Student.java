package kz.iitu.libraryapp.core.student;

import java.util.Objects;

public class Student {

    private Long id;
    private String name;
    private String surname;
    private String group;

    public Student() {
    }

    public Student(Long id, String name, String surname, String group) {
        this.id = id;
        this.name = name;
        this.surname = surname;
        this.group = group;
    }

    @Override
    public boolean equals(Object obj) {
        if (obj == null)
            return false;

        if (!(obj instanceof Student))
            return false;

        return Objects.equals(this.id, ((Student) obj).id) &&
                Objects.equals(this.name, ((Student) obj).name) &&
                Objects.equals(this.surname, ((Student) obj).surname) &&
                Objects.equals(this.group, ((Student) obj).group);
    }

    @Override
    public String toString() {
        return "Student{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", surname='" + surname + '\'' +
                ", group='" + group + '\'' +
                '}';
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getSurname() {
        return surname;
    }

    public void setSurname(String surname) {
        this.surname = surname;
    }

    public String getGroup() {
        return group;
    }

    public void setGroup(String group) {
        this.group = group;
    }
}
