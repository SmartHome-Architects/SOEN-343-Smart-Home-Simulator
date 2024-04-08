package domain.user;

import domain.Permission.Permission;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

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

    public static List<Users> initializeUsers(String usersFileName) {
        List<Users> usersList = new ArrayList<>();

        try (Scanner scanner = new Scanner(new File(usersFileName))) {
            while (scanner.hasNextLine()) {
                String[] userData = scanner.nextLine().split("\\|");
                String username = userData[0];
                String email = userData[1];
                String password = userData[2];
                String userType = userData[3];
                String location = userData[4];

                Permission permission = UserPermissionInitializer.initializePermission("database/DoorPermission.txt", "database/LightPermission.txt", "database/WindowPermission.txt", "database/SHHPermission.txt","database/SHPPermission.txt", userType);

                Users user = new Users(username, email, password, userType, location, permission);
                usersList.add(user);
            }
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }

        return usersList;
    }

    public static List<Users> getAllUser() {
        // initial all users and get them
        return initializeUsers("database/" + userInstance.getLoggedInUser().getUsername() + ".txt");
    }



}

