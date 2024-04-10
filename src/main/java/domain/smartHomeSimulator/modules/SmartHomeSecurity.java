package domain.smartHomeSimulator.modules;

import domain.sensors.Door;
import domain.sensors.Window;
import domain.user.Users;
import presentation.Swing.command.UserAccountManager;

import javax.swing.*;
import java.awt.*;
import java.util.List;
import java.util.Map;

public class SmartHomeSecurity {
    private boolean awayModeActive;
    private boolean motionDetectorActive;
    private final UserAccountManager userAccountManager;
    private List<Window> windows; // List of Window objects
    private List<Door> doors; // List of Door objects
    private Map<Window, JLabel> windowLabels; // Mapping of Window objects to their JLabel components
    private Map<Door, JLabel> doorLabels; // Mapping of Door objects to their JLabel components

    public SmartHomeSecurity(UserAccountManager userAccountManager) {
        this.userAccountManager = userAccountManager;
        this.awayModeActive = false;
        this.motionDetectorActive = false;
    }

    // Method to set the windows and their labels
    public void setWindowsAndLabels(List<Window> windows, Map<Window, JLabel> windowLabels) {
        this.windows = windows;
        this.windowLabels = windowLabels;
    }

    // Method to set the doors and their labels
    public void setDoorsAndLabels(List<Door> doors, Map<Door, JLabel> doorLabels) {
        this.doors = doors;
        this.doorLabels = doorLabels;
    }

    // Method to toggle Away Mode
    public void toggleAwayMode() {
        String loggedInUsername = userAccountManager.getLoggedInUsername();
        if (loggedInUsername != null && "Outside".equals(userAccountManager.getUserLocation(loggedInUsername))) {
            awayModeActive = !awayModeActive;
            if (awayModeActive) {
                closeAllWindowsAndDoors(); // Close all windows and doors when away mode is activated
                activateMotionDetector(); // Activate motion detector when away mode is activated
                System.out.println("Away mode is activated.");
            } else {
                System.out.println("Away mode is deactivated.");
                updateWindowAndDoorIcons(); // Update window and door icons after deactivating away mode
                deactivateMotionDetector(); // Deactivate motion detector when away mode is deactivated
            }
        } else {
            System.out.println("Cannot activate away mode. User must be outside.");
        }
    }

    // Method to check if away mode is active
    public boolean isAwayModeActive() {
        return awayModeActive;
    }

    // Method to check if motion detector is active
    public boolean isMotionDetectorActive() {
        return motionDetectorActive;
    }
    public void toggleMotionDetector() {
        if (!isAwayModeActive()) {
            motionDetectorActive = !motionDetectorActive;
            if (motionDetectorActive && isUserInside()) {
                // If motion detector is activated and a user is inside, take necessary action
                System.out.println("Motion detected inside the house.");
                // Implement action here, such as displaying an icon or sending a notification
            }
        } else {
            System.out.println("Cannot activate motion detector when away mode is active.");
        }
    }


    // Method to check if any user is inside when motion detector is active
    public boolean isUserInside() {
        List<String> userLocations = userAccountManager.getAllUsersLocations();
        for (String location : userLocations) {
            if (!"Outside".equals(location)) {
                return true; // At least one user is inside
            }
        }
        return false; // No user is inside
    }

    public String getUserInside() {
        List<String> usernames = userAccountManager.getAllUsernames();
        for (String username : usernames) {
            String location = userAccountManager.getUserLocation(username);
            if (!"Outside".equals(location)) {
                return username; // Return the username of the user inside
            }
        }
        return null; // Return null if no user is inside
    }





    // Method to handle motion detector activation
    private void activateMotionDetector() {
        motionDetectorActive = true;
        System.out.println("Motion detector is activated.");
    }

    // Method to handle motion detector deactivation
    private void deactivateMotionDetector() {
        motionDetectorActive = false;
        System.out.println("Motion detector is deactivated.");
    }

    // Method to close all windows and doors
    public void closeAllWindowsAndDoors() {
        closeAllWindows();
        closeAllDoors();
    }

    // Method to close all windows
    public void closeAllWindows() {
        for (Window window : windows) {
            window.setOpen(false); // Close each window
        }
        System.out.println("All windows are closed.");
        updateWindowIcons(); // Update window icons after closing all windows
    }

    // Method to close all doors
    public void closeAllDoors() {
        for (Door door : doors) {
            door.setOpen(false); // Close each door
        }
        System.out.println("All doors are closed.");
        updateDoorIcons(); // Update door icons after closing all doors
    }

    // Method to update window icons based on their state
    private void updateWindowIcons() {
        for (Window window : windows) {
            JLabel label = windowLabels.get(window);
            if (label != null) {
                ImageIcon icon = new ImageIcon(window.isOpen() ? "images/open.png" : "images/closed.png");
                Image scaledImage = icon.getImage().getScaledInstance(30, 30, Image.SCALE_SMOOTH);
                icon.setImage(scaledImage);
                label.setIcon(icon);
            }
        }
    }

    // Method to update door icons based on their state
    private void updateDoorIcons() {
        for (Door door : doors) {
            JLabel label = doorLabels.get(door);
            if (label != null) {
                ImageIcon icon = new ImageIcon(door.isOpen() ? "images/open.png" : "images/closed.png");
                Image scaledImage = icon.getImage().getScaledInstance(30, 30, Image.SCALE_SMOOTH);
                icon.setImage(scaledImage);
                label.setIcon(icon);
            }
        }
    }

    // Method to update window and door icons
    private void updateWindowAndDoorIcons() {
        updateWindowIcons();
        updateDoorIcons();
    }
}
