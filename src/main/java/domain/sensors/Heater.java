package domain.sensors;

import domain.house.Coordinate;

import javax.swing.*;
import java.awt.*;

public class Heater extends TempControlUnit {
    public Heater(int id, Coordinate coordinates) {
        super(id,coordinates);
    }

    @Override
    public void turnOn() {
        ImageIcon icon;
        icon = new ImageIcon("images/heating.png");
        Image scaledImage = icon.getImage().getScaledInstance(20, 20, Image.SCALE_SMOOTH);
        icon.setImage(scaledImage);
        this.getTempUnitLabel().setIcon(icon);
        this.setIsOn(true);
    }

    @Override
    public void turnOff() {
        this.getTempUnitLabel().setIcon(null);
        this.setIsOn(false);
    }
}
