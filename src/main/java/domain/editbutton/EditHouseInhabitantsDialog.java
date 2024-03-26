package domain.editbutton;

import presentation.Swing.command.UserAccountManager;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.util.List;
import java.util.Map;

public class EditHouseInhabitantsDialog extends JDialog {
    private JComboBox<String> inhabitantComboBox;
    private JComboBox<String> locationComboBox;
    private UserAccountManager userAccountManager;
    private String selectedUsername;

    public EditHouseInhabitantsDialog(Frame parent, UserAccountManager userAccountManager, List<String> usernames, String username) {
        super(parent, "Edit House Inhabitants", true);
        setSize(300, 150);
        setLocationRelativeTo(parent);

        this.userAccountManager = userAccountManager;
        this.selectedUsername = username;

        initComponents(usernames, username);
        layoutComponents();
        addListeners();

        // Retrieve the content of the logged-in user's file
        List<String> loggedInUserContent = userAccountManager.getFirstColumnContent(username);
        displayLoggedInUserContent(loggedInUserContent);

    }


    private void initComponents(List<String> usernames, String username) {
        inhabitantComboBox = new JComboBox<>();

        // Add the logged-in username directly to the dropdown
        inhabitantComboBox.addItem(username);

        // Add other usernames from the list
        for (String name : usernames) {
            if (!name.equals(username)) {
                inhabitantComboBox.addItem(name);
            }
        }

        locationComboBox = new JComboBox<>(new String[]{"Living Room", "Bedroom 1", "Bedroom 2", "Kitchen", "Bathroom", "Outside"});
    }


    private void layoutComponents() {
        JPanel panel = new JPanel(new GridLayout(2, 2));
        panel.add(new JLabel("Inhabitant:"));
        panel.add(inhabitantComboBox);
        panel.add(new JLabel("Location:"));
        panel.add(locationComboBox);

        JButton saveButton = new JButton("Save");
        JButton cancelButton = new JButton("Cancel");

        JPanel buttonPanel = new JPanel();
        buttonPanel.add(saveButton);
        buttonPanel.add(cancelButton);

        getContentPane().setLayout(new BorderLayout());
        getContentPane().add(panel, BorderLayout.CENTER);
        getContentPane().add(buttonPanel, BorderLayout.SOUTH);
    }

    private void addListeners() {
        JButton saveButton = new JButton("Save");
        saveButton.addActionListener(e -> saveChanges());

        JButton cancelButton = new JButton("Cancel");
        cancelButton.addActionListener(e -> dispose());

        // Add save and cancel buttons to the button panel
        JPanel buttonPanel = new JPanel();
        buttonPanel.add(saveButton);
        buttonPanel.add(cancelButton);

        // Add the button panel to the dialog
        getContentPane().add(buttonPanel, BorderLayout.SOUTH);
    }


    private void populateInhabitantComboBox(List<String> usernames, String username) {
        inhabitantComboBox.removeAllItems();
        for (String name : usernames) {
            inhabitantComboBox.addItem(name);
        }
        inhabitantComboBox.setSelectedItem(username);
    }

    private void displayLoggedInUserContent(List<String> loggedInUserContent) {
        // Display the content of the logged-in user's file
        // For demonstration, let's print it to the console
        for (String line : loggedInUserContent) {
            System.out.println(line);
        }
    }

    public void saveChanges() {
        String inhabitant = (String) inhabitantComboBox.getSelectedItem();
        String location = (String) locationComboBox.getSelectedItem();

        if (location.equals("Outside")) {
            System.out.println("Moving " + inhabitant + " outside the home");
        } else {
            System.out.println("Placing " + inhabitant + " in " + location);
        }

        dispose();
    }

    public void updateUserDropdown(List<String> updatedUsernames, String selectedUsername) {
        populateInhabitantComboBox(updatedUsernames, selectedUsername);
    }

    public String getSelectedUsername() {
        return selectedUsername;
    }
}
