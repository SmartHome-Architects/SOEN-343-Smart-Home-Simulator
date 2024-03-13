package domain.user;


import domain.Permission.Permission;

import java.util.List;

public class Profile {
    private String username;
    private String password;
    private String type;
    private String location;
    private String email;

    private List<Permission> permissions;


    public Profile(String username, String password, String type, String location, String email, List<Permission> permissions) {
        this.username = username;
        this.password = password;
        this.type = type;
        this.location = location;
        this.email = email;
        this.permissions = permissions;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getLocation() {
        return location;
    }

    public void setLocation(String location) {
        this.location = location;
    }

    public List<Permission> getPermissions() {
        return permissions;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public void setPermissions(List<Permission> permissions) {
        this.permissions = permissions;
    }

    @Override
    public String toString() {
        return "Profile{" +
                "username='" + username + '\'' +
                ", password='" + password + '\'' +
                ", type='" + type + '\'' +
                ", location='" + location + '\'' +
                ", email='" + email + '\'' +
                ", permissions=" + permissions +
                '}';
    }
}