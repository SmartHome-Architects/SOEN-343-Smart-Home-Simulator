package domain.editbutton;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import javax.swing.JLabel;
import domain.house.Room;
import domain.user.LoggedInUser;
import domain.user.Users;
import presentation.Swing.LogEntry;
import presentation.Swing.command.UserAccountManager;
import domain.house.House;

public class EditHouseInhabitantsDialog extends JDialog {
    private String oldLocation;
    private JLabel locationTag;

    private JTextArea textArea1;

    private JComboBox<String> inhabitantComboBox;
    private JComboBox<String> locationComboBox;
    private UserAccountManager userAccountManager;
    private House houseInstance;
    private String selectedUsername;
    private LogEntry logEntry;
    private Map<String,JLabel> userLabels;

    private LoggedInUser user;

    public EditHouseInhabitantsDialog(Frame parent, UserAccountManager userAccountManager, List<String> usernames, String username, House houseInstance, Map<String,JLabel> userLabels, LoggedInUser user, JLabel locationTag, JTextArea textArea1) {
        super(parent, "Edit House Inhabitants", true);
        setSize(300, 150);
        setLocationRelativeTo(parent);
        this.locationTag = locationTag;
        this.userAccountManager = userAccountManager;
        this.selectedUsername = username;
        this.houseInstance = houseInstance;
        this.userLabels = userLabels;
        this.user = user;
        this.textArea1 = textArea1;

        // Fetch the location of the selected user from the UserAccountManager
        String userLocation = userAccountManager.getUserLocation(username);

        // If userLocation is not null, set it as the initial location
        if (userLocation != null) {
            oldLocation = userLocation;
        } else {
            System.out.println("Location for user " + username + " not found.");
        }

        initComponents(usernames, username);
        layoutComponents();
        addListeners();

        // Initialize JTextArea for console output (if not already initialized)
        LogEntry.setTextArea(textArea1);

        // Populate the locationComboBox with room names
        populateLocationComboBoxWithRoomNames();

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

        locationComboBox = new JComboBox<>();

        // Fetch the location of the selected user from the UserAccountManager
        String userLocation = userAccountManager.getUserLocation(username);

        // If userLocation is not null, add it to the locationComboBox and store it as oldLocation
        if (userLocation != null) {
            locationComboBox.addItem(userLocation);
            oldLocation = userLocation; // Update oldLocation to the initial location
        } else {
            System.out.println("Location for user " + username + " not found.");
        }

        // Add listener to locationComboBox to display old and new locations when a new location is selected
    }


        public void populateLocationComboBoxWithRoomNames() {
        List<String> roomNames = houseInstance.getRoomNames();
        locationComboBox.removeAllItems();
        for (String roomName : roomNames) {
            locationComboBox.addItem(roomName);
        }
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
        // ActionListener for saveButton
        JButton saveButton = new JButton("Save");
        saveButton.addActionListener(e -> {
            saveChanges();
            // Example of logging an event
        });

        // ActionListener for cancelButton
        JButton cancelButton = new JButton("Cancel");
        cancelButton.addActionListener(e -> {
            dispose();
            // Example of logging an event
        });

        // Add save and cancel buttons to the button panel
        JPanel buttonPanel = new JPanel();
        buttonPanel.add(saveButton);
        buttonPanel.add(cancelButton);

        // Add the button panel to the dialog
        getContentPane().add(buttonPanel, BorderLayout.SOUTH);
    }

    private void saveChanges() {
        String inhabitant = (String) inhabitantComboBox.getSelectedItem();
        String newLocation = (String) locationComboBox.getSelectedItem();

        List<Room> rooms = houseInstance.getRooms();

        int new_x_bound = 0;
        int new_y_bound = 0;
        for (Room r: rooms) {
            if(r.getRoomName().equals(newLocation)){
                new_x_bound = r.getX();
                new_y_bound = r.getY();
            }
        }

        JLabel jLabel = userLabels.get(inhabitant);

        if (jLabel != null) { // Check if the label exists
            jLabel.setBounds(new_x_bound + (int)(Math.random() * 2 + 10), new_y_bound, 30, 30); // Move the label to the new location
        } else {
            System.err.println("Label not found for inhabitant: " + inhabitant);
            return; // Exit the method if the label is not found
        }

        if (newLocation.equals("Outside")) {
            System.out.println("Moving " + inhabitant + " outside the home");
        } else {
            System.out.println("Placing " + inhabitant + " from " + oldLocation + " to " + newLocation);

            // Log the location change in database
            logEntry.LocationLog(user.getLoggedInUser().getUsername(), inhabitant , oldLocation, newLocation);

            // Update the location in the user's file
            updateUserLocation(inhabitant, newLocation);
            // Update the oldLocation to the new location
            oldLocation = newLocation;
            // Assuming you have a JLabel locationTag to display the location
            locationTag.setText(newLocation); // Update the displayed location in locationTag
        }



        dispose();
    }

    private void updateUserLocation(String username, String newLocation) {
//        // Get the instance of UserAccountManager

//
//        // Update the location in the user's file


        UserAccountManager userAccountManager1 = new UserAccountManager("database/" + user.getLoggedInUser().getUsername() + ".txt");
        userAccountManager1.updateUserLocation(username, newLocation);
    }




    private void displayLoggedInUserContent(List<String> loggedInUserContent) {
        // Display the content of the logged-in user's file
        // For demonstration, let's print it to the console
        for (String line : loggedInUserContent) {

      }
    }

    public void updateUserDropdown(List<String> updatedUsernames, String selectedUsername) {
        populateInhabitantComboBox(updatedUsernames, selectedUsername);
    }

    public String getSelectedLocation() {
        return (String) locationComboBox.getSelectedItem();
    }


    private void populateInhabitantComboBox(List<String> usernames, String username) {
        inhabitantComboBox.removeAllItems();
        for (String name : usernames) {
            inhabitantComboBox.addItem(name);
        }
        inhabitantComboBox.setSelectedItem(username);
    }

    public String getSelectedUsername() {
        return selectedUsername;
    }
}
