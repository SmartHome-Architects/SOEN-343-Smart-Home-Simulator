package presentation.Swing.SHH;

import presentation.Swing.LogEntry;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.ArrayList;
import java.util.List;


// link the room id or room name from the JSON file to the House object's rooms, then set the temperature in house's room object??
// this is frontend only
// I only serialized and saved in JSON specific Room attributes because i did not want to make all sensor classes serializable
// i forgot to create house object to get the rooms but i hardcoded them in json

public class RoomTemperature {
    private JTextArea textArea1;
    public static void show(JFrame parentFrame, JTextArea textArea1) {

        // Load room information and desired temp from the JSON file -> RoomSerializer class
        List<RoomSerializer> roomSerializerList = RoomSerializer.loadRoomInfo("database/room_info.json");

        // Setting up popup
        JDialog dialog = new JDialog(parentFrame, "Set Room Temperature", true);
        dialog.setSize(600, 400);
        dialog.setLocationRelativeTo(parentFrame);


        // Create a table model with two columns: "Room Name" and "Temperature"
        DefaultTableModel tableModel = new DefaultTableModel(new Object[]{"Room Name", "Desired Temperature"}, 0) {
            @Override
            public boolean isCellEditable(int row, int column) {
                return column == 1;
            }
        };

        // Add values to the table
        for (RoomSerializer roomSerializer : roomSerializerList) {
            tableModel.addRow(new Object[]{roomSerializer.getRoomName(), roomSerializer.getDesiredRoomTemperature()});
        }

        // Create table model
        JTable table = new JTable(tableModel);

        // Mouse listener to handle clicks on rows
        table.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                int row = table.getSelectedRow();
                if (row != -1) {
                    // Fetching current temperature
                    String currentTemperature = String.valueOf(table.getValueAt(row, 1));
                    // New popup
                    String newTemperature = JOptionPane.showInputDialog(dialog, "Enter new temperature for the selected room:", currentTemperature);
                    if (newTemperature != null && !newTemperature.isEmpty()) {
                        // Update the temperature in the table
                        tableModel.setValueAt(newTemperature, row, 1);

                        // Log the temperature modification
                        String roomName = (String) table.getValueAt(row, 0);
                        double oldTemperature = Double.parseDouble(currentTemperature);
                        double updatedTemperature = Double.parseDouble(newTemperature);
                        LogEntry.Temperaturelog("User", roomName, oldTemperature, updatedTemperature, textArea1);
                    }
                }
            }
        });


        JScrollPane scrollPane = new JScrollPane(table);

        // Save button
        JButton saveChangesButton = new JButton("Save Changes");
        saveChangesButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {

                // Iterate through the table rows to update the desired temperature
                for (int row = 0; row < tableModel.getRowCount(); row++) {
                    // Get room name and updated temperature from the table model
                    String roomName = (String) tableModel.getValueAt(row, 0);
                    double newTemperature = Double.valueOf(tableModel.getValueAt(row, 1).toString());


                    // Update the corresponding RoomInfo object with the new temperature
                    for (RoomSerializer roomSerializer : roomSerializerList) {
                        if (roomSerializer.getRoomName().equals(roomName)) {
                            roomSerializer.setDesiredRoomTemperature(newTemperature);
                            break;
                        }
                    }
                }

                // Save the updated room information to the JSON file
                RoomSerializer.saveRoomInfo(roomSerializerList, "database/room_info.json");

                JOptionPane.showMessageDialog(dialog, "Temperature changes saved successfully.");
            }
        });

        JPanel panel = new JPanel(new BorderLayout());
        panel.add(scrollPane, BorderLayout.CENTER);
        panel.add(saveChangesButton, BorderLayout.SOUTH);

        dialog.getContentPane().add(panel);

        dialog.setVisible(true);
    }
}
