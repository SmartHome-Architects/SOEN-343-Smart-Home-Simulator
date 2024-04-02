package domain.sensors;

import domain.house.Coordinate;

import javax.swing.*;
import java.awt.*;

public class Light {
    private String name;
    private String location;
    private int lightID;
    private boolean isOpen; // true for open, false for close

    private Coordinate lightCoordinates;

    private JLabel lightLabel;

    public Light(String name, String location, int lightID, Coordinate lightCoordinates) {
        this.name = name;
        this.location = location;
        this.lightID = lightID;
        this.isOpen = false;
        this.lightCoordinates = lightCoordinates;
    }

    public String getName() {
        return name;
    }

    public void turnOn() {
        setOpen(true);
    }

    public void turnOff() {
        setOpen(false);
    }

    public void setLightLabel(JLabel lightLabel) {
        this.lightLabel = lightLabel;
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

    public int getLightID() {
        return lightID;
    }

    public void setLightID(int lightID) {
        this.lightID = lightID;
    }

    public boolean isOpen() {
        return isOpen;
    }

    public void setOpen(boolean open) {
        isOpen = open;
        ImageIcon icon;
        if (open == true) {
            icon = new ImageIcon("images/lightOn.png");
        } else {
            icon = new ImageIcon("images/lightOff.png");
        }
        Image scaledImage = icon.getImage().getScaledInstance(30, 30, Image.SCALE_SMOOTH);
        icon.setImage(scaledImage);
        lightLabel.setIcon(icon);
    }


    public int getX(){
        return lightCoordinates.getX();
    }

    public int getY(){
        return lightCoordinates.getY();
    }

    @Override
    public String toString() {
        return "Light{" +
                "name='" + name + '\'' +
                ", location='" + location + '\'' +
                ", lightID=" + lightID +
                ", isOpen=" + isOpen +
                ", lightCoordinates=" + lightCoordinates.getX() + "," + lightCoordinates.getY() +
                '}';
    }
}
