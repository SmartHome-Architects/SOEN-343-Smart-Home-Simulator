package domain.smartHomeSimulator.modules;

import domain.house.House;
import domain.house.Room;
import domain.sensors.Door;
import domain.sensors.Light;
import domain.sensors.TempControlUnit;
import domain.sensors.Window;

import javax.swing.*;
import java.awt.*;
import java.util.Map;

public class SmartHomeSimulator {

    public void loadHouseLayoutImage(String imagePath, JLabel houseLayoutLabel, JPanel houseImage){
        ImageIcon houseLayout = new ImageIcon(imagePath);
        Image image = houseLayout.getImage().getScaledInstance(700, 473, Image.SCALE_SMOOTH);
        houseLayoutLabel.setIcon(new ImageIcon(image));
        houseLayoutLabel.setText("house layout image");
        houseImage.setLayout(null);
    }

    public void loadTempUnitIcons(House h, JPanel houseImage, Map<TempControlUnit,JLabel> tempUnitLabels){
        for (Room r: h.getRooms()) {
            if(!(r.getRoomName().equals("Outside"))){
                JLabel label = new JLabel();
                label.setIcon(null);
                label.setBounds(r.getAcUnit().getX(),r.getAcUnit().getY() - 30,35,35);
                houseImage.add(label);
                r.getHeater().setTempUnitLabel(label);
                r.getAcUnit().setTempUnitLabel(label);
                tempUnitLabels.put(r.getAcUnit(),label);
                tempUnitLabels.put(r.getHeater(),label);
            }
        }
    }

    public void loadLightIcons(House h, JPanel houseImage, Map<Light, JLabel> lightLabels){
        ImageIcon lightOff = loadImageIcon("images/lightOff.png");
        ImageIcon lightOn = loadImageIcon("images/lightOn.png");

        for (Light light : h.getLights()) {
            JLabel label = new JLabel();
            if (light.isOpen()) {
                label.setIcon(lightOn);
            } else {
                label.setIcon(lightOff);
            }
            label.setBounds(light.getX(), light.getY(), 30, 30);
            light.setLightLabel(label);
            houseImage.add(label);
            lightLabels.put(light, label);
        }
    }

    public void loadDoorIcons(House h, JPanel houseImage, Map<Door, JLabel> doorLabels, SmartHomeSecurity securitySystem){
        ImageIcon opened = loadImageIcon("images/open.png");
        ImageIcon closed = loadImageIcon("images/closed.png");

        for(Door door: h.getDoors()){
            JLabel label = new JLabel();
            if(door.isOpen()){
                label.setIcon(opened);
            }
            else{
                label.setIcon(closed);
            }
            label.setBounds(door.getX(), door.getY(), 30, 30);
            door.setDoorLabel(label);
            houseImage.add(label);
            doorLabels.put(door, label);
        }

        // After loading door icons, set doors and labels in SmartHomeSecurity
        securitySystem.setDoorsAndLabels(h.getDoors(), doorLabels);
    }


    public void loadWindowIcons(House h, JPanel houseImage, Map<Window, JLabel> windowLabels, SmartHomeSecurity securitySystem){
        ImageIcon opened = loadImageIcon("images/open.png");
        ImageIcon closed = loadImageIcon("images/closed.png");

        for(Window window: h.getWindows()){
            JLabel label = new JLabel();
            if(window.isOpen()){
                label.setIcon(opened);
            }
            else{
                label.setIcon(closed);
            }
            label.setBounds(window.getX(),window.getY(),30,30);
            window.setWindowLabel(label);
            houseImage.add(label);
            windowLabels.put(window,label);
        }

        // After loading window icons, set windows and labels in SmartHomeSecurity
        securitySystem.setWindowsAndLabels(h.getWindows(), windowLabels);
    }


    private ImageIcon loadImageIcon(String path) {
        ImageIcon icon = new ImageIcon(path);
        Image image = icon.getImage().getScaledInstance(30, 30, Image.SCALE_SMOOTH);
        return new ImageIcon(image);
    }

    public void initSlider(JSlider slider1){
        slider1.setMinimum(1);
        slider1.setMaximum(3);
        slider1.setValue(1);
        slider1.setMajorTickSpacing(1);
        slider1.setMinorTickSpacing(0);
        slider1.setPaintTicks(true);
        slider1.setPaintLabels(true);
        slider1.setBackground(Color.WHITE);
    }
}