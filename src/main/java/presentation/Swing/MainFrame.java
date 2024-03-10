package src.main.java.presentation.Swing;

import src.main.java.domain.dateTime.Date;
import src.main.java.domain.dateTime.Time;

import javax.swing.*;
import java.awt.event.*;

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
    private JPanel plus;
    private JComboBox comboBox2;
    private JComboBox ManageUser;
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
    private JSlider slider1;
    private JButton buttonLogOut;
    private JPanel SetTimeDate;
    private JTextField TimeText;
    private JTextField DateText;

    private Date currentDate;
    private Time currentTime;

    // c
    public MainFrame() {

        //Sets Date and Time on the DASHBOARD
        currentDate = new Date();
        currentTime = new Time();

        date.setText(currentDate.toString());
        time.setText(currentTime.toString());

        //Updates Time every second
        Timer timer = new Timer(1000, e -> updateDateTime());
        timer.start();

        DateText.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                updateDate();
            }
        });

        // Add ActionListener to timeTextField
        TimeText.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                updateTime();
            }
        });


        JFrame frame = new JFrame("Dashboard");
        frame.setContentPane(WindowContainer);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(1250, 700);
        frame.setLocationRelativeTo(null);
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
    }

}
