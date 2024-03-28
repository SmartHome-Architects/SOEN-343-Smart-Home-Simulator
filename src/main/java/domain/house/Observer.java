package domain.house;

import javax.swing.*;
import java.util.HashMap;
import java.util.Map;

public interface Observer {
    void update(double tempRate, boolean isActive, double outsideTemp, Map<Room, JLabel> temperatureLabels);
}