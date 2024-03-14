package presentation.Swing.SHC;

import domain.sensors.Door;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.JTableHeader;
import javax.swing.table.TableCellRenderer;
import java.awt.*;
import java.util.ArrayList;
import java.util.List;

public class SHCTableModel {
    public static DefaultTableModel createTableModel(List<Door> doors) {
        List<Object[]> data = getData(doors);
        return new DefaultTableModel(
                data.toArray(new Object[0][0]),
                new Object[]{"Room Name", "Open/Close"}) {
            @Override
            public Class<?> getColumnClass(int columnIndex) {
                return columnIndex == 1 ? Boolean.class : Object.class;
            }

            @Override
            public boolean isCellEditable(int row, int column) {
                return column == 1;
            }
        };
    }

    private static List<Object[]> getData(List<Door> doors) {
        List<Object[]> data = new ArrayList<>();
        for (Door door : doors) {
            if (door != null && !door.getName().isEmpty()) {
                String name = door.getLocation() + " " + door.getName();
                boolean isOpen = door.isOpen();
                data.add(new Object[]{name, isOpen});
            }
        }
        return data;
    }

    public static JTable createTable(DefaultTableModel model) {
        return new JTable(model) {
            @Override
            public Component prepareRenderer(TableCellRenderer renderer, int row, int column) {
                Component component = super.prepareRenderer(renderer, row, column);
                if (column == 1 && component instanceof JCheckBox) {
                    ((JCheckBox) component).setHorizontalAlignment(SwingConstants.CENTER);
                }
                return component;
            }

            @Override
            public JTableHeader getTableHeader() {
                return null;
            }
        };
    }
}
