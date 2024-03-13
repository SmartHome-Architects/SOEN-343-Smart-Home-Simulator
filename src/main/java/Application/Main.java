package Application;

import domain.Permission.Permission;
import domain.Permission.WindowPermission;
import domain.house.Coordinate;
import domain.sensors.Window;
import domain.smartHomeSimulator.modules.SmartHomeCoreFunctionality;
import domain.user.Profile;
import presentation.Swing.LoginAndSignUp.AuthenticationManager;
import presentation.Swing.LoginAndSignUp.LogIn;
import presentation.Swing.MainFrame;

import javax.swing.*;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {

            LogIn loginFrame = new LogIn();

            // Action listener for "Login" button
            loginFrame.setLoginActionListener(e -> {
                String email = loginFrame.getEmailText();
                String password = loginFrame.getPasswordText();

                boolean isAuthenticated = AuthenticationManager.authenticateUser(email, password);

                if (isAuthenticated) {
                    // Authentication successful, open dashboard
                    MainFrame mainFrame = new MainFrame();
                    mainFrame.showMainFrame();
                    loginFrame.dispose(); // Close the login frame
                } else {
                    // Authentication failed, show error message
                    JOptionPane.showMessageDialog(loginFrame, "Invalid email or password. Please try again.");
                }
            });

            loginFrame.setVisible(true);
            loginFrame.setLocationRelativeTo(null);

        });


        /*
        // ----- debugging
        //List<Profile> profiles = loadUsersFromFile("database/Users.txt");

       // SmartHomeCoreFunctionality smartHome = new SmartHomeCoreFunctionality(profiles.get(0));

        Coordinate window = new Coordinate(1,2);

        Window livingRoomWindow = new Window("Living Room Window", "Living Room", 1, window);


        System.out.println(livingRoomWindow);

        smartHome.openWindow(livingRoomWindow);
        System.out.println(livingRoomWindow);

        // Deny window permission to the user
       // smartHome.grantWindowPermission(profiles.get(0));
        smartHome.openWindow(livingRoomWindow);
        System.out.println(livingRoomWindow);

        smartHome.closeWindow(livingRoomWindow);
        System.out.println(livingRoomWindow);



        // Save updated user profile to file
        //saveUsersToFile(profiles, "database/Users.txt");



*/



    }

















/*

    private static List<Profile> loadUsersFromFile(String filename) {
        List<Profile> profiles = new ArrayList<>();
        try (Scanner scanner = new Scanner(new File(filename))) {
            while (scanner.hasNextLine()) {
                String line = scanner.nextLine();
                String[] parts = line.split("\\|");
                if (parts.length >= 6) {
                    String username = parts[0];
                    String email = parts[1];
                    String password = parts[2];
                    String type = parts[3];
                    String location = parts[4];
                    boolean hasPermission = Boolean.parseBoolean(parts[5]);
                    List<Permission> permissions = new ArrayList<>();
                    permissions.add(new WindowPermission(hasPermission));
                    profiles.add(new Profile(username, password, type, location, email, permissions));
                }
            }
        } catch (FileNotFoundException e) {
            System.err.println("File not found: " + filename);
            e.printStackTrace();
        }
        return profiles;
    }

    private static void saveUsersToFile(List<Profile> profiles, String filename) {
        try (PrintWriter writer = new PrintWriter(filename)) {
            for (Profile profile : profiles) {
                StringBuilder sb = new StringBuilder();
                sb.append(profile.getUsername()).append("|")
                        .append(profile.getEmail()).append("|")
                        .append(profile.getPassword()).append("|")
                        .append(profile.getType()).append("|")
                        .append(profile.getLocation()).append("|");
                boolean hasPermission = false;
                for (Permission permission : profile.getPermissions()) {
                    if (permission instanceof WindowPermission && ((WindowPermission) permission).isHasWindowPermission()) {
                        hasPermission = true;
                        break;
                    }
                }
                sb.append(hasPermission);
                writer.println(sb.toString());
            }
        } catch (FileNotFoundException e) {
            System.err.println("File not found: " + filename);
            e.printStackTrace();
        }
    }

*/









}