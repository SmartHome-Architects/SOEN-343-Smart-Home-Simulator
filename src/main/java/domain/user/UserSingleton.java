package domain.user;

import java.util.List;

import static domain.user.UsersInitializer.initializeUsers;

public class UserSingleton {
    private static LoggedInUser userInstance;

    public static void setUser(LoggedInUser user) {
        userInstance = user;
    }

   // public static LoggedInUser getUser() {
    //    return userInstance;
   // }

    public static LoggedInUser getUser() {
        // Retrieve all users
        List<Users> allUsers = UsersInitializer.getAllUsers();

        // Get the username of the logged-in user from the existing userInstance
        String loggedInUsername = userInstance.getLoggedInUser().getUsername();

        // Find the logged-in user in the list of all users
        for (Users user : allUsers) {
            if (user.getUsername().equals(loggedInUsername)) {
                // Create a new LoggedInUser object with the found user and return it
                return new LoggedInUser(user);
            }
        }

        // Return null if the logged-in user is not found in the list of all users
        return null;
    }



}

