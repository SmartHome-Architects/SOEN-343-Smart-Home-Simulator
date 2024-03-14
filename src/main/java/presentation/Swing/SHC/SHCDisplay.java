package presentation.Swing.SHC;

import domain.sensors.Door;
import domain.sensors.Light;
import domain.sensors.Window;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.util.List;

public class SHCDisplay {
    private JPanel checkBoxPanel;

    public SHCDisplay(JPanel checkBoxPanel) {
        this.checkBoxPanel = checkBoxPanel;
    }

    public void displayItems(List<?> devices) {
        clearPanel();
        checkBoxPanel.setLayout(new BorderLayout());
        DefaultTableModel tableModel = SHCTableModel.createTableModel(
                devices,
                device -> {
                    if (device instanceof Door) {
                        Door door = (Door) device;
                        return new Object[]{door.getLocation() + " " + door.getName(), door.isOpen()};
                    } else if (device instanceof domain.sensors.Window) {
                        domain.sensors.Window window = (Window) device;
                        return new Object[]{window.getLocation() + " " + window.getName(), window.isOpen()};
                    } else if (device instanceof Light) {
                        Light light = (Light) device;
                        return new Object[]{light.getLocation() + " " + light.getName(), light.isOpen()};
                    }
                    return null;
                }
        );
        JTable table = SHCTableModel.createTable(tableModel);
        JScrollPane scrollPane = new JScrollPane(table);
        checkBoxPanel.removeAll();
        checkBoxPanel.add(scrollPane);
        checkBoxPanel.revalidate();
        checkBoxPanel.repaint();
    }

    public void clearPanel() {
        Component[] components = checkBoxPanel.getComponents();
        for (Component component : components) {
            if (component instanceof JScrollPane) {
                checkBoxPanel.remove(component);
                checkBoxPanel.revalidate();
                checkBoxPanel.repaint();
                break;
            }
        }
    }
}
