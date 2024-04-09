package presentation.Swing.SHC;

import domain.house.House;
import domain.sensors.Door;
import domain.sensors.Light;
import domain.sensors.Window;
import domain.user.LoggedInUser;
import domain.user.UserSingleton;
import presentation.Swing.LogEntry;

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
import java.util.Objects;


public class SHCTableModel<T> {
    private static LoggedInUser user;
    private LogEntry logEntry;

    public static <T> DefaultTableModel createTableModel(List<T> items, SingleItem<T> singleItem, JTextArea textArea1) {
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

    public static JTable createTable(DefaultTableModel model, House h, String selectedItem, JTextArea textArea1) {
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
                    return new CheckBoxEditor(this,h,selectedItem, textArea1);
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
        private final House house;
        private final String selectedItem;

        public CheckBoxEditor(JTable table, House h, String selectedItem, JTextArea textArea1) {
            this.checkBox = new JCheckBox();
            this.house = h;
            this.selectedItem = selectedItem;
            checkBox.addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    boolean isChecked = checkBox.isSelected();
                    int column = 0;
                    int row = table.getSelectedRow();
                    String component = (table.getModel().getValueAt(row,column)).toString();

                    //For log entry
                    String state = isChecked ? "Opened" : "Closed";

                    if (selectedItem.equals("Windows")) {
                        // Permission check UI
                        // get logged in users permissions
                        user = UserSingleton.getUser();
                        System.out.println("User location: " + user.getLocation());
                        // check permissions
                        if ((!Objects.equals(user.getLocation(), "Outside") && (user.getPermissions().isHasWindowPermissionInsideHome())) ||
                                ((Objects.equals(user.getLocation(), "Outside")) && user.getPermissions().isHasWindowPermissionOutside())) {
                            System.out.println("You have permission to open/close lights");

                            //Log entry for textfile
                            LogEntry.SHClog(user.getLoggedInUser().getUsername(), "Window", component, state, "Window State Change", textArea1);

                        List<Window> windows = house.getWindows();
                            for (Window w : windows) {
                                String window = w.getLocation() + " " + w.getWindowID();
                                if (window.equals(component)) {
                                    // Perform action if user has permission
                                    w.setOpen(isChecked);
                                }
                            }
                         }else{
                            System.out.println("You do not have permission to open/close windows");
                            checkBox.setSelected(!isChecked);
                        }
                    }
                    else if (selectedItem.equals("Doors")) {
                        // Permission check UI
                        // get logged in users permissions
                        user = UserSingleton.getUser();
                        System.out.println("User location: " + user.getLocation());
                        // check permissions
                        if ((!Objects.equals(user.getLocation(), "Outside") && (user.getPermissions().isHasDoorPermissionInsideHome())) ||
                                ((Objects.equals(user.getLocation(), "Outside")) && user.getPermissions().isHasDoorPermissionOutside())) {
                            System.out.println("You have permission to open/close doors");

                            //Log entry for textfile
                            LogEntry.SHClog(user.getLoggedInUser().getUsername(), "Door", component, state, "Door State Change", textArea1);

                            List<Door> doors = house.getDoors();
                            for (Door d : doors) {
                                String door = d.getLocation();
                                if (door.equals(component)) {
                                    d.setOpen(isChecked);
                                }
                            }
                        }else {
                            System.out.println("You do not have permission to open/close doors");
                            checkBox.setSelected(!isChecked);
                        }

                    } else if (selectedItem.equals("Lights")) {
                        // Permission check UI
                        // get logged in users permissions
                        user = UserSingleton.getUser();
                        System.out.println("User location: " + user.getLocation());
                        // check permissions
                        if ((!Objects.equals(user.getLocation(), "Outside") && (user.getPermissions().isHasLightPermissionInsideHome())) ||
                                ((Objects.equals(user.getLocation(), "Outside")) && user.getPermissions().isHasLightPermissionOutside())) {
                            System.out.println("You have permission to open/close lights");

                            //Log entry for textfile
                            LogEntry.SHClog(user.getLoggedInUser().getUsername(), "Light", component, state, "Light State Change", textArea1);

                            List<Light> lights = house.getLights();
                            for (Light l : lights) {
                                String window = l.getLocation() + " " + l.getLightID();
                                if (window.equals(component)) {
                                    l.setOpen(isChecked);
                                }
                            }
                        } else {
                            System.out.println("You do not have permission to open/close lights");
                            checkBox.setSelected(!isChecked);
                        }
                    }
                }
            });
        }

        @Override
        public Component getTableCellEditorComponent(JTable table, Object value, boolean isSelected, int row, int column) {
            checkBox.setSelected(value != null && (boolean) value);
            // Disable checkbox if user doesn't have permission
            checkBox.setEnabled(checkBox.isSelected() || selectedItem.equals("Lights") || selectedItem.equals("Doors") || selectedItem.equals("Windows"));
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
