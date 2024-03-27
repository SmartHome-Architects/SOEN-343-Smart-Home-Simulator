package presentation.Swing.SHH;

import domain.house.House;
import domain.house.Room;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.ArrayList;
import java.util.List;

public class RoomTemperature {
    public static void show(JFrame parentFrame) {
        // Load room information from the JSON file
        List<RoomInfo> roomInfoList = RoomInfo.loadRoomInfo("room_info.json");

        // Create a dialog for setting room temperature
        JDialog dialog = new JDialog(parentFrame, "Set Room Temperature", true);
        dialog.setSize(600, 400);
        dialog.setLocationRelativeTo(parentFrame);

        // Create a table model with two columns: "Room Name" and "Temperature"
        DefaultTableModel tableModel = new DefaultTableModel(new Object[]{"Room Name", "Temperature"}, 0) {
            @Override
            public boolean isCellEditable(int row, int column) {
                // Allow editing only for the temperature column
                return column == 1;
            }
        };

        // Populate the table model with data from the list of RoomInfo objects
        for (RoomInfo roomInfo : roomInfoList) {
            tableModel.addRow(new Object[]{roomInfo.getRoomName(), roomInfo.getDesiredRoomTemperature()});
        }

        // Create the JTable with the table model
        JTable table = new JTable(tableModel);

        // Add a mouse listener to the table to handle clicks on rows
        table.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                int row = table.getSelectedRow();
                if (row != -1) {
                    String currentTemperature = String.valueOf(table.getValueAt(row, 1));
                    String newTemperature = JOptionPane.showInputDialog(dialog, "Enter new temperature for the selected room:", currentTemperature);
                    if (newTemperature != null && !newTemperature.isEmpty()) {
                        // Update the temperature in the table model
                        tableModel.setValueAt(newTemperature, row, 1);
                    }
                }
            }
        });

        // Create a scroll pane for the table
        JScrollPane scrollPane = new JScrollPane(table);

        // Create a button to save changes
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
                    for (RoomInfo roomInfo : roomInfoList) {
                        if (roomInfo.getRoomName().equals(roomName)) {
                            roomInfo.setDesiredRoomTemperature(newTemperature);
                            break;
                        }
                    }
                }

                // Save the updated room information to the JSON file
                RoomInfo.saveRoomInfo(roomInfoList, "room_info.json");

                JOptionPane.showMessageDialog(dialog, "Temperature changes saved successfully.");
            }
        });

        // Create a panel to hold the components
        JPanel panel = new JPanel(new BorderLayout());
        panel.add(scrollPane, BorderLayout.CENTER);
        panel.add(saveChangesButton, BorderLayout.SOUTH);

        // Add the panel to the dialog
        dialog.getContentPane().add(panel);

        dialog.setVisible(true);
    }
}
