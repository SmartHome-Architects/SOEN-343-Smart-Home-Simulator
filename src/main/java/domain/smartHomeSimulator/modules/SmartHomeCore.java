package domain.smartHomeSimulator.modules;

import domain.house.House;
import domain.sensors.Door;
import domain.sensors.Light;
import domain.sensors.Window;

public class SmartHomeCore {

    public void windowAction(House house, String component, boolean isChecked){
        for (Window w : house.getWindows()) {
            String window = w.getLocation() + " " + w.getWindowID();
            if (window.equals(component)) {
                w.setOpen(isChecked);
            }
        }
    }
    public void doorAction(House house, String component, boolean isChecked){
        for (Door d : house.getDoors()) {
            String door = d.getLocation();
            if (door.equals(component)) {
                d.setOpen(isChecked);
            }
        }
    }

    public void lightAction(House house, String component, boolean isChecked){
        for (Light l : house.getLights()) {
            String window = l.getLocation() + " " + l.getLightID();
            if (window.equals(component)) {
                l.setOpen(isChecked);
            }
        }
    }
}
