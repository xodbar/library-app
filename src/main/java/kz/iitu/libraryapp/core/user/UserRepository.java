package kz.iitu.libraryapp.core.user;

import kz.iitu.libraryapp.core.exception.auth.RegisterException;

import java.sql.*;
import java.util.ArrayList;
import java.util.Objects;
import java.util.stream.Collectors;

public class UserRepository {

    private static UserRepository INSTANCE;
    private static final String url = "jdbc:postgresql://localhost:5432/iitu?currentSchema=library_app";
    private static final String username = "postgres";
    private static final String password = "postgres";

    private UserRepository() {
        try {
            Class.forName("org.postgresql.Driver").getDeclaredConstructor().newInstance();
            try (Connection conn = DriverManager.getConnection(url, username, password)) {
                conn.close();
                System.out.println("Initial connection to DB successful");
            }
        } catch (Exception ex) {
            System.out.println("Failed to initialize UserDB");
            ex.printStackTrace();
        }
    }

    public static UserRepository getInstance() {
        if (INSTANCE == null)
            INSTANCE = new UserRepository();

        return INSTANCE;
    }

    public void addUser(User user) throws RegisterException {
        if (getByUsername(user.getUsername()) != null)
            throw new RegisterException("User already exists!");

        if (user.getPassword() == null || user.getPassword().isEmpty())
            throw new RegisterException("Password is empty");

        int result = insert(user.getUsername(), user.getPassword());

        if (result == 0)
            throw new RegisterException("Failed to create user");
    }

    public User getUserByUsername(String username) {
        try {
            return getAllUsers().stream()
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
            return getAllUsers().stream()
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
            return getAllUsers().stream().
                    filter(user -> user.getUsername().equals(username)).
                    collect(Collectors.toList()).
                    get(0);
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    public ArrayList<User> getAllUsers() {
        ArrayList<User> users = new ArrayList<>();

        try {
            Class.forName("org.postgresql.Driver").getDeclaredConstructor().newInstance();
            try (Connection conn = DriverManager.getConnection(url, username, password)) {
                Statement statement = conn.createStatement();
                ResultSet resultSet = statement.executeQuery("SELECT * FROM users");
                while (resultSet.next()) {
                    Long id = resultSet.getLong(1);
                    String username = resultSet.getString(2);
                    String password = resultSet.getString(3);
                    User product = new User(id, username, password);
                    users.add(product);
                }
            }
        } catch (Exception ex) {
            ex.printStackTrace();
        }

        return users;
    }

    private int insert(String newUsername, String newPassword) {
        try {
            Class.forName("org.postgresql.Driver").getDeclaredConstructor().newInstance();
            try (Connection conn = DriverManager.getConnection(url, username, password)) {
                String sql = "INSERT INTO users (username, password) Values (?, ?)";
                try (PreparedStatement preparedStatement = conn.prepareStatement(sql)) {
                    preparedStatement.setString(1, newUsername);
                    preparedStatement.setString(2, newPassword);

                    return preparedStatement.executeUpdate();
                }
            }
        } catch (Exception ex) {
            ex.printStackTrace();
        }

        return 0;
    }
}
