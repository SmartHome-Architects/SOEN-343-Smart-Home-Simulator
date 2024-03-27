package domain.user;

public class LoggedInUser {


    private Users loggedInUser;

    public LoggedInUser(Users user) {
        this.loggedInUser = user;
    }

    public Users getLoggedInUser() {
        return loggedInUser;
    }
}
