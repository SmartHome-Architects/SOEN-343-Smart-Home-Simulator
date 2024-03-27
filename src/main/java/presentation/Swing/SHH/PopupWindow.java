package presentation.Swing.SHH;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import domain.house.House;
import domain.house.Room;
import domain.house.Zone;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class PopupWindow extends JDialog {

    private static final String JSON_FILE_PATH = "zones.json";

    private House house;

    public PopupWindow(JFrame parent) {
        super(parent, "Manage Zones", true);
        setSize(600, 400);
        setLocationRelativeTo(parent);

        house = new House();

        // Create a table model with a single column for room names
        DefaultTableModel tableModel = new DefaultTableModel();
        tableModel.addColumn("Room Name");

        // Add room names to the table model
        for (String roomName : house.getRoomNames()) {
            tableModel.addRow(new Object[]{roomName});
        }

        // Create the JTable with the table model
        JTable table = new JTable(tableModel);

        // Add the table to a scroll pane to make it scrollable
        JScrollPane scrollPane = new JScrollPane(table);

        // Add the scroll pane to the dialog's content pane
        getContentPane().add(scrollPane, BorderLayout.CENTER);

        // Create a button for creating a zone
        JButton createZoneButton = new JButton("Create Zone");
        createZoneButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                createZone();
            }
        });

        // Add the button to the dialog's content pane
        getContentPane().add(createZoneButton, BorderLayout.SOUTH);

        // Create a button for viewing zones
        JButton viewZonesButton = new JButton("See Zones");
        viewZonesButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                loadAndDisplayZones();
            }
        });

        // Add the "See Zones" button to the dialog's content pane
        getContentPane().add(viewZonesButton, BorderLayout.NORTH);
    }

    private void createZone() {
        // Get selected rows from the table
        int[] selectedRows = ((JTable) ((JScrollPane) getContentPane().getComponent(0)).getViewport().getView()).getSelectedRows();

        // Create a list to store selected room names
        List<String> selectedRoomNames = new ArrayList<>();
        List<Integer> selectedRoomIds = new ArrayList<>();
        for (int row : selectedRows) {
            selectedRoomNames.add((String) ((JTable) ((JScrollPane) getContentPane().getComponent(0)).getViewport().getView()).getValueAt(row, 0));
        }

        // Prompt user to input the zone name
        String zoneName = JOptionPane.showInputDialog(this, "Enter zone name:");

        // Prompt user to input the desired temperature
        String tempInput = JOptionPane.showInputDialog(this, "Enter desired temperature for the zone:");
        double desiredTemperature = Double.parseDouble(tempInput);

        // Create a zone with the selected rooms, provided name, and desired temperature
        Zone newZone = new Zone(zoneName, desiredTemperature);
        for (Room room : house.getRooms()) {
            if (selectedRoomNames.contains(room.getRoomName())) {
                newZone.addRoomToZone(room);
                selectedRoomIds.add(room.getId());
            }
        }

        // Output the newly created zone in the console
        System.out.println("New Zone Created:");
        System.out.println("Zone Name: " + newZone.getZoneName());
        System.out.println("Desired Temperature: " + newZone.getDesiredZoneTemperature());
        for (Room room : newZone.getZoneRooms()) {
            System.out.println("Room ID: " + room.getId() + ", Room Name: " + room.getRoomName());
        }

        // Save the zone information in a JSON file
        List<Zone> zones = new ArrayList<>();
        zones.add(newZone);
        ZoneNameSerializer.saveZones(zones);
    }







    private void loadAndDisplayZones() {
        ObjectMapper mapper = new ObjectMapper();
        try {
            // Deserialize the zones from the JSON file
            List<Map<String, Object>> zoneList = mapper.readValue(new File(JSON_FILE_PATH),
                    new TypeReference<List<Map<String, Object>>>() {});

            // Create a table model for displaying zones
            DefaultTableModel tempTableModel = new DefaultTableModel(new Object[]{"Zone Name", "Rooms", "Temperature"}, 0) {
                @Override
                public boolean isCellEditable(int row, int column) {
                    // Allow editing only for the temperature column
                    return column == 2;
                }
            };

            // Populate the table model with zone information
            for (Map<String, Object> zoneMap : zoneList) {
                String zoneName = (String) zoneMap.get("zoneName");
                double desiredTemperature = (double) zoneMap.get("desiredTemperature");
                List<Map<String, Object>> roomList = (List<Map<String, Object>>) zoneMap.get("rooms");
                StringBuilder roomNames = new StringBuilder();
                for (Map<String, Object> roomMap : roomList) {
                    roomNames.append(roomMap.get("roomName")).append(", ");
                }
                if (roomNames.length() > 0) {
                    roomNames.setLength(roomNames.length() - 2); // Remove the last comma and space
                }
                Object[] rowData = {zoneName, roomNames.toString(), desiredTemperature};
                tempTableModel.addRow(rowData);
            }

            // Create a JTable with the table model
            JTable tempTable = new JTable(tempTableModel);

            // Add a mouse listener to the temperature column
            tempTable.addMouseListener(new MouseAdapter() {
                @Override
                public void mouseClicked(MouseEvent e) {
                    int column = tempTable.getColumnModel().getColumnIndexAtX(e.getX());
                    int row = e.getY() / tempTable.getRowHeight();

                    // If the clicked column is the temperature column
                    if (column == 2 && row < tempTable.getRowCount() && row >= 0) {
                        double currentTemperature = (double) tempTableModel.getValueAt(row, column);
                        String newTemperatureStr = JOptionPane.showInputDialog(null, "Enter new temperature for the selected zone:", currentTemperature);
                        if (newTemperatureStr != null && !newTemperatureStr.isEmpty()) {
                            try {
                                double newTemperature = Double.parseDouble(newTemperatureStr);
                                tempTableModel.setValueAt(newTemperature, row, column);
                            } catch (NumberFormatException ex) {
                                JOptionPane.showMessageDialog(null, "Invalid temperature format.");
                            }
                        }
                    }
                }
            });

            // Create a scroll pane for the table
            JScrollPane scrollPane = new JScrollPane(tempTable);

            // Create a button to save changes
            JButton saveChangesButton = new JButton("Save Changes");
            saveChangesButton.addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    // Save the updated temperatures to the JSON file
                    saveUpdatedTemperatures(zoneList, tempTableModel);
                    JOptionPane.showMessageDialog(null, "Temperature changes saved successfully.");
                }
            });

            // Add the components to a panel
            JPanel panel = new JPanel(new BorderLayout());
            panel.add(scrollPane, BorderLayout.CENTER);
            panel.add(saveChangesButton, BorderLayout.SOUTH);

            // Create a dialog to display zones
            JDialog zoneDialog = new JDialog(this, "Zones Information", true);
            zoneDialog.getContentPane().add(panel);
            zoneDialog.setSize(600, 400);
            zoneDialog.setLocationRelativeTo(this);
            zoneDialog.setVisible(true);

        } catch (IOException e) {
            System.err.println("Error loading zones: " + e.getMessage());
        }
    }

    private void saveUpdatedTemperatures(List<Map<String, Object>> zoneList, DefaultTableModel tempTableModel) {
        try {
            // Update the temperature values in the zone list based on the table model
            for (int i = 0; i < tempTableModel.getRowCount(); i++) {
                double newTemperature = (double) tempTableModel.getValueAt(i, 2);
                zoneList.get(i).put("desiredTemperature", newTemperature);
            }

            // Write the updated zone list to the JSON file
            ObjectMapper mapper = new ObjectMapper();
            mapper.writeValue(new File(JSON_FILE_PATH), zoneList);
            System.out.println("Temperature changes saved to " + JSON_FILE_PATH);
        } catch (IOException e) {
            System.err.println("Error saving temperature changes: " + e.getMessage());
        }
    }






















    private void saveZoneChanges(List<Zone> zones) {
        try {
            ObjectMapper mapper = new ObjectMapper();
            List<Map<String, Object>> zoneList = new ArrayList<>();
            for (Zone zone : zones) {
                Map<String, Object> zoneMap = Map.of(
                        "zoneName", zone.getZoneName(),
                        "desiredTemperature", zone.getDesiredZoneTemperature(),
                        "rooms", ZoneNameSerializer.convertRoomsToMapList(zone.getZoneRooms())
                );
                zoneList.add(zoneMap);
            }
            // Serialize the updated zone list to the JSON file
            mapper.writeValue(new File(JSON_FILE_PATH), zoneList);
            System.out.println("Zones updated and saved to " + JSON_FILE_PATH);
        } catch (IOException e) {
            System.err.println("Error saving zones: " + e.getMessage());
        }
    }








    public static void show(JFrame parent) {
        PopupWindow dialog = new PopupWindow(parent);
        dialog.setVisible(true);
    }
}
