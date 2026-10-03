package se.kth.labb.bo;

import se.kth.labb.ui.dto.UserInfo;

import java.nio.charset.StandardCharsets;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.ArrayList;
import java.util.List;

public class UserFacade {
    public static UserInfo login(String username, String password) {
        User user = User.findByUsername(username);
        if (user == null || password == null) {
            return null;
        }
        if (!user.getPasswordHash().equals(hashPassword(password))) {
            return null;
        }
        return new UserInfo(user.getId(), user.getName(), user.getUsername(), user.getRole());
    }

    public static UserInfo createUser(String name, String username, String password, String role) {
        String passwordHash = hashPassword(password);
        User user = User.create(name, username, passwordHash, role);
        return new UserInfo(user.getId(), user.getName(), user.getUsername(), user.getRole());
    }

    public static UserInfo updateUser(int id, String name, String username, String password, String role) {
        User user = User.update(id, name, username, hashPassword(password), role);
        if (user == null) {
            return null;
        }
        return new UserInfo(user.getId(), user.getName(), user.getUsername(), user.getRole());
    }

    public static UserInfo deleteUser(int id) {
        User user = User.delete(id);
        if (user == null) {
            return null;
        }
        return new UserInfo(user.getId(), user.getName(), user.getUsername(), user.getRole());
    }

    public static List<UserInfo> getAllUsers() {
        List<User> users = User.getAllUsers();
        List<UserInfo> userInfos = new ArrayList<>();
        for (User user : users) {
            userInfos.add(new UserInfo(user.getId(), user.getName(), user.getUsername(), user.getRole()));
        }
        return userInfos;
    }

    private static String hashPassword(String password) {
        try {
            byte[] hash = MessageDigest.getInstance("SHA-256")
                    .digest(password.getBytes(StandardCharsets.UTF_8));
            StringBuilder hex = new StringBuilder(hash.length * 2);
            for (byte value : hash) {
                hex.append(String.format("%02x", value));
            }
            return hex.toString();
        } catch (NoSuchAlgorithmException e) {
            throw new IllegalStateException(e);
        }
    }
}
