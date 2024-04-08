package domain.user;

import domain.Permission.Permission;

import java.util.List;

public class
LoggedInUser {


    private Users loggedInUser;

   // public LoggedInUser(Users user) {
     //   this.loggedInUser = user;
  //  }

    public LoggedInUser(Users user) {
        this.loggedInUser = findUser(user.getUsername(), "database/Users.txt");
    }

    private Users findUser(String username, String usersFileName) {
        List<Users> usersList = UsersInitializer.initializeUsers(usersFileName);
        for (Users user : usersList) {
            if (user.getUsername().equals(username)) {
                return user;
            }
        }
        return null;
    }

    public Users getLoggedInUser() {
        return loggedInUser;
    }

    public Permission getPermissions() {
        if (loggedInUser != null) {
            return loggedInUser.getPermissions();
        }
        return null;
    }

    public String getLocation() {
        if (loggedInUser != null) {
            return loggedInUser.getLocation();
        }
        return null;
    }

    public String getUserType(){
        if(loggedInUser != null){
            return loggedInUser.getType();
        }
        return null;
    }


    @Override
    public String toString() {
        return "LoggedInUser{" +
                "loggedInUser=" + loggedInUser +
                '}';
    }
}
