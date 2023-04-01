package kz.iitu.libraryapp.core.user;

public class UserDBService {

//    private static UserDBService INSTANCE;
//    private Connection connection;
//
//    private UserDBService() {
//        try {
//            String url = "jdbc:postgresql://localhost:5432/iitu?currentSchema=library_app";
//            String username = "postgres";
//            String password = "postgres";
//            Class.forName("org.postgresql.Driver").getDeclaredConstructor().newInstance();
//            try (Connection conn = DriverManager.getConnection(url, username, password)) {
//                connection = conn;
//                System.out.println("Connection to ProductDB successful!");
//            }
//        } catch (Exception ex) {
//            System.out.println("Failed to initialize UserDB");
//            ex.printStackTrace();
//        }
//    }
//
//    public static UserDBService getInstance() {
//        if (INSTANCE == null)
//            INSTANCE = new UserDBService();
//
//        return INSTANCE;
//    }
//
//    public void addUser(User user) throws Exception {
//        if (getByUsername(user.getUsername()) != null)
//            throw new Exception("User already exists!");
//
//        if (user.getPassword() == null || user.getPassword().isEmpty())
//            throw new Exception("Password is empty");
//
//        int result = insert(user.getUsername(), user.getPassword());
//
//        if (result == 0)
//            throw new Exception("Failed to create user");
//    }
//
//    public User getUserByUsername(String username) {
//        try {
//            return getAllUsers().stream()
//                    .filter(user -> Objects.equals(user.getUsername(), username))
//                    .collect(Collectors.toList())
//                    .get(0);
//        } catch (Exception e) {
//            e.printStackTrace();
//            return null;
//        }
//    }
//
//    public User getUserById(Long id) {
//        try {
//            return getAllUsers().stream()
//                    .filter(user -> Objects.equals(user.getId(), id))
//                    .collect(Collectors.toList())
//                    .get(0);
//        } catch (Exception e) {
//            e.printStackTrace();
//            return null;
//        }
//    }
//
//    public User getByUsername(String username) {
//        try {
//            return getAllUsers().stream().
//                    filter(user -> user.getUsername().equals(username)).
//                    collect(Collectors.toList()).
//                    get(0);
//        } catch (Exception e) {
//            e.printStackTrace();
//            return null;
//        }
//    }
//
//    public ArrayList<User> getAllUsers() {
//        ArrayList<User> users = new ArrayList<>();
//
//        try {
//            Statement statement = connection.createStatement();
//            ResultSet resultSet = statement.executeQuery("SELECT * FROM users");
//            while (resultSet.next()) {
//                Long id = resultSet.getLong(1);
//                String username = resultSet.getString(2);
//                String password = resultSet.getString(3);
//                User product = new User(id, username, password);
//                users.add(product);
//            }
//        } catch (Exception ex) {
//            ex.printStackTrace();
//        }
//
//        return users;
//    }
//
//    private int insert(String username, String password) {
//        try {
//            String sql = "INSERT INTO users (username, password) Values (?, ?)";
//            try (PreparedStatement preparedStatement = connection.prepareStatement(sql)) {
//                preparedStatement.setString(1, username);
//                preparedStatement.setString(2, password);
//
//                return preparedStatement.executeUpdate();
//            }
//        } catch (Exception ex) {
//            ex.printStackTrace();
//        }
//
//        return 0;
//    }
}
