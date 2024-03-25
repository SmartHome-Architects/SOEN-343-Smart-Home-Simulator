package domain.user;

import domain.Permission.Permission;

public class Users {
    private String username;
    private String email;
    private String password;
    private String type; // Parent, Children or Guest
    private String location;
    private Permission permissions;

    public Users(String username, String email, String password, String type, String location, Permission permissions) {
        this.username = username;
        this.email = email;
        this.password = password;
        this.type = type;
        this.location = location;
        this.permissions = permissions;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
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

    public Permission getPermissions() {
        return permissions;
    }

    public void setPermissions(Permission permissions) {
        this.permissions = permissions;
    }

    @Override
    public String toString() {
        return "Users{" +
                "username='" + username + '\'' +
                ", email='" + email + '\'' +
                ", password='" + password + '\'' +
                ", type='" + type + '\'' +
                ", location='" + location + '\'' +
                ", permissions=" + permissions +
                '}';
    }
}
