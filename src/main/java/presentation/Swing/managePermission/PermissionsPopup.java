package presentation.Swing.managePermission;

import domain.user.Users;
import domain.user.UsersInitializer;
import presentation.Swing.LogEntry;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.List;
import java.util.Scanner;

public class PermissionsPopup {

    private JTextArea textArea1;

    public static void show(JFrame parentFrame, JTextArea textArea1) {
        // popup size
        JDialog dialog = new JDialog(parentFrame, "Manage Permissions", true); // Use parentFrame as the parent window
        dialog.setSize(600, 400);
        dialog.setLocationRelativeTo(parentFrame); // Center the dialog relative to the parent frame

        // combobox to select item
        JComboBox<String> categoryComboBox = new JComboBox<>(new String[]{"Door", "Light", "Window", "SHH", "SHP"});
        dialog.add(categoryComboBox, BorderLayout.NORTH);

        // create table model
        DefaultTableModel model = new DefaultTableModel(new Object[]{"User Type", "Outside Permission", "Inside Permission"}, 0) {
            @Override
            public Class<?> getColumnClass(int columnIndex) {
                return columnIndex == 0 ? String.class : Boolean.class;
            }
        };

        // create table
        JTable table = new JTable(model);

        // style table
        JScrollPane scrollPane = new JScrollPane(table);
        dialog.add(scrollPane, BorderLayout.CENTER);

        // Save button to save to file
        JButton saveButton = new JButton("Save");
        saveButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                try {
                    savePermissions((String) categoryComboBox.getSelectedItem(), model);
                    JOptionPane.showMessageDialog(dialog, "Permissions saved successfully.");
                } catch (IOException ex) {
                    ex.printStackTrace();
                    JOptionPane.showMessageDialog(dialog, "Error saving permissions.", "Error", JOptionPane.ERROR_MESSAGE);
                }
            }
        });

        // add save button
        dialog.add(saveButton, BorderLayout.SOUTH);

        // update table after being saved
        updateTableModel((String) categoryComboBox.getSelectedItem(), model, textArea1);

        categoryComboBox.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String selectedCategory = (String) categoryComboBox.getSelectedItem();
                updateTableModel(selectedCategory, model, textArea1);
            }
        });

        // make visible
        dialog.setVisible(true);
    }

    // update table from item selected from combobox
    private static void updateTableModel(String category, DefaultTableModel model, JTextArea textArea1) {
        model.setRowCount(0); // Clear existing rows
        try (Scanner scanner = new Scanner(new File("database/" + category + "Permission.txt"))) {
            while (scanner.hasNextLine()) {
                String line = scanner.nextLine();
                String[] permissionData = line.split("\\|");
                Object[] rowData = {permissionData[0], Boolean.parseBoolean(permissionData[1]), Boolean.parseBoolean(permissionData[2])};
                model.addRow(rowData);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }

        model.addTableModelListener(e -> {
            int row = e.getFirstRow();
            int column = e.getColumn();
            String userType = (String) model.getValueAt(row, 0);
            boolean outsidePermission = (boolean) model.getValueAt(row, 1);
            boolean insidePermission = (boolean) model.getValueAt(row, 2);
            String eventDescription = "User Type: " + userType + ", Outside Permission: " + outsidePermission + ", Inside Permission: " + insidePermission;
            String user = "[User's Name]"; // You need to specify the actual user's name here
            String deviceID = "Permission Management Interface"; // You can set an appropriate device ID
            String eventType = (outsidePermission ? "Permission Granted" : "Permission Revoked") + " for " + category + " Module - " + userType;
            LogEntry.Permissionlog(user, deviceID, eventType, eventDescription, textArea1);
        });
    }

    // save permissions to appropriate files
    private static void savePermissions(String category, DefaultTableModel model) throws IOException {
        File permissionsFile = new File("database/" + category + "Permission.txt");
        FileWriter writer = new FileWriter(permissionsFile);
        for (int i = 0; i < model.getRowCount(); i++) {
            String userType = (String) model.getValueAt(i, 0);
            boolean outsidePermission = (boolean) model.getValueAt(i, 1);
            boolean insidePermission = (boolean) model.getValueAt(i, 2);
            writer.write(userType + "|" + outsidePermission + "|" + insidePermission + "\n");
        }
        writer.close();
        List<Users> allUsers = UsersInitializer.getAllUsers();
    }
}
