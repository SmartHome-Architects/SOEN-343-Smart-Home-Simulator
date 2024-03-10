package src.main.java.presentation.Swing;

import src.main.java.domain.dateTime.Date;
import src.main.java.domain.dateTime.Time;

import javax.swing.*;

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
    private JComboBox combobox3;
    private JTextArea textArea1;
    private JButton buttonOn;
    private JButton buttonOff;
    private JLabel userTag;
    private JLabel locationTag;
    private JLabel temperature;
    private JLabel date;
    private JLabel time;
    private JButton buttonLogOut;

    public MainFrame() {

        //Sets Date and Time on the DASHBOARD
        Date currentDate = new Date();
        Time currentTime = new Time();

        Timer timer = new Timer(1000, e -> updateDateTime());
        timer.start();

        date.setText(currentDate.toString());
        time.setText(currentTime.toString());

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

    private void updateDateTime() {
        Date currentDate = new Date();
        Time currentTime = new Time();

        date.setText(currentDate.toString());
        time.setText(currentTime.toString());
    }

}
