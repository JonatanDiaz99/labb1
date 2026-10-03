package se.kth.labb.db;

import se.kth.labb.bo.User;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.List;
import java.util.ArrayList;

public class UserDB {
    public static User getUser(String username) {
        String sql = "SELECT id, name, username, password_hash, role FROM users WHERE username = ?";
        try {
            Connection conn = DBManager.getConnection();
            try (PreparedStatement stmt = conn.prepareStatement(sql)) {
                stmt.setString(1, username);
                try (ResultSet rs = stmt.executeQuery()) {
                    if (rs.next()) {
                        return new User(
                                rs.getInt("id"),
                                rs.getString("name"),
                                rs.getString("username"),
                                rs.getString("password_hash"),
                                rs.getString("role")
                        );
                    }
                    return null;
                }
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public static User createUser(User user) {
        String sql = "INSERT INTO users (name, username, password_hash, role) VALUES (?, ?, ?, ?)";
        try {
            Connection conn = DBManager.getConnection();
            try (PreparedStatement stmt = conn.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS)) {
                stmt.setString(1, user.getName());
                stmt.setString(2, user.getUsername());
                stmt.setString(3, user.getPasswordHash());
                stmt.setString(4, user.getRole());
                stmt.executeUpdate();
                try (ResultSet keys = stmt.getGeneratedKeys()) {
                    if (keys.next()) {
                        return new User(
                                keys.getInt(1),
                                user.getName(),
                                user.getUsername(),
                                user.getPasswordHash(),
                                user.getRole()
                        );
                    }
                }
            }
            throw new RuntimeException("Användaren kunde inte skapas.");
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public static void updateUser(User user) {
        String sql = "UPDATE users SET name = ?, username = ?, password_hash = ?, role = ? WHERE id = ?";
        try {
            Connection conn = DBManager.getConnection();
            try (PreparedStatement stmt = conn.prepareStatement(sql)) {
                stmt.setString(1, user.getName());
                stmt.setString(2, user.getUsername());
                stmt.setString(3, user.getPasswordHash());
                stmt.setString(4, user.getRole());
                stmt.setInt(5, user.getId());
                stmt.executeUpdate();
            }
        }
        catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public static void deleteUser(int id) {
        String sql = "DELETE FROM users WHERE id = ?";
        try {
            Connection conn = DBManager.getConnection();
            try (PreparedStatement stmt = conn.prepareStatement(sql)) {
                stmt.setInt(1, id);
                stmt.executeUpdate();
            }
        }
        catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public static List<User> getAllUsers() {
        String sql = "SELECT id, name, username, password_hash, role FROM users";
        try {
            Connection conn = DBManager.getConnection();
            try (PreparedStatement stmt = conn.prepareStatement(sql)) {
                try (ResultSet rs = stmt.executeQuery()) {
                    List<User> users = new ArrayList<>();
                    while (rs.next()) {
                        users.add(new User(
                                rs.getInt("id"),
                                rs.getString("name"),
                                rs.getString("username"),
                                rs.getString("password_hash"),
                                rs.getString("role")
                        ));
                    }
                    return users;
                }
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public static User getUserById(int id) {
        String sql = "SELECT id, name, username, password_hash, role FROM users WHERE id = ?";
        try {
            Connection conn = DBManager.getConnection();
            try (PreparedStatement stmt = conn.prepareStatement(sql)) {
                stmt.setInt(1, id);
                try (ResultSet rs = stmt.executeQuery()) {
                    if (rs.next()) {
                        return new User(
                                rs.getInt("id"),
                                rs.getString("name"),
                                rs.getString("username"),
                                rs.getString("password_hash"),
                                rs.getString("role")
                        );
                    }
                    return null;
                }
            }
        }
        catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
}
