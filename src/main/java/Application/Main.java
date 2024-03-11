package Application;

import domain.house.House;
import domain.house.Room;
import presentation.Swing.MainFrame;

import javax.swing.SwingUtilities;
import java.util.List;

public class Main {
    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            MainFrame mainFrame = new MainFrame();
            mainFrame.showMainFrame();
        });

        //remove when done
        House h = new House();

    }
}