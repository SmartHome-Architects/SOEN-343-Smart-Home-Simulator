package presentation.Swing.SHC;

import domain.house.House;
import domain.house.Room;
import domain.sensors.Door;
import domain.sensors.Light;
import domain.sensors.Window;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.JTableHeader;
import javax.swing.table.TableCellEditor;
import javax.swing.table.TableCellRenderer;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.List;


public class SHCTableModel<T> {
    public static <T> DefaultTableModel createTableModel(List<T> items, SingleItem<T> singleItem) {
        List<Object[]> data = getData(items, singleItem);
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

    private static <T> List<Object[]> getData(List<T> items, SingleItem<T> singleItem) {
        List<Object[]> data = new ArrayList<>();
        for (T item : items) {
            Object[] rowData = singleItem.extractData(item);
            if (rowData != null && rowData.length > 1) {
                data.add(rowData);
            }
        }
        return data;
    }

    public static JTable createTable(DefaultTableModel model, House h, String selectedItem) {
        return new JTable(model) {
            @Override
            public TableCellRenderer getCellRenderer(int row, int column) {
                if (column == 1) {
                    return new CheckBoxRenderer();
                }
                return super.getCellRenderer(row, column);
            }

            @Override
            public TableCellEditor getCellEditor(int row, int column) {
                if (column == 1) {
                    return new CheckBoxEditor(this,h,selectedItem);
                }
                return super.getCellEditor(row, column);
            }

            @Override
            public JTableHeader getTableHeader() {
                return null;
            }
        };
    }

    static class CheckBoxRenderer extends JCheckBox implements TableCellRenderer {
        public CheckBoxRenderer() {
            setHorizontalAlignment(SwingConstants.LEFT);
        }

        @Override
        public Component getTableCellRendererComponent(JTable table, Object value, boolean isSelected, boolean hasFocus, int row, int column) {
            setSelected(value != null && (boolean) value);
            return this;
        }
    }

    static class CheckBoxEditor extends AbstractCellEditor implements TableCellEditor {
        private final JCheckBox checkBox;

        public CheckBoxEditor(JTable table, House h, String selectedItem) {
            checkBox = new JCheckBox();
            checkBox.addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    //stopCellEditing();
                    boolean isChecked = checkBox.isSelected();
                    int column = 0;
                    int row = table.getSelectedRow();
                    String component = (table.getModel().getValueAt(row,column)).toString();

                    if(selectedItem.equals("Windows")){
                        List<Window> windows = h.getWindows();
                        for (Window w : windows) {
                            String window = w.getLocation() + " " + w.getWindowID();
                            if (window.equals(component)) {
                                w.setOpen(isChecked);
                            }
                        }
                    }
                    if(selectedItem.equals("Doors")){
                        List<Door> doors = h.getDoors();
                        for (Door d : doors) {
                            String door = d.getLocation();
                            if (door.equals(component)) {
                                d.setOpen(isChecked);
                            }
                        }
                    }

                    if(selectedItem.equals("Lights")){
                        List<Light> lights = h.getLights();
                        for (Light l : lights) {
                            String window = l.getLocation() + " " + l.getLightID();
                            if (window.equals(component)) {
                                l.setOpen(isChecked);
                            }
                        }
                    }

                }
            });
        }

        @Override
        public Component getTableCellEditorComponent(JTable table, Object value, boolean isSelected, int row, int column) {
            checkBox.setSelected(value != null && (boolean) value);
            return checkBox;
        }

        @Override
        public Object getCellEditorValue() {
            return checkBox.isSelected();
        }
    }



    public interface SingleItem<T> {
        Object[] extractData(T item);
    }
}
