package presentation.Swing.SHC;

import domain.house.House;
import domain.sensors.Door;
import domain.sensors.Light;
import domain.sensors.Window;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.util.List;

public class SHCDisplay {
    private JPanel checkBoxPanel;
    private House h;
    private String selectedItem;
    private JTextArea textArea1;

    public SHCDisplay(JPanel checkBoxPanel, House h, String selectedItem, JTextArea textArea1) {
        this.checkBoxPanel = checkBoxPanel;
        this.h = h;
        this.selectedItem = selectedItem;
        this.textArea1 = textArea1;
    }

    public void displayItems(List<?> devices) {
        clearPanel();
        checkBoxPanel.setLayout(new BorderLayout());
        int selection;
        DefaultTableModel tableModel = SHCTableModel.createTableModel(
                devices,
                device -> {
                    if (device instanceof Door) {
                        Door door = (Door) device;
                        return new Object[]{door.getLocation(), door.isOpen()};
                    } else if (device instanceof Window) {
                        domain.sensors.Window window = (Window) device;
                        return new Object[]{window.getLocation() + " " + window.getWindowID(), window.isOpen()};
                    } else if (device instanceof Light) {
                        Light light = (Light) device;
                        return new Object[]{light.getLocation() + " " + light.getLightID(), light.isOpen()};
                    }
                    return null;
                }, textArea1
        );
        JTable table = SHCTableModel.createTable(tableModel,h,selectedItem, textArea1);
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
