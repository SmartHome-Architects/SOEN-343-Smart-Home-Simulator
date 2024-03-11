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
        List<Room> rooms = h.getRooms();
        for (Room room: rooms) {

            System.out.println(room.getRoomName() + " " + room.getTemperature());

            if(room.getRoomName().equals("Kitchen")){
                room.setTemperature(25.0);
                System.out.println(room.getTemperature() + " changed.");
            }
        }

    }
}