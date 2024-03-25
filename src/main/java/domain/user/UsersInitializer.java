package domain.user;

import domain.Permission.Permission;
import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class UsersInitializer {
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

                Permission permission = UserPermissionInitializer.initializePermission("database/DoorPermission.txt", "database/LightPermission.txt", "database/WindowPermission.txt", "database/HeaterPermission.txt", userType);

                Users user = new Users(username, email, password, userType, location, permission);
                usersList.add(user);
            }
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }

        return usersList;
    }

    public static List<Users> getAllUsers() {
        // initial all users and get them
        return initializeUsers("database/Users.txt");
    }
}
