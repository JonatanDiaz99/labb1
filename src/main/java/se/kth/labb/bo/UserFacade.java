package se.kth.labb.bo;

import se.kth.labb.ui.dto.UserInfo;

import java.nio.charset.StandardCharsets;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

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
