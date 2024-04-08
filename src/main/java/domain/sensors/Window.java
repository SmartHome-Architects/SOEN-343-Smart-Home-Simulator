package domain.sensors;

import domain.house.Coordinate;
import domain.user.UserSingleton;

import javax.swing.*;
import java.awt.*;
import java.util.Objects;

public class Window {
    private String name;
    private String location;
    private int windowID;
    private boolean isOpen; // true for open, false for close
    private boolean isBlocked; // true for blocked, false for not blocked

    private Coordinate windowCoordinates;

    private JLabel windowLabels;

    private boolean awayModeActive; // Track if away mode is active

    public Window(String name, String location, int windowID, Coordinate windowCoordinates) {
        this.name = name;
        this.location = location;
        this.windowID = windowID;
        this.isOpen = false;
        this.isBlocked = false;
        this.windowCoordinates = windowCoordinates;
    }

    public JLabel getWindowLabel() {
        return windowLabels;
    }

    public void setWindowLabel(JLabel windowLabel) {
        this.windowLabels = windowLabel;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getLocation() {
        return location;
    }

    public void setLocation(String location) {
        this.location = location;
    }

    public int getWindowID() {
        return windowID;
    }

    public void setWindowID(int windowID) {
        this.windowID = windowID;
    }

    public boolean isOpen() {
        return isOpen;
    }

    public void setOpen(boolean open) {
        isOpen = open;
        updateWindowIcon(); // Update window icon when the open/close state changes
    }

    public boolean isBlocked() {
        return isBlocked;
    }

    public void setBlocked(boolean blocked) {
        isBlocked = blocked;
    }

    public int getX() {
        return windowCoordinates.getX();
    }

    public int getY() {
        return windowCoordinates.getY();
    }

    public void setAwayModeActive(boolean awayModeActive) {
        this.awayModeActive = awayModeActive;
        updateWindowIcon(); // Update window icon when away mode state changes
    }

    private void updateWindowIcon() {
        ImageIcon icon;
        if (awayModeActive) {
            // Show closed window icon when away mode is active
            icon = new ImageIcon("images/closed.png");
        } else {
            // Show open or closed window icon based on the current state
            icon = new ImageIcon(isOpen ? "images/open.png" : "images/closed.png");
        }
        Image scaledImage = icon.getImage().getScaledInstance(30, 30, Image.SCALE_SMOOTH);
        icon.setImage(scaledImage);
        windowLabels.setIcon(icon);
    }

    @Override
    public String toString() {
        return "Window{" +
                "name='" + name + '\'' +
                ", location='" + location + '\'' +
                ", windowID=" + windowID +
                ", isOpen=" + isOpen +
                ", isBlocked=" + isBlocked +
                ", windowCoordinates=" + windowCoordinates.getX() + "," + windowCoordinates.getY() +
                '}';
    }
}
