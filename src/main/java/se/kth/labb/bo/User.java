package se.kth.labb.bo;

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
}
