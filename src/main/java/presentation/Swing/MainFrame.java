package presentation.Swing;


import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import domain.dateTime.Date;
import domain.dateTime.Time;
import domain.editbutton.EditHouseInhabitantsDialog;
import domain.house.House;

import domain.house.Room;
import domain.house.Zone;
import domain.sensors.Door;
import domain.sensors.Light;
import domain.sensors.Window;
import domain.smartHomeSimulator.modules.SmartHomeHeating;
import domain.user.LoggedInUser;

import domain.user.Users;
import domain.user.UsersInitializer;
import presentation.Swing.LoginAndSignUp.LogIn;
import presentation.Swing.SHC.SHCDisplay;
import presentation.Swing.SHH.ZoneManager;
import presentation.Swing.SHH.RoomTemperature;
import presentation.Swing.command.AddProfileCommand;
import presentation.Swing.command.DeleteProfileCommand;
import presentation.Swing.command.EditProfileCommand;
import presentation.Swing.command.ProfileManager;
import presentation.Swing.command.UserAccountManager;
import presentation.Swing.managePermission.PermissionsPopup;

import javax.swing.*;

import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;
import javax.swing.table.TableCellRenderer;
import java.awt.*;
import java.awt.event.*;
import java.io.*;
import java.util.HashMap;
import java.util.List;
import java.net.HttpURLConnection;
import java.net.URL;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Map;

public class MainFrame {

    private boolean SHHisOn;
    private JPanel WindowContainer;
    private JPanel titleContainer;
    private JLabel title;
    private JPanel ProfileBoard;
    private JPanel Modules;
    private JPanel houseImage;
    private JPanel consoleOutput;
    private JTabbedPane tabbedPane1;
    private JPanel SHS;
    private JPanel SHC;
    private JPanel SHP;
    private JPanel SHH;
    private JPanel comboBox4;
    private JPanel SHCPanel2;
    private JTextArea textArea1;
    private JButton buttonOff;
    private JLabel userTag;
    private JLabel locationTag;
    private JLabel temperature;
    private JLabel date;
    private JLabel time;
    private JButton buttonLogOut;
    private JPanel SetTimeDate;
    private JTextField TimeText;
    private JTextField DateText;
    private JTabbedPane ManageUsers;
    private JPanel Add;
    private JPanel Delete;
    private JPanel Edit;
    private JTextField NewUsername;
    private JTextField NewEmail;
    private JRadioButton NewparentRadioButton;
    private JRadioButton NewchildRadioButton;
    private JRadioButton NewguestRadioButton;
    private JTextField DeleteUser;
    private JLabel DeleteUsername;
    private JPasswordField NewPassword;
    private JTextField OldUsername;
    private JTextField UpdateUsername;
    private JTextField UpdateEmail;
    private JPasswordField UpdatePassword;
    private JRadioButton UpdateparentRadioButton;
    private JRadioButton UpdatechildRadioButton;
    private JRadioButton UpdateguestRadioButton;
    private JButton Add_Profile;
    private JButton Delete_Profile;
    private JButton Edit_Profile;
    private JLabel houseLayoutLabel;

    private ImageIcon houseLayout;
    private JPanel kitchenLightPanel;
    private JPanel livingRoomLightPanel;
    private JPanel bedroom2LightPanel;
    private JPanel bathroomLightPanel;
    private JPanel garageLightPanel;
    private JPanel bedroom1LightPanel;
    private JPanel hallwayLightPanel;
    private JPanel backLightPanel;
    private JPanel frontLightPanel;
    private JLabel livingLightLabel;
    private JLabel bedroom2LightLabel;
    private JLabel bedroom1LightLabel;
    private JLabel hallwayLightLabel;
    private JLabel backLightLabel;
    private JLabel bathroomLightLabel;
    private JLabel garageLightLabel;
    private JLabel frontLightLabel;
    private JLabel kitchenLightLabel;

    private JButton editButton;

    private JTable table1;
    private JTable table2;
    private JTable table3;
    private JTable table4;
    private JTable table5;
    private JPanel checkBoxPanel;
    private JComboBox comboBox;
    private JPanel screen;
    private JButton permissionsButton;
    private JPanel backDoorPanel;
    private JLabel backDoorLabel;
    private JPanel kitchenWindow1Panel;
    private JLabel kitchenWindow1Label;
    private JPanel kitchenWindow2Panel;
    private JLabel kitchenWindow2Label;
    private JPanel livingWindow1Panel;
    private JLabel livingWindow1Label;
    private JPanel livingWindow2Panel;
    private JLabel livingWindow2Label;
    private JPanel bathroomWindowPanel;
    private JLabel bathroomWindowLabel;
    private JPanel bathroomDoorPanel;
    private JLabel bathroomDoorLabel;
    private JPanel garageInDoorPanel;
    private JPanel garageOutDoorPanel;
    private JLabel garageInDoorLabel;
    private JLabel garageOutDoorLabel;
    private JPanel frontDoorPanel;
    private JLabel frontDoorLabel;
    private JPanel bedroom1Window1Panel;
    private JLabel bedroom1Window1Label;
    private JPanel bedroom1Window2Panel;
    private JLabel bedroom1Window2Label;
    private JPanel bedroom1DoorPanel;
    private JLabel bedroom1DoorLabel;
    private JPanel bedroom2DoorPanel;
    private JLabel bedroom2DoorLabel;
    private JPanel bedroom2WindowPanel;
    private JLabel bedroom2WindowLabel;
    private JLabel onOffSHHLabel;
    private JButton onOffSHHButton;
    private JLabel zoneManagementLabel;
    private JPanel zoneManagement;
    private JButton zoneManagementButton;
    private JButton roomTempButton;


    private Date currentDate;
    private Time currentTime;
    private Thread timeIncrementer;
    private ProfileManager profileManager;

    private ImageIcon lightOn;
    private ImageIcon lightOff;
    private ImageIcon opened;
    private ImageIcon closed;

    private ImageIcon userIcon;

    private boolean isFrozen = false;
    LoggedInUser user;
    Map<String,JLabel> userLabels = new HashMap<>();
    Map<Room,JLabel> temperatureLabels = new HashMap<>();
    // Windows needed


    public MainFrame(LoggedInUser user) {
        this.user = user;
        House h = new House();
        SmartHomeHeating shh = new SmartHomeHeating(temperatureLabels);


        try{
            loadZones(h,shh);
        }catch (IOException e){
            System.out.println(e);
        }

        //---------------------individual room temp --------------------------
        roomTempButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                // Create an instance of your popup window
                RoomTemperature.show((JFrame) SwingUtilities.getWindowAncestor(roomTempButton), user.getLoggedInUser().getUsername(), textArea1);
            }
        });


        //----------------------zone management------------------------------

        zoneManagementButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                // Create an instance of your popup window
                ZoneManager.show((JFrame) SwingUtilities.getWindowAncestor(zoneManagementButton),h,shh, user.getLoggedInUser().getUsername(), textArea1);
            }
        });


        //----------------------PermissionPopup----------------------------------------------------------
        permissionsButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                PermissionsPopup.show((JFrame) SwingUtilities.getWindowAncestor(permissionsButton));

                LogEntry.setTextArea(textArea1);
                LogEntry.Permissionlog(user.getLoggedInUser().getUsername(),"SHS Module", "Manage Permissions", "Edit User's Permissions");
            }
        });


        //-------------------------Set Date and Time--------------------------------------------------------

        //Sets Date and Time on the DASHBOARD
        currentDate = new Date();
        currentTime = new Time();

        date.setText(currentDate.toString());
        time.setText(currentTime.toString());

        //Updates Time every second
        Timer defaultTimer = new Timer(1000, e -> updateDateTime());
        defaultTimer.start();

        DateText.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent event) {
                updateDate();
            }
        });

        TimeText.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent event) {
                updateTime();
            }
        });

        //--------------------------Account Management-----------------------------------------------------------------


        UserAccountManager userAccountManager = new UserAccountManager("database/Users.txt");

        AddProfileCommand addProfileCommand = new AddProfileCommand(userAccountManager, "", "", "", "");
        DeleteProfileCommand deleteProfileCommand = new DeleteProfileCommand(userAccountManager, "");
        EditProfileCommand editProfileCommand = new EditProfileCommand(userAccountManager, "", "", "", "", "");

        profileManager = new ProfileManager(addProfileCommand, deleteProfileCommand, editProfileCommand);

        //Adds the new user profile to the text file
        Add_Profile.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String username = getNewUsername().getText();
                String email = getNewEmail().getText();
                String password = getNewPassword().getText();
                String accessibility = "";
                if (getParentRadioButton().isSelected()) {
                    accessibility = "Parent";
                } else if (getChildRadioButton().isSelected()) {
                    accessibility = "Child";
                } else if (getGuestRadioButton().isSelected()) {
                    accessibility = "Guest";
                }

                //Create String for Log Entry
                String addLog = "Add User Profile: " + username;

                AddProfileCommand addProfileCommand = new AddProfileCommand(userAccountManager, username, email, password, accessibility);
                addProfileCommand.execute();

                LogEntry.setTextArea(textArea1);
                LogEntry.Profilelog(user.getLoggedInUser().getUsername(),"SHS Module", "Manage User Profile", addLog);

                JOptionPane.showMessageDialog(WindowContainer, "User Profile Added Successfully!");
            }
        });

    // Assuming you have a JLabel locationTag to display the location
        String loggedInUsername = userAccountManager.getLoggedInUsername();
        String oldLocation = userAccountManager.getUserLocation(loggedInUsername);
        locationTag.setText(oldLocation);

        //Edit User Location
        editButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent event) {
                // Get the username of the logged-in user from the UserAccountManager
                String loggedInUsername = userAccountManager.getLoggedInUsername();

                // Check if the loggedInUsername is not null
                if (loggedInUsername == null) {
                    System.err.println("Error: No logged-in user found.");
                    return; // Handle the error appropriately
                }
                String oldLocation = userAccountManager.getUserLocation(loggedInUsername);

                // Get the content of the logged-in user's file
                List<String> firstColumnContent = userAccountManager.getFirstColumnContent(loggedInUsername);

                EditHouseInhabitantsDialog dialog = new EditHouseInhabitantsDialog(
                        (Frame) SwingUtilities.getWindowAncestor(WindowContainer), // parent Frame
                        userAccountManager, // UserAccountManager instance
                        firstColumnContent, // List of first column content
                        loggedInUsername, // Selected username
                        h, // Pass your House instance here
                        userLabels,
                        user,
                        locationTag,// Pass the locationTag JLabel to the dialog
                        textArea1 //For Output Console
                );

                // Populate locationComboBox with room names from House instance
                dialog.populateLocationComboBoxWithRoomNames();

                // Update the locationTag with the old location
                locationTag.setText(oldLocation);

                // Display the dialog
                dialog.setVisible(true);

                // After making the dialog visible, display the selected username within the dialog
                JLabel selectedUsernameLabel = new JLabel("Selected Username: " + loggedInUsername);
                dialog.getContentPane().add(selectedUsernameLabel, BorderLayout.NORTH);

                // Get the new location selected in the dialog
                String newLocation = dialog.getSelectedLocation();

                // Find the old and new rooms
                Room oldRoom = null;
                Room newRoom = null;
                for (Room r : h.getRooms()) {
                    if (r.getRoomName().equals(oldLocation)) {
                        oldRoom = r;
                    }
                    if (r.getRoomName().equals(newLocation)) {
                        newRoom = r;
                    }
                }

                // Turn off lights in the old room
                if (oldRoom != null) {
                    for (Light light : oldRoom.getLights()) {
                        light.turnOff(); // Assuming you have a method to turn off the light
                        // The associated JLabel's icon will be updated automatically
                    }
                }

                // Update the user's location in the UserAccountManager
                userAccountManager.updateUserLocation(loggedInUsername, newLocation);

                // Turn on lights in the new room
                if (newRoom != null) {
                    for (Light light : newRoom.getLights()) {
                        light.turnOn(); // Assuming you have a method to turn on the light
                        // The associated JLabel's icon will be updated automatically
                    }
                }

                // Refresh the display to reflect the changes
                refreshDisplay(); // Implement this method to refresh the display
            }

            private void refreshDisplay() {
                // Implement logic to refresh the display
            }
        });



        //Deletes the user profile to the text file
        Delete_Profile.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String usernameToDelete = getDeleteUser().getText();
                String deleteLog = "Delete User Profile: " + usernameToDelete; //Create String for Log Entry

                DeleteProfileCommand deleteProfileCommand = new DeleteProfileCommand(userAccountManager, usernameToDelete);
                deleteProfileCommand.execute();

                LogEntry.setTextArea(textArea1);
                LogEntry.Profilelog(user.getLoggedInUser().getUsername(),"SHS Module", "Manage User Profile", deleteLog);

                JOptionPane.showMessageDialog(WindowContainer, "User Profile Deleted Successfully!");
            }
        });

        //Edits the user profile in the text file
        Edit_Profile.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String oldUsername = getOldUsername().getText();
                String username = getUpdateUsername().getText();
                String email = getUpdateEmail().getText();
                String password = getUpdatePassword().getText();
                String accessibility = "";
                if (getUpdateparentRadioButton().isSelected()) {
                    accessibility = "Parent";
                } else if (getUpdatechildRadioButton().isSelected()) {
                    accessibility = "Child";
                } else if (getUpdateguestRadioButton().isSelected()) {
                    accessibility = "Guest";
                }

                String editLog = "Editing User Profile: " + oldUsername; //Create String for Log Entry

                EditProfileCommand editProfileCommand = new EditProfileCommand(userAccountManager, oldUsername, username, email, password, accessibility);
                editProfileCommand.execute();

                LogEntry.setTextArea(textArea1);
                LogEntry.Profilelog(user.getLoggedInUser().getUsername(),"SHS Module", "Manage User Profile", editLog);

                JOptionPane.showMessageDialog(WindowContainer, "User Profile Edited Successfully!");
            }
        });

        JFrame frame = new JFrame("Dashboard");
        frame.setContentPane(WindowContainer);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(1250, 700);
        frame.setLocationRelativeTo(null);

        //-----------------------------------2D House Layout--------------------------------------------------------------------------

        // Load house layout image
        houseLayout = new ImageIcon("images/houseLayout.png");
        Image image = houseLayout.getImage().getScaledInstance(700, 473, Image.SCALE_SMOOTH);
        houseLayoutLabel.setIcon(new ImageIcon(image));
        houseLayoutLabel.setText("house layout image");

        houseImage.setLayout(null); // Set null layout for absolute positioning

        // Load light icon for each room
        lightOff = new ImageIcon("images/lightOff.png");
        Image lightOffImage = lightOff.getImage().getScaledInstance(30, 30, Image.SCALE_SMOOTH);
        lightOff = new ImageIcon(lightOffImage);
        lightOn = new ImageIcon("images/lightOn.png");
        Image lightOnImage = lightOn.getImage().getScaledInstance(30, 30, Image.SCALE_SMOOTH);
        lightOn = new ImageIcon(lightOnImage);

        //Load open/closed icon
        opened = new ImageIcon("images/open.png");
        Image openImage = opened.getImage().getScaledInstance(30, 30, Image.SCALE_SMOOTH);
        opened = new ImageIcon(openImage);
        closed = new ImageIcon("images/closed.png");
        Image closedImage = closed.getImage().getScaledInstance(30, 30, Image.SCALE_SMOOTH);
        closed = new ImageIcon(closedImage);

        // Load user icon.
        userIcon = new ImageIcon("images/UserIcon.png");
        Image userImage = userIcon.getImage().getScaledInstance(20,20,Image.SCALE_SMOOTH);
        userIcon = new ImageIcon(userImage);


        List<Light> houseLights = h.getLights();
        Map<Light, JLabel> lightLabels = new HashMap<>();

        for (Light light: houseLights) {
            JLabel label = new JLabel();
            if(light.isOpen()){
                label.setIcon(lightOn);
            }
            else{
                label.setIcon(lightOff);
            }
            label.setBounds(light.getX(),light.getY(),30,30);
            light.setLightLabel(label);
            houseImage.add(label);
            lightLabels.put(light, label);
        }

        List<Door> houseDoors = h.getDoors();
        Map<Door,JLabel> doorLabels = new HashMap<>();

        for(Door door: houseDoors){
            JLabel label= new JLabel();
            if(door.isOpen()){
                label.setIcon(opened);
            }
            else{
                label.setIcon(closed);
            }
            label.setBounds(door.getX(),door.getY(),30,30);
            door.setDoorLabel(label);
            houseImage.add(label);
            doorLabels.put(door,label);
        }

        List<Window> houseWindows = h.getWindows();
        Map<Window,JLabel> windowLabels = new HashMap<>();

        for(Window window: houseWindows){
            JLabel label = new JLabel();
            if(window.isOpen()){
                label.setIcon(opened);
            }
            else{
                label.setIcon(closed);
            }
            label.setBounds(window.getX(),window.getY(),30,30);
            window.setWindowLabel(label);
            houseImage.add(label);
            windowLabels.put(window,label);
        }


        List<Users> usersList = UsersInitializer.getAllUsers();
        List<Room> rooms = h.getRooms();

// Keep track of whether any user is in a room
        boolean userInAnyRoom = false;

// Inside the method or event where user icons are added to the layout
        for (Users u : usersList) {
            String location = u.getLocation();
            boolean userInRoom = false;
            for (Room r : rooms) {
                if (r.getRoomName().equals(location)) {
                    // Assuming user location matches room name correctly
                    JLabel label = new JLabel();
                    label.setIcon(userIcon);
                    if (u.getUsername().equals(user.getLoggedInUser().getUsername())) {
                        label.setForeground(Color.red);
                        label.setText(u.getUsername());
                        label.setHorizontalTextPosition(JLabel.CENTER);
                        label.setVerticalTextPosition(JLabel.CENTER);
                    }
                    label.setBounds(r.getX() + (int) (Math.random() * 10) + 4, r.getY() + (int) (Math.random() * 10) + 2, 30, 30);
                    houseImage.add(label);
                    userLabels.put(u.getUsername(), label);

                    // Mark that the user is in the room
                    userInRoom = true;

                    // Turn on lights in the room
                    List<Light> lights = r.getLights();
                    for (Light light : lights) {
                        light.turnOn(); // Assuming you have a method to turn on the light
                        // The associated JLabel's icon will be updated automatically
                    }

                    // Break the loop once user is found in the room
                    break;
                }
            }
            // Update the overall status of whether any user is in a room
            userInAnyRoom |= userInRoom;
        }

// After processing all users, check if any user is in any room
        if (!userInAnyRoom) {
            // If no user is in any room, turn off lights in all rooms
            for (Room r : rooms) {
                List<Light> lights = r.getLights();
                for (Light light : lights) {
                    light.turnOff(); // Assuming you have a method to turn off the light
                    // The associated JLabel's icon will be updated automatically
                }
            }
        }



        for (Room r: rooms) {
            if(!(r.getRoomName().equals("Outside"))){
                JLabel label = new JLabel();
                String temp = Double.toString(r.getTemperature());
                label.setForeground(Color.blue);
                label.setText(temp + "°");
                label.setBounds(r.getX(),r.getY() - 30,35,35);
                houseImage.add(label);
                temperatureLabels.put(r,label);
            }
        }

        houseLayoutLabel.setBounds(0, -60, 550, 500);
        houseImage.add(houseLayoutLabel, BorderLayout.CENTER);

        //-------------------------------------------------------------------------------------------------------------

        tabbedPane1.addChangeListener(new ChangeListener() {
            @Override
            public void stateChanged(ChangeEvent e) {
                int selectedTabIndex = tabbedPane1.getSelectedIndex();
                int specificTabIndex = 1; // Replace 0 with the index of the specific tab you want
                if (selectedTabIndex == specificTabIndex) {
                    comboBox.setSelectedItem("Select Item");
                }
            }
        });

        comboBox.addItem("Select Item");
        comboBox.addItem("Doors");
        comboBox.addItem("Windows");
        comboBox.addItem("Lights");

        comboBox.addActionListener(new ActionListener() {

            @Override
            public void actionPerformed(ActionEvent e) {
                String selectedItem = (String) comboBox.getSelectedItem();
                SHCDisplay displayHelper = new SHCDisplay(checkBoxPanel,h,selectedItem);
                if (selectedItem.equals("Doors")) {
                    displayHelper.displayItems(h.getDoors());
                } else if (selectedItem.equals("Windows")) {
                    displayHelper.displayItems(h.getWindows());
                } else if (selectedItem.equals("Lights")) {
                    displayHelper.displayItems(h.getLights());
                } else if (selectedItem.equals("Select Item")) {
                    displayHelper.clearPanel();
                }
            }
        });
// Inside the constructor or initialization method of MainFrame
        buttonLogOut.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                // Dispose of or close the current window
                JFrame frame = (JFrame) SwingUtilities.getWindowAncestor(WindowContainer);
                frame.dispose();

                // Create a new instance of the login page
                LogIn loginPage = new LogIn(); // Assuming LoginPage is your login page class

                // Show the login page
                loginPage.setVisible(true);            }
        });



        //-------------------------------------------------------------------------------------------------------------

        // ON/OFF SHH button
        SHHisOn = false;
        onOffSHHButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                // toggle the state
                SHHisOn = !SHHisOn;
                if (SHHisOn) {
                    onOffSHHButton.setText("On");
                    shh.setActive(true);

                    LogEntry.setTextArea(textArea1);
                    LogEntry.SHHButtonlog(user.getLoggedInUser().getUsername(), "Smart Home Heating is turned on.");
                    System.out.println("Button is turned ON");

                } else {
                    onOffSHHButton.setText("Off");
                    shh.setActive(false);

                    LogEntry.setTextArea(textArea1);
                    LogEntry.SHHButtonlog(user.getLoggedInUser().getUsername(), "Smart Home Heating is turned oFF.");
                    System.out.println("Button is turned OFF");
                }
            }
        });

        //-----------------------------------ON/OFF Simulator button--------------------------------------------------------------------------


        buttonOff.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent event) {
                if (!isFrozen) {
                    // Freeze all components except the off button
                    freezeComponents();
                    buttonOff.setText("ON");
                } else {
                    // Unfreeze all components
                    unfreezeComponents();
                    buttonOff.setText("Off");
                }
                // Toggle freeze state
                isFrozen = !isFrozen;
            }
        });

        //-------------------------------temperature API--------------------------------------------------------------
        try {
            String jsonString = getTemperatureJSON();

            ObjectMapper objectMapper = new ObjectMapper();
            JsonNode jsonResponse = objectMapper.readTree(jsonString);

            // Get the current timestamp
            LocalDateTime currentTime = LocalDateTime.now();
            DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd'T'HH:mm");
            String currentTimestamp = currentTime.format(formatter);

            // Find the closest timestamp in the API response
            int index = -1;
            JsonNode timeArray = jsonResponse.get("hourly").get("time");
            for (int i = 0; i < timeArray.size(); i++) {
                String timestamp = timeArray.get(i).asText();
                if (timestamp.equals(currentTimestamp) || timestamp.compareTo(currentTimestamp) > 0) {
                    index = i;
                    break;
                }
            }

            // Extract temperature if index is found
            if (index != -1) {
                double temperature1 = jsonResponse.get("hourly").get("temperature_2m").get(index).asDouble();
                temperature.setText("Outside Temperature " + ": " + temperature1 + "°C");
                shh.setOutsideTemp(temperature1);
            } else {
                temperature.setText("Temperature data not found for the current time.");
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }


    private void freezeComponents() {
        // Disable all components
        setComponentEnabled(WindowContainer, false);
        time.setVisible(false);
        // Enable the off button
        buttonOff.setEnabled(true);
    }

    // Method to unfreeze all components
    private void unfreezeComponents() {
        // Enable all components
        setComponentEnabled(WindowContainer, true);
        time.setVisible(true);
        // Enable the off button
        buttonOff.setEnabled(true);
    }

    // Recursive method to set component enabled state
    private void setComponentEnabled(Component component, boolean enabled) {
        if (component instanceof Container) {
            Component[] components = ((Container) component).getComponents();
            for (Component comp : components) {
                setComponentEnabled(comp, enabled);
            }
        }
        component.setEnabled(enabled);

    }


    public void showMainFrame() {
        JFrame frame = new JFrame("Dashboard");
        frame.setContentPane(WindowContainer);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(1250, 700);
        frame.setLocationRelativeTo(null);
        frame.setVisible(true);
    }

    //Updates display of Time and Date
    private void updateDateTime() {
        // Only update the time and date labels if they are not already being edited by the user
        if (!TimeText.isFocusOwner() && !DateText.isFocusOwner()) {
            Date currentDate = new Date();
            Time currentTime = new Time();

            date.setText(currentDate.toString());
            time.setText(currentTime.toString());
        }
    }

    // Update date based on user input
    private void updateDate() {
        String oldDateStr = date.getText();
        String newDateStr = DateText.getText();
        currentDate = new Date(newDateStr);
        date.setText(currentDate.toString());

        LogEntry.setTextArea(textArea1);
        LogEntry.DateTimelog(user.getLoggedInUser().getUsername(), "SHS Module", "Time and Date Settings", "Modification of Date", oldDateStr, newDateStr);

        JOptionPane.showMessageDialog(WindowContainer, "Date Updated Successfully");

    }

    // Update time based on user input
    private void updateTime() {
        String oldDateStr = time.getText();
        String newTimeStr = TimeText.getText();
        currentTime = new Time(newTimeStr);
        time.setText(currentTime.toString());

        startIncrementingTime();

        LogEntry.setTextArea(textArea1);
        LogEntry.DateTimelog(user.getLoggedInUser().getUsername(),"SHS Module", "Time and Date Settings", "Modification of Time", oldDateStr, newTimeStr);

        JOptionPane.showMessageDialog(WindowContainer, "Time Updated Successfully");
    }

    private void startIncrementingTime() {
        if (timeIncrementer == null) {
            timeIncrementer = new Thread(new Runnable() {
                @Override
                public void run() {
                    while (true) {
                        try {
                            Thread.sleep(1000);
                            currentTime.increment();
                            SwingUtilities.invokeLater(new Runnable() {
                                @Override
                                public void run() {
                                    time.setText(currentTime.toString());
                                }
                            });
                        } catch (InterruptedException e) {
                        }
                    }
                }
            });
            timeIncrementer.start();
        }
    }

    private void stopIncrementingTime() {
        if (timeIncrementer != null && timeIncrementer.isAlive()) {
            timeIncrementer.interrupt();
            timeIncrementer = null;
        }
    }


    //Getters for Adding User Profile to text file
    public JTextField getNewUsername() {
        return NewUsername;
    }

    public JTextField getNewEmail() {
        return NewEmail;
    }

    public JPasswordField getNewPassword() {
        return NewPassword;
    }

    public JRadioButton getParentRadioButton() {
        return NewparentRadioButton;
    }

    public JRadioButton getChildRadioButton() {
        return NewchildRadioButton;
    }

    public JRadioButton getGuestRadioButton() {
        return NewguestRadioButton;
    }

    //Getters for Deleting User Profile from text file
    public JTextField getDeleteUser() {
        return DeleteUser;
    }

    //Getters for Editing User Profile in text file
    public JTextField getOldUsername() {
        return OldUsername;
    }

    public JTextField getUpdateUsername() {
        return UpdateUsername;
    }

    public JTextField getUpdateEmail() {
        return UpdateEmail;
    }

    public JPasswordField getUpdatePassword() {
        return UpdatePassword;
    }

    public JRadioButton getUpdateparentRadioButton() {
        return UpdateparentRadioButton;
    }

    public JRadioButton getUpdatechildRadioButton() {
        return UpdatechildRadioButton;
    }

    public JRadioButton getUpdateguestRadioButton() {
        return UpdateguestRadioButton;
    }


    // rendering checkboxes in the table
    class CheckBoxRenderer extends JCheckBox implements TableCellRenderer {
        CheckBoxRenderer() {
            super();
            setHorizontalAlignment(JLabel.CENTER);
        }

        public Component getTableCellRendererComponent(JTable table, Object value, boolean isSelected,
                                                       boolean hasFocus, int row, int column) {
            setSelected(value != null && (boolean) value);
            return this;
        }
    }



    private void updateFile() {
        try {
            File inputFile = new File("database/permissions.txt");
            File tempFile = new File("database/tempUsers.txt");

            BufferedReader reader = new BufferedReader(new FileReader(inputFile));
            PrintWriter writer = new PrintWriter(new FileWriter(tempFile));

            String line;
            int row = 0;
            while ((line = reader.readLine()) != null) {
                String[] parts = line.split("\\|");
                boolean windowAnywhere = (boolean) table1.getValueAt(row, 1);
                boolean windowInsideHome = (boolean) table1.getValueAt(row, 2);
                boolean windowInsideRoom = (boolean) table1.getValueAt(row, 3);
                boolean doorAnywhere = (boolean) table2.getValueAt(row, 1);
                boolean doorInsideHome = (boolean) table2.getValueAt(row, 2);
                boolean doorInsideRoom = (boolean) table2.getValueAt(row, 3);
                boolean lightAnywhere = (boolean) table3.getValueAt(row, 1);
                boolean lightInsideHome = (boolean) table3.getValueAt(row, 2);
                boolean lightInsideRoom = (boolean) table3.getValueAt(row, 3);
                boolean awaymode = (boolean) table4.getValueAt(row, 1);
                boolean heating = (boolean) table5.getValueAt(row, 1);

                // Keep the other parts intact
                writer.println(parts[0] + "|" + windowAnywhere + "|" + windowInsideHome + "|" + windowInsideRoom  + "|"  + doorAnywhere+ "|" +
                        doorInsideHome + "|" + doorInsideRoom+ "|"  + lightAnywhere+ "|" +
                        lightInsideHome + "|" + lightInsideRoom + "|" + awaymode + "|" + heating);
                row++;
            }
            reader.close();
            writer.close();

            if (!inputFile.delete()) {
                System.out.println("Could not delete file");
                return;
            }
            if (!tempFile.renameTo(inputFile)) {
                System.out.println("Could not rename file");
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }



    private static String getTemperatureJSON() throws Exception {
        URL url = new URL("https://api.open-meteo.com/v1/forecast?latitude=45.5088&longitude=-73.5878&hourly=temperature_2m");
        HttpURLConnection connection = (HttpURLConnection) url.openConnection();
        connection.setRequestMethod("GET");

        int responseCode = connection.getResponseCode();
        if (responseCode == HttpURLConnection.HTTP_OK) {
            BufferedReader in = new BufferedReader(new InputStreamReader(connection.getInputStream()));
            StringBuilder response = new StringBuilder();
            String inputLine;

            while ((inputLine = in.readLine()) != null) {
                response.append(inputLine);
            }
            in.close();

            return response.toString();
        } else {
            throw new Exception("Failed to fetch temperature data. Response code: " + responseCode);
        }
    }

    public void loadZones(House h, SmartHomeHeating shh) throws IOException {
        ObjectMapper mapper = new ObjectMapper();
        String file = "database/zones.json";
        List<Map<String, Object>> zoneList = mapper.readValue(new File(file), new TypeReference<List<Map<String, Object>>>() {});
        for (Map<String, Object> zoneMap : zoneList) {
            String zoneName = (String) zoneMap.get("zoneName");
            double desiredTemperature = (double) zoneMap.get("desiredTemperature");
            Zone zone = new Zone(zoneName,desiredTemperature);
            List<Map<String, Object>> roomList = (List<Map<String, Object>>) zoneMap.get("rooms");
            for (Map<String, Object> roomMap : roomList) {
                String roomName = roomMap.get("roomName").toString();
                List<Room> rooms = h.getRooms();
                for (Room r:rooms) {
                    if(r.getRoomName().equals(roomName)){
                        zone.addRoomToZone(r);
                    }
                }
            }
            shh.attach(zone);
        }

    }


    public JPanel getZoneManagementPanel() {
        return zoneManagement;
    }

}
