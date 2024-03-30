package domain.sensors;

import domain.house.Coordinate;

import javax.swing.*;
import java.awt.*;

public class Door {
    private String name;
    private String location;
    private int doorID;
    private boolean isOpen; // true for open, false for close

    private Coordinate doorCoordinates;

    private JLabel doorLabel;

    public Door(String name, String location, int doorID,Coordinate doorCoordinates) {
        this.name = name;
        this.location = location;
        this.doorID = doorID;
        this.isOpen = false;
        this.doorCoordinates = doorCoordinates;
    }

    public String getName() {
        return name;
    }

    public void setDoorLabel(JLabel doorLabel) {
        this.doorLabel = doorLabel;
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

    public int getDoorID() {
        return doorID;
    }

    public void setDoorID(int doorID) {
        this.doorID = doorID;
    }

    public boolean isOpen() {
        return isOpen;
    }

    public void setOpen(boolean open) {
        isOpen = open;
        ImageIcon icon;
        if (open == false) {
            icon = new ImageIcon("images/closed.png");
        } else {
            icon = new ImageIcon("images/open.png");
        }
        Image scaledImage = icon.getImage().getScaledInstance(30, 30, Image.SCALE_SMOOTH);
        icon.setImage(scaledImage);
        doorLabel.setIcon(icon);
    }

    public int getX(){
        return doorCoordinates.getX();
    }

    public int getY(){
        return doorCoordinates.getY();
    }

    @Override
    public String toString() {
        return "Door{" +
                "name='" + name + '\'' +
                ", location='" + location + '\'' +
                ", doorID=" + doorID +
                ", isOpen=" + isOpen +
                ", doorCoordinates=" + doorCoordinates.getX() + "," +doorCoordinates.getY()+
                '}';
    }
}
