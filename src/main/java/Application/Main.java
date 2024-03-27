package Application;
import domain.house.Room;
import domain.house.Zone;
import domain.user.Users;
import domain.user.UsersInitializer;
import presentation.Swing.LoginAndSignUp.AuthenticationManager;
import presentation.Swing.LoginAndSignUp.LogIn;
import presentation.Swing.MainFrame;
import presentation.Swing.SHH.ZoneNameSerializer;

import javax.swing.*;
import java.util.ArrayList;
import java.util.List;

public class Main {




    public static void main(String[] args) {



        //ZoneNameSerializer.loadAndPrintZones();


        SwingUtilities.invokeLater(() -> {

            LogIn loginFrame = new LogIn();

            loginFrame.setVisible(true);
            loginFrame.setLocationRelativeTo(null);

        });

        /*
        List<Users> allUsers = UsersInitializer.getAllUsers();
        for(Users user : allUsers) {
            System.out.println("User: " + user.getUsername() + ", Details: " + user);
        }
        */

    }
}
