package presentation.Swing;

import domain.dateTime.Date;
import domain.dateTime.Time;
import presentation.Swing.command.AddProfileCommand;
import presentation.Swing.command.DeleteProfileCommand;
import presentation.Swing.command.EditProfileCommand;
import presentation.Swing.command.ProfileManager;
import presentation.Swing.command.UserAccountManager;

import javax.swing.*;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableCellRenderer;
import java.awt.*;
import java.awt.event.*;
import java.io.*;
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
    private JPanel combobx1;
    private JTextArea textArea1;
    private JButton buttonOn;
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
    private JTabbedPane PermissionsPane;
    private JPanel SHCP;
    private JTable table1;
    private JTabbedPane tabbedPane2;

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

    // Windows needed

    // c
    public MainFrame() {


        // Create table model
        DefaultTableModel model = new DefaultTableModel();
        model.addColumn("User");
        model.addColumn("Anywhere");
        model.addColumn("Inside Home");
        model.addColumn("Inside Room");

        // Add data from file to table model
        try {
            File file = new File("database/Users.txt");
            Scanner scanner = new Scanner(file);
            while (scanner.hasNextLine()) {
                String line = scanner.nextLine();
                String[] parts = line.split("\\|");
                String username = parts[0];
                boolean anywhere = Boolean.parseBoolean(parts[5]);
                boolean insideHome = Boolean.parseBoolean(parts[6]);
                boolean insideRoom = Boolean.parseBoolean(parts[7]);
                model.addRow(new Object[]{username, anywhere, insideHome, insideRoom});
            }
            scanner.close();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }

        // Add table model to table
        table1.setModel(model);

        // Customize rendering of checkboxes
        table1.getColumnModel().getColumn(1).setCellRenderer(new CheckBoxRenderer());
        table1.getColumnModel().getColumn(2).setCellRenderer(new CheckBoxRenderer());
        table1.getColumnModel().getColumn(3).setCellRenderer(new CheckBoxRenderer());

        // Add listener for checkbox modification
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
            }
        });

        //Deletes the user profile to the text file
        Delete_Profile.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String usernameToDelete = getDeleteUser().getText();

                DeleteProfileCommand deleteProfileCommand = new DeleteProfileCommand(userAccountManager, usernameToDelete);
                deleteProfileCommand.execute();
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

    }

    // Update time based on user input
    private void updateTime() {
        String newTimeStr = TimeText.getText();
        currentTime = new Time(newTimeStr);
        time.setText(currentTime.toString());

        startIncrementingTime();

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




    private void updateFile() {
        try {
            File inputFile = new File("database/Users.txt");
            File tempFile = new File("database/tempUsers.txt");

            BufferedReader reader = new BufferedReader(new FileReader(inputFile));
            PrintWriter writer = new PrintWriter(new FileWriter(tempFile));

            String line;
            int row = 0;
            while ((line = reader.readLine()) != null) {
                String[] parts = line.split("\\|");
                boolean anywhere = (boolean) table1.getValueAt(row, 1);
                boolean insideHome = (boolean) table1.getValueAt(row, 2);
                boolean insideRoom = (boolean) table1.getValueAt(row, 3);
                // Keep the other parts intact
                writer.println(parts[0] + "|" + parts[1] + "|" + parts[2] + "|" + parts[3] + "|" +
                        parts[4] + "|" + anywhere + "|" + insideHome + "|" + insideRoom);
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


    // CheckBoxRenderer class for rendering checkboxes in the table
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










}
