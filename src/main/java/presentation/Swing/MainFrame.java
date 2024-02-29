package src.main.java.presentation.Swing;

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
    private JComboBox comboBox2;
    private JComboBox combobox3;
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

    // c
    public MainFrame() {
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

}
