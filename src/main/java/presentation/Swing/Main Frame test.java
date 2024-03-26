package presentation.Swing;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import domain.dateTime.Date;
import domain.dateTime.Time;
import domain.editbutton.EditHouseInhabitantsDialog;
import domain.house.House;

import domain.smartHomeSimulator.modules.SmartHomeHeating;
import domain.user.LoggedInUser;

import presentation.Swing.SHC.SHCDisplay;
import presentation.Swing.command.AddProfileCommand;
import presentation.Swing.command.DeleteProfileCommand;
import presentation.Swing.command.EditProfileCommand;
import presentation.Swing.command.ProfileManager;
import presentation.Swing.command.UserAccountManager;
import presentation.Swing.managePermission.PermissionsPopup;

import javax.swing.*;

import javax.swing.table.TableCellRenderer;
import java.awt.*;
import java.awt.event.*;
import java.io.*;
import java.util.List;
import java.net.HttpURLConnection;
import java.net.URL;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

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


    private Date currentDate;
    private Time currentTime;
    private Thread timeIncrementer;
    private ProfileManager profileManager;


    // Lights for each room (true = on, false = off)
    private boolean bathroomLight = false;
    private boolean bedroom1Light = false;
    private boolean bedroom2Light = false;
    private boolean kitchenLight = false;
    private boolean livingroomLight = false;
    private boolean garageLight = false;
    private boolean hallwayLight = false;
    private boolean frontLight = false; //front yard light
    private boolean backLight = false; //backyard light

    private ImageIcon lightOn;
    private ImageIcon lightOff;
    private ImageIcon opened;
    private ImageIcon closed;

    // Doors (true = open, false = closed)
    private boolean frontDoor = false;
    private boolean backDoor = false;
    private boolean bedroom1Door = false;
    private boolean bathroomDoor = false;
    private boolean garageInsideDoor = false;
    private boolean garageOutsideDoor = false;

    private boolean kitchenWindow1Closed = true;
    private boolean kitchenWindow2Closed = true;

    LoggedInUser user;

    public MainFrame(LoggedInUser user) {
        this.user = user;
        House h = new House();
        //----------------------PermissionPopup----------------------------------------------------------
        permissionsButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                PermissionsPopup.show((JFrame) SwingUtilities.getWindowAncestor(permissionsButton));

                LogEntry.setTextArea(textArea1);
                LogEntry.Profilelog("SHS Module", "Manage Permissions", "Edit User's Permissions");
            }
        });
    }
}
    


