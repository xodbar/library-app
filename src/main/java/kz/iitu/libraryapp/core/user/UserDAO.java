package kz.iitu.libraryapp.core.user;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

public class UserDAO {

    private final List<User> users;
    private Long id;

    private static UserDAO INSTANCE;

    private UserDAO() {
        users = new ArrayList<>();
        id = 0L;
    }

    public static UserDAO getInstance() {
        if (INSTANCE == null)
            INSTANCE = new UserDAO();

        return INSTANCE;
    }

    public void addUser(User user) throws Exception {
        if (getByUsername(user.getUsername()) != null)
            throw new Exception("User already exists!");

        if (user.getPassword() == null || user.getPassword().isEmpty())
            throw new Exception("Password is empty");

        user.setId(id);
        id += 1;

        users.add(user);
    }

    public User getUserByUsername(String username) {
        try {
            return users.stream()
                    .filter(user -> Objects.equals(user.getUsername(), username))
                    .collect(Collectors.toList())
                    .get(0);
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    public User getUserById(Long id) {
        try {
            return users.stream()
                    .filter(user -> Objects.equals(user.getId(), id))
                    .collect(Collectors.toList())
                    .get(0);
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    public User getByUsername(String username) {
        try {
            return users.stream().
                    filter(user -> user.getUsername().equals(username)).
                    collect(Collectors.toList()).
                    get(0);
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }
}
