package se.kth.labb.bo;

import se.kth.labb.db.UserDB;
import java.util.List;

public class User {

    private final int id;
    private final String name;
    private final String username;
    private final String passwordHash;
    private final String role;

    public User(int id, String name, String username, String passwordHash, String role) {
        this.id = id;
        this.name = name;
        this.username = username;
        this.passwordHash = passwordHash;
        this.role = role;
    }

    public int getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public String getUsername() {
        return username;
    }

    public String getPasswordHash() {
        return passwordHash;
    }

    public String getRole() {
        return role;
    }

    public static User findByUsername(String username) {
        return UserDB.getUser(username);
    }

    public static User findById(int id) {
        return UserDB.getUserById(id);
    }

    public static List<User> getAllUsers() {
        return UserDB.getAllUsers();
    }

    public static User create(String name, String username, String passwordHash, String role) {
        return UserDB.createUser(new User(0, name, username, passwordHash, role));
    }

    public static User update(int id, String name, String username, String passwordHash, String role) {
        if (findById(id) == null) {
            return null;
        }
        
        User user = new User(id, name, username, passwordHash, role);
        UserDB.updateUser(user);
        return user;
    }

    public static User delete(int id) {
        User user = User.findById(id);
        if (user == null) {
            return null;
        }
        UserDB.deleteUser(id);
        return user;
    }
}
