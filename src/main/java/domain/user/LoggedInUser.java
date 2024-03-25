package domain.user;

public class LoggedInUser {

    private String username;
    private String userType;

    public LoggedInUser(String username, String userType){
        this.username = username;
        this.userType = userType;
    }

    public String getUsername() {
        return username;
    }

    public String getUserType() {
        return userType;
    }
}
