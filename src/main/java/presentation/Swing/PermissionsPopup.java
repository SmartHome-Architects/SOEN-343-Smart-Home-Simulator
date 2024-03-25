package presentation.Swing;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Scanner;

public class PermissionsPopup {
    public static void show(JFrame parentFrame) {
        // Create and configure your popup dialog
        JDialog dialog = new JDialog(parentFrame, "Manage Permissions", true); // Use parentFrame as the parent window
        dialog.setSize(600, 600);
        dialog.setLocationRelativeTo(parentFrame); // Center the dialog relative to the parent frame

        // Create a JComboBox for selecting permission categories
        JComboBox<String> categoryComboBox = new JComboBox<>(new String[]{"Door", "Light", "Window", "Heater"});
        dialog.add(categoryComboBox, BorderLayout.NORTH);

        // Create table model with columns: User Type, Outside Permission, Inside Permission
        DefaultTableModel model = new DefaultTableModel(new Object[]{"User Type", "Outside Permission", "Inside Permission"}, 0) {
            @Override
            public Class<?> getColumnClass(int columnIndex) {
                return columnIndex == 0 ? String.class : Boolean.class;
            }
        };

        // Create the table using the model
        JTable table = new JTable(model);

        // Add the table to a scroll pane
        JScrollPane scrollPane = new JScrollPane(table);

        // Add the scroll pane to the dialog
        dialog.add(scrollPane, BorderLayout.CENTER);

        // Add a Save button to save changes to permissions files
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

        // Add components to the dialog
        dialog.add(saveButton, BorderLayout.SOUTH);

        // Update table model
        updateTableModel((String) categoryComboBox.getSelectedItem(), model);

        // Add action listener to the category JComboBox
        categoryComboBox.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String selectedCategory = (String) categoryComboBox.getSelectedItem();
                updateTableModel(selectedCategory, model);
            }
        });

        // Set the visibility of the dialog
        dialog.setVisible(true);
    }

    // Method to update the table model based on the selected category
    private static void updateTableModel(String category, DefaultTableModel model) {
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
    }

    // Method to save permissions based on the selected category
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
    }
}
