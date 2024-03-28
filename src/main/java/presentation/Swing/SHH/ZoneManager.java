package presentation.Swing.SHH;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import domain.house.House;
import domain.house.Room;
import domain.house.Zone;
import domain.smartHomeSimulator.modules.SmartHomeHeating;

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


// link to Zone class with zone name??
// can create a zone object by fetching saved values from json
public class ZoneManager extends JDialog {

    private static final String JSON_FILE_PATH = "database/zones.json";

    // house object to get rooms
    private House house;

    SmartHomeHeating shh;

    public ZoneManager(JFrame parent, House h, SmartHomeHeating shh) {

        // set up popup
        super(parent, "Manage Zones", true);
        setSize(600, 400);
        setLocationRelativeTo(parent);

        // initialize house object
        this.house = h;
        this.shh = shh;

        // Table model with a column for room names
        DefaultTableModel tableModel = new DefaultTableModel();
        tableModel.addColumn("Room Name");

        // Add room names to the table
        for (String roomName : house.getRoomNames()) {
            tableModel.addRow(new Object[]{roomName});
        }

        //formatting
        JTable table = new JTable(tableModel);
        JScrollPane scrollPane = new JScrollPane(table);
        getContentPane().add(scrollPane, BorderLayout.CENTER);

        // Create Zone button
        JButton createZoneButton = new JButton("Create Zone");
        createZoneButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                createZone();
            }
        });

        getContentPane().add(createZoneButton, BorderLayout.SOUTH);

        // View Zones button
        JButton viewZonesButton = new JButton("View Zones");
        viewZonesButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                loadAndDisplayZones();
            }
        });

        getContentPane().add(viewZonesButton, BorderLayout.NORTH);
    }

    private void createZone() {
        // Get selected rows from the table to select zone keep pressing on Ctrl and click on zones
        int[] selectedRows = ((JTable) ((JScrollPane) getContentPane().getComponent(0)).getViewport().getView()).getSelectedRows();

        // Create a list to store selected room names
        List<String> selectedRoomNames = new ArrayList<>();
        List<Integer> selectedRoomIds = new ArrayList<>();
        for (int row : selectedRows) {
            selectedRoomNames.add((String) ((JTable) ((JScrollPane) getContentPane().getComponent(0)).getViewport().getView()).getValueAt(row, 0));
        }

        // Prompt for zone name
        String zoneName = JOptionPane.showInputDialog(this, "Enter zone name:");

        // Prompt for desired temperature
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

        shh.attach(newZone);
        System.out.println(shh.getZones().size());

        // Save the zone information in a JSON file
        List<Zone> zones = new ArrayList<>();
        zones.add(newZone);
        ZoneSerializer.saveZones(zones);
    }

    private void loadAndDisplayZones() {
        ObjectMapper mapper = new ObjectMapper();
        try {
            // Deserialize the zones from the JSON file
            List<Map<String, Object>> zoneList = mapper.readValue(new File(JSON_FILE_PATH),
                    new TypeReference<List<Map<String, Object>>>() {});

            // Create a table for displaying zones
            DefaultTableModel tempTableModel = new DefaultTableModel(new Object[]{"Zone Name", "Rooms", "Desired Temperature"}, 0) {
                @Override
                public boolean isCellEditable(int row, int column) {
                    return column == 2;
                }
            };

            // table model with zone information
            for (Map<String, Object> zoneMap : zoneList) {
                String zoneName = (String) zoneMap.get("zoneName");
                double desiredTemperature = (double) zoneMap.get("desiredTemperature");
                List<Map<String, Object>> roomList = (List<Map<String, Object>>) zoneMap.get("rooms");
                StringBuilder roomNames = new StringBuilder();
                for (Map<String, Object> roomMap : roomList) {
                    roomNames.append(roomMap.get("roomName")).append(", ");
                }
                if (roomNames.length() > 0) {
                    roomNames.setLength(roomNames.length() - 2);
                }
                Object[] rowData = {zoneName, roomNames.toString(), desiredTemperature};
                tempTableModel.addRow(rowData);
            }

            JTable tempTable = new JTable(tempTableModel);

            // mouse listener to the temperature column
            tempTable.addMouseListener(new MouseAdapter() {
                @Override
                public void mouseClicked(MouseEvent e) {
                    int column = tempTable.getColumnModel().getColumnIndexAtX(e.getX());
                    int row = e.getY() / tempTable.getRowHeight();

                    // clicked column is the temperature column
                    // edit temperature
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

            JScrollPane scrollPane = new JScrollPane(tempTable);

            // Save button
            JButton saveChangesButton = new JButton("Save Changes");
            saveChangesButton.addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    // Save the updated temperatures to the JSON file
                    saveUpdatedTemperatures(zoneList, tempTableModel);
                    JOptionPane.showMessageDialog(null, "Temperature changes saved successfully.");
                }
            });

            JPanel panel = new JPanel(new BorderLayout());
            panel.add(scrollPane, BorderLayout.CENTER);
            panel.add(saveChangesButton, BorderLayout.SOUTH);

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
            // Update the temperature values in the zone list
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

    public static void show(JFrame parent, House h, SmartHomeHeating shh) {
        ZoneManager dialog = new ZoneManager(parent,h,shh);
        dialog.setVisible(true);
    }
}