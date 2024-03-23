package presentation.Swing;


import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import domain.dateTime.Date;
import domain.dateTime.Time;
import domain.editbutton.EditHouseInhabitantsDialog;
import domain.house.House;
import domain.house.Room;
import domain.sensors.Door;
import domain.sensors.Light;
import domain.sensors.Window;
import presentation.Swing.SHC.SHCDisplay;
import presentation.Swing.SHC.SHCTableModel;
import presentation.Swing.command.AddProfileCommand;
import presentation.Swing.command.DeleteProfileCommand;
import presentation.Swing.command.EditProfileCommand;
import presentation.Swing.command.ProfileManager;
import presentation.Swing.command.UserAccountManager;

import javax.swing.*;
import javax.swing.event.TableModelEvent;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.JTableHeader;
import javax.swing.table.TableCellRenderer;
import java.awt.*;
import java.awt.event.*;
import java.io.*;
import java.util.List;
import java.util.ArrayList;
import java.net.HttpURLConnection;
import java.net.URL;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Scanner;

public class MainFrame {


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
    private JComboBox comboBox2;
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
    private JButton editButton;

    private JTabbedPane PermissionsPane;
    private JPanel SHCP;
    private JTable table1;
    private JTabbedPane tabbedPane2;
    private JScrollPane windowPermission;
    private JScrollPane doorPermission;
    private JTable table2;
    private JScrollPane lightPermission;
    private JTable table3;
    private JScrollPane awaymode;
    private JTable table4;
    private JScrollPane heating;
    private JTable table5;
    private JPanel checkBoxPanel;
    private JPanel SHCPanel1;
    private JComboBox comboBox;
    private JPanel screen;

    private Date currentDate;
    private Time currentTime;
    private Thread timeIncrementer;
    private ProfileManager profileManager;

    private ImageIcon houseLayout;

    // Lights for each room (true = on, false = off)
    private boolean bathroomLight;
    private boolean bedroom1Light;
    private boolean bedroom2Light;
    private boolean kitchenLight;
    private boolean livingroomLight;
    private boolean garageLight;
    private boolean hallwayLight;
    private boolean frontLight; //front yard light
    private boolean backLight; //backyard light

    // Doors (true = open, false = closed)
    private boolean frontDoor;
    private boolean backDoor;
    private boolean bedroom1Door;
    private boolean bedroom2Door;
    private boolean bathroomDoor;
    private boolean garageInsideDoor;
    private boolean garageOutsideDoor;
    private boolean isFrozen = false;
    // Windows needed

    // c
    public MainFrame() {
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


        editButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent event) {
                EditHouseInhabitantsDialog dialog = new EditHouseInhabitantsDialog((Frame) SwingUtilities.getWindowAncestor(WindowContainer));
                dialog.setVisible(true);
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

                AddProfileCommand addProfileCommand = new AddProfileCommand(userAccountManager, username, email, password, accessibility);
                addProfileCommand.execute();

                LogEntry.setTextArea(textArea1);
                LogEntry.Profilelog("SHS Module", "Manage User Profiles", "Add a User Profile");

                JOptionPane.showMessageDialog(WindowContainer, "User Profile Added Successfully!");

            }
        });

        //Deletes the user profile to the text file
        Delete_Profile.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String usernameToDelete = getDeleteUser().getText();

                DeleteProfileCommand deleteProfileCommand = new DeleteProfileCommand(userAccountManager, usernameToDelete);
                deleteProfileCommand.execute();

                LogEntry.setTextArea(textArea1);
                LogEntry.Profilelog("SHS Module", "Manage User Profiles", "Delete a User Profile");

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

                EditProfileCommand editProfileCommand = new EditProfileCommand(userAccountManager, oldUsername, username, email, password, accessibility);
                editProfileCommand.execute();

                LogEntry.setTextArea(textArea1);
                LogEntry.Profilelog("SHS Module", "Manage User Profiles", "Edit a User Profile");

                JOptionPane.showMessageDialog(WindowContainer, "User Profile Edited Successfully!");
            }
        });


        JFrame frame = new JFrame("Dashboard");
        frame.setContentPane(WindowContainer);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(1250, 700);
        frame.setLocationRelativeTo(null);

        // Load the image with the specified width and height
        houseLayout = new ImageIcon("images/houseLayout.png");
        Image image = houseLayout.getImage().getScaledInstance(700, 473, Image.SCALE_SMOOTH);
        houseLayoutLabel.setIcon(new ImageIcon(image));
        houseLayoutLabel.setText("house layout image");
        houseImage.setLayout(new BorderLayout());
        houseImage.add(houseLayoutLabel, BorderLayout.CENTER);


        //-------------------------------------Permissions Tables-------------------------------------------------------

        //--------------------------------Window---------------------------------------------
        DefaultTableModel model1 = new DefaultTableModel();
        model1.addColumn("User");
        model1.addColumn("Anywhere");
        model1.addColumn("Inside Home");
        model1.addColumn("Inside Room");

        try {
            File file = new File("database/permissions.txt");
            Scanner scanner = new Scanner(file);
            while (scanner.hasNextLine()) {
                String line = scanner.nextLine();
                String[] parts = line.split("\\|");
                String username = parts[0];
                boolean anywhere = Boolean.parseBoolean(parts[1]);
                boolean insideHome = Boolean.parseBoolean(parts[2]);
                boolean insideRoom = Boolean.parseBoolean(parts[3]);
                model1.addRow(new Object[]{username, anywhere, insideHome, insideRoom});
            }
            scanner.close();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }

        table1.setModel(model1);

        table1.getColumnModel().getColumn(1).setCellRenderer(new CheckBoxRenderer());
        table1.getColumnModel().getColumn(2).setCellRenderer(new CheckBoxRenderer());
        table1.getColumnModel().getColumn(3).setCellRenderer(new CheckBoxRenderer());

        table1.addMouseListener(new MouseAdapter() {
            public void mouseClicked(MouseEvent e) {
                int column = table1.getColumnModel().getColumnIndexAtX(e.getX());
                int row = e.getY() / table1.getRowHeight();

                if (row < table1.getRowCount() && column < table1.getColumnCount() && row >= 0 && column >= 0) {
                    Object value = table1.getValueAt(row, column);
                    if (value instanceof Boolean) {
                        table1.setValueAt(!(Boolean) value, row, column);
                        updateFile(); // Update file after checkbox modification
                    }
                }
            }
        });

        //--------------------------------Door---------------------------------------------
        DefaultTableModel model2 = new DefaultTableModel();
        model2.addColumn("User");
        model2.addColumn("Anywhere");
        model2.addColumn("Inside Home");
        model2.addColumn("Inside Room");

// Add data from file to table model for table2
        try {
            File file = new File("database/permissions.txt");
            Scanner scanner = new Scanner(file);
            while (scanner.hasNextLine()) {
                String line = scanner.nextLine();
                String[] parts = line.split("\\|");
                String username = parts[0];
                boolean anywhere = Boolean.parseBoolean(parts[4]);
                boolean insideHome = Boolean.parseBoolean(parts[5]);
                boolean insideRoom = Boolean.parseBoolean(parts[6]);
                model2.addRow(new Object[]{username, anywhere, insideHome, insideRoom});
            }
            scanner.close();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }

        table2.setModel(model2);

        table2.getColumnModel().getColumn(1).setCellRenderer(new CheckBoxRenderer());
        table2.getColumnModel().getColumn(2).setCellRenderer(new CheckBoxRenderer());
        table2.getColumnModel().getColumn(3).setCellRenderer(new CheckBoxRenderer());

        table2.addMouseListener(new MouseAdapter() {
            public void mouseClicked(MouseEvent e) {
                int column = table2.getColumnModel().getColumnIndexAtX(e.getX());
                int row = e.getY() / table2.getRowHeight();

                if (row < table2.getRowCount() && column < table2.getColumnCount() && row >= 0 && column >= 0) {
                    Object value = table2.getValueAt(row, column);
                    if (value instanceof Boolean) {
                        table2.setValueAt(!(Boolean) value, row, column);
                        updateFile(); // Update file after checkbox modification for table2
                    }
                }
            }
        });

        //--------------------------------Light---------------------------------------------

        DefaultTableModel model3 = new DefaultTableModel();
        model3.addColumn("User");
        model3.addColumn("Anywhere");
        model3.addColumn("Inside Home");
        model3.addColumn("Inside Room");

        try {
            File file = new File("database/permissions.txt");
            Scanner scanner = new Scanner(file);
            while (scanner.hasNextLine()) {
                String line = scanner.nextLine();
                String[] parts = line.split("\\|");
                String username = parts[0];
                boolean anywhere = Boolean.parseBoolean(parts[7]);
                boolean insideHome = Boolean.parseBoolean(parts[8]);
                boolean insideRoom = Boolean.parseBoolean(parts[9]);
                model3.addRow(new Object[]{username, anywhere, insideHome, insideRoom});
            }
            scanner.close();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }

        table3.setModel(model3);

        table3.getColumnModel().getColumn(1).setCellRenderer(new CheckBoxRenderer());
        table3.getColumnModel().getColumn(2).setCellRenderer(new CheckBoxRenderer());
        table3.getColumnModel().getColumn(3).setCellRenderer(new CheckBoxRenderer());

        table3.addMouseListener(new MouseAdapter() {
            public void mouseClicked(MouseEvent e) {
                int column = table3.getColumnModel().getColumnIndexAtX(e.getX());
                int row = e.getY() / table3.getRowHeight();

                if (row < table3.getRowCount() && column < table3.getColumnCount() && row >= 0 && column >= 0) {
                    Object value = table3.getValueAt(row, column);
                    if (value instanceof Boolean) {
                        table3.setValueAt(!(Boolean) value, row, column);
                        updateFile(); // Update file after checkbox modification
                    }
                }
            }
        });


        //--------------------------------Security---------------------------------------------

        DefaultTableModel model4 = new DefaultTableModel();
        model4.addColumn("User");
        model4.addColumn("Away Mode");

        try {
            File file = new File("database/permissions.txt");
            Scanner scanner = new Scanner(file);
            while (scanner.hasNextLine()) {
                String line = scanner.nextLine();
                String[] parts = line.split("\\|");
                String username = parts[0];
                boolean awaymode = Boolean.parseBoolean(parts[10]);
                model4.addRow(new Object[]{username, awaymode});
            }
            scanner.close();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }

        table4.setModel(model4);

        table4.getColumnModel().getColumn(1).setCellRenderer(new CheckBoxRenderer());

        table4.addMouseListener(new MouseAdapter() {
            public void mouseClicked(MouseEvent e) {
                int column = table4.getColumnModel().getColumnIndexAtX(e.getX());
                int row = e.getY() / table4.getRowHeight();

                if (row < table4.getRowCount() && column < table4.getColumnCount() && row >= 0 && column >= 0) {
                    Object value = table4.getValueAt(row, column);
                    if (value instanceof Boolean) {
                        table4.setValueAt(!(Boolean) value, row, column);
                        updateFile(); // Update file after checkbox modification
                    }
                }
            }
        });

        //--------------------------------Heating---------------------------------------------

        DefaultTableModel model5 = new DefaultTableModel();
        model5.addColumn("User");
        model5.addColumn("Heating");


        try {
            File file = new File("database/permissions.txt");
            Scanner scanner = new Scanner(file);
            while (scanner.hasNextLine()) {
                String line = scanner.nextLine();
                String[] parts = line.split("\\|");
                String username = parts[0];
                boolean heating = Boolean.parseBoolean(parts[11]);
                model5.addRow(new Object[]{username, heating});
            }
            scanner.close();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }

        table5.setModel(model5);

        table5.getColumnModel().getColumn(1).setCellRenderer(new CheckBoxRenderer());

        table5.addMouseListener(new MouseAdapter() {
            public void mouseClicked(MouseEvent e) {
                int column = table5.getColumnModel().getColumnIndexAtX(e.getX());
                int row = e.getY() / table5.getRowHeight();

                if (row < table5.getRowCount() && column < table5.getColumnCount() && row >= 0 && column >= 0) {
                    Object value = table5.getValueAt(row, column);
                    if (value instanceof Boolean) {
                        table5.setValueAt(!(Boolean) value, row, column);
                        updateFile(); // Update file after checkbox modification
                    }
                }
            }
        });

        //-------------------------------------------------------------------------------------------------------------



        //-------------------------------------------------------------------------------------------------------------


        //work in progress

        House h = new House();
        comboBox.addItem("Select Item");
        comboBox.addItem("Doors");
        comboBox.addItem("Windows");
        comboBox.addItem("Lights");

        comboBox.addActionListener(new ActionListener() {
            SHCDisplay displayHelper = new SHCDisplay(checkBoxPanel);
            @Override
            public void actionPerformed(ActionEvent e) {
                String selectedItem = (String) comboBox.getSelectedItem();
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



        //-------------------------------------------------------------------------------------------------------------


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
        String newDateStr = DateText.getText();
        currentDate = new Date(newDateStr);
        date.setText(currentDate.toString());

        JOptionPane.showMessageDialog(WindowContainer, "Date Updated Successfully");

    }

    // Update time based on user input
    private void updateTime() {
        String newTimeStr = TimeText.getText();
        currentTime = new Time(newTimeStr);
        time.setText(currentTime.toString());

        startIncrementingTime();

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

}
