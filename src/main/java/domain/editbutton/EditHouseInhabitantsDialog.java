package domain.editbutton;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

public class EditHouseInhabitantsDialog extends JDialog {
    public JComboBox<String> inhabitantComboBox;
    public JComboBox<String> locationComboBox;

    public EditHouseInhabitantsDialog(Frame parent) {
        super(parent, "Edit House Inhabitants", true);
        setSize(300, 150);
        setLocationRelativeTo(parent);

        JPanel panel = new JPanel(new GridLayout(2, 2));

        JLabel inhabitantLabel = new JLabel("Inhabitant:");
        inhabitantComboBox = new JComboBox<>(new String[]{"Inhabitant 1", "Inhabitant 2", "Inhabitant 3"});
        panel.add(inhabitantLabel);
        panel.add(inhabitantComboBox);

        JLabel locationLabel = new JLabel("Location:");
        locationComboBox = new JComboBox<>(new String[]{"Living Room", "Bedroom 1", "Bedroom 2", "Kitchen", "Bathroom", "Outside"});
        panel.add(locationLabel);
        panel.add(locationComboBox);

        JButton saveButton = new JButton("Save");
        saveButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent event) {
                saveChanges();
            }
        });

        JButton cancelButton = new JButton("Cancel");
        cancelButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent event) {
                dispose();
            }
        });

        JPanel buttonPanel = new JPanel();
        buttonPanel.add(saveButton);
        buttonPanel.add(cancelButton);

        getContentPane().setLayout(new BorderLayout());
        getContentPane().add(panel, BorderLayout.CENTER);
        getContentPane().add(buttonPanel, BorderLayout.SOUTH);
    }

    public void saveChanges() {
        String inhabitant = (String) inhabitantComboBox.getSelectedItem();
        String location = (String) locationComboBox.getSelectedItem();

        // Check if the location is "Outside"
        if (location.equals("Outside")) {
            // Move the inhabitant outside the home (write to text file, update internal data structure, etc.)
            System.out.println("Moving " + inhabitant + " outside the home");
        } else {
            // Place the inhabitant in the specified room (write to text file, update internal data structure, etc.)
            System.out.println("Placing " + inhabitant + " in " + location);
        }

        dispose();
    }

}
