package se.kth.labb.ui.dto;

public class UserInfo {
    private int id;
    private String name;
    private String username;
    private String role;

    public UserInfo(int id, String name, String username, String role) {
        this.id = id;
        this.name = name;
        this.username = username;
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

    public String getRole() {
        return role;
    }
}
