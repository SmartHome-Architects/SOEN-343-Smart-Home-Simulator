package presentationTest;

import domain.house.House;
import domain.sensors.Door;
import domain.smartHomeSimulator.modules.SmartHomeSecurity;
import org.junit.Test;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableModel;
import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.*;

public class SHCTableModelTest {

    @Test
    public void testCreateTableModel() {
        // Test createTableModel method
        List<Door> doors = new ArrayList<>();


        TableModel tableModel = SHCTableModel.createTableModel(doors, door -> {
            if (door.isOpen()) {
                return new Object[]{door.getLocation() + " " + door.getName(), "Open"};
            } else {
                return new Object[]{door.getLocation() + " " + door.getName(), "Close"};
            }
        }, new JTextArea());

        assertNotNull("TableModel should not be null", tableModel);
        assertEquals("TableModel should have correct number of rows", 0, tableModel.getRowCount());
        assertEquals("TableModel should have correct number of columns", 2, tableModel.getColumnCount());
        assertEquals("TableModel should have correct column names", "Room Name", tableModel.getColumnName(0));
        assertEquals("TableModel should have correct column names", "Open/Close", tableModel.getColumnName(1));
    }

    @Test
    public void testCreateTable() {
        // Test createTable method
        House house = new House();
        String selectedItem = "Doors";
        JTextArea textArea1 = new JTextArea();
        DefaultTableModel model = new DefaultTableModel(new Object[][]{{"Living Room Door", "Open"}}, new Object[]{"Room Name", "Open/Close"});
        JTable table = SHCTableModel.createTable(model,house,selectedItem, textArea1, new SmartHomeSecurity(new UserAccountManager("a.txt")));

        assertNotNull("Table should not be null", table);
        assertEquals("Table should have correct number of rows", 1, table.getRowCount());
        assertEquals("Table should have correct number of columns", 2, table.getColumnCount());
        assertEquals("Table should have correct column names", "Room Name", table.getColumnName(0));
        assertEquals("Table should have correct column names", "Open/Close", table.getColumnName(1));
    }
}
