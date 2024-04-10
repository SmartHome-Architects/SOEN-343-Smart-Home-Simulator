package presentationTest;

import domain.house.Coordinate;
import domain.house.House;
import domain.sensors.Door;
import domain.smartHomeSimulator.modules.SmartHomeSecurity;
import org.junit.Before;
import org.junit.Test;

import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.*;

public class SHCDisplayTest {

    private JPanel checkBoxPanel;
    private SHCDisplay shcDisplay;

    private JTextArea textArea1;

    private String user;

    @Before
    public void setUp() {
        checkBoxPanel = new JPanel();
        // Mock values for House and selectedItem
        House mockHouse = new House(); // You can create a mock House object according to your test needs
        String mockSelectedItem = "Selected"; // You can set a mock value for selectedItem
        shcDisplay = new SHCDisplay(checkBoxPanel, mockHouse, mockSelectedItem, textArea1, new SmartHomeSecurity(new UserAccountManager("f.txt"), user, textArea1));
    }


    @Test
    public void testDisplayItems() {
        // Test displaying items
        List<Object> devices = new ArrayList<>();
        devices.add(new Door("Front Door", "Living Room", 1, new Coordinate(0, 0))); // Example values for id and coordinate

        shcDisplay.displayItems(devices);

        Component[] components = checkBoxPanel.getComponents();
        boolean foundScrollPane = false;
        for (Component component : components) {
            if (component instanceof JScrollPane) {
                foundScrollPane = true;
                break;
            }
        }
        assertTrue("ScrollPane should be added to checkBoxPanel", foundScrollPane);
    }

    @Test
    public void testClearPanel() {
        // Test clearing panel
        JScrollPane scrollPane = new JScrollPane();
        checkBoxPanel.add(scrollPane);

        shcDisplay.clearPanel();

        Component[] components = checkBoxPanel.getComponents();
        assertEquals("No components should be present in checkBoxPanel", 0, components.length);
    }
}
