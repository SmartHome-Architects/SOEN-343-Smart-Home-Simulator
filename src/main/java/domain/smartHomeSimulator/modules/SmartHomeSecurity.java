package domain.smartHomeSimulator.modules;

import domain.sensors.Door;
import domain.sensors.Window;
import presentation.Swing.LogEntry;
import presentation.Swing.command.UserAccountManager;

import javax.swing.*;
import java.awt.*;
import java.util.List;
import java.util.Map;

public class SmartHomeSecurity {
    private boolean awayModeActive;
    private final UserAccountManager userAccountManager;
    private List<Window> windows; // List of Window objects
    private List<Door> doors; // List of Door objects
    private Map<Window, JLabel> windowLabels; // Mapping of Window objects to their JLabel components
    private Map<Door, JLabel> doorLabels; // Mapping of Door objects to their JLabel components

    private JTextArea textArea1;

    private String user;

    public SmartHomeSecurity(UserAccountManager userAccountManager, String user, JTextArea textArea1) {
        this.userAccountManager = userAccountManager;
        this.awayModeActive = false;
        this.user = user;
        this.textArea1 = textArea1;
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
                LogEntry.AwayModelog(user, "Away Mode is turned ON.", textArea1);
                System.out.println("Away mode is activated.");
            } else {
                LogEntry.AwayModelog(user, "Away Mode is turned OFF.", textArea1);
                System.out.println("Away mode is deactivated.");
                updateWindowAndDoorIcons(); // Update window and door icons after deactivating away mode
            }
        } else {
            System.out.println("Cannot activate away mode. User must be outside.");
        }
    }

    // Method to check if away mode is active
    public boolean isAwayModeActive() {
        return awayModeActive;
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

    // Method to handle notifications about open windows
    public void handleOpenWindow(Window window) {
        if (awayModeActive) {
            closeAllWindows(); // Close all windows when away mode is activated and a window is open
        }
        // Additional logic can be added here if needed
    }

    // Method to handle notifications about open doors
    public void handleOpenDoor(Door door) {
        if (awayModeActive) {
            closeAllDoors(); // Close all doors when away mode is activated and a door is open
        }
        // Additional logic can be added here if needed
    }
}
