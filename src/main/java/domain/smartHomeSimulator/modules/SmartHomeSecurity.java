package domain.smartHomeSimulator.modules;

import domain.sensors.Window;
import presentation.Swing.command.UserAccountManager;

import javax.swing.*;
import java.awt.*;
import java.util.List;
import java.util.Map;

public class SmartHomeSecurity {
    private boolean awayModeActive;
    private final UserAccountManager userAccountManager;
    private List<Window> windows; // Assuming windows is a list of Window objects
    private Map<Window, JLabel> windowLabels; // Mapping of Window objects to their JLabel components

    public SmartHomeSecurity(UserAccountManager userAccountManager) {
        this.userAccountManager = userAccountManager;
        this.awayModeActive = false;
    }

    // Method to set the windows and their labels
    public void setWindowsAndLabels(List<Window> windows, Map<Window, JLabel> windowLabels) {
        this.windows = windows;
        this.windowLabels = windowLabels;
    }

    // Method to toggle Away Mode
    public void toggleAwayMode() {
        String loggedInUsername = userAccountManager.getLoggedInUsername();
        if (loggedInUsername != null && "Outside".equals(userAccountManager.getUserLocation(loggedInUsername))) {
            awayModeActive = !awayModeActive;
            if (awayModeActive) {
                closeAllWindows(); // Close all windows when away mode is activated
                System.out.println("Away mode is activated.");
            } else {
                System.out.println("Away mode is deactivated.");
                // Update window icons after deactivating away mode
                updateWindowIcons();
            }
        } else {
            System.out.println("Cannot activate away mode. User must be outside.");
        }
    }


    // Method to check if away mode is active
    public boolean isAwayModeActive() {
        return awayModeActive;
    }

    // Method to close all windows
    public void closeAllWindows() {
        for (Window window : windows) {
            window.setOpen(false); // Close each window
        }
        System.out.println("All doors and windows are closed.");
        // Update window icons after closing all windows
        updateWindowIcons();
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

    // Method to handle notifications about open windows
    public void handleOpenWindow(Window window) {
        if (awayModeActive) {
            closeAllWindows(); // Close all windows when away mode is activated and a window is open
        }
        // Additional logic can be added here if needed
    }
}
