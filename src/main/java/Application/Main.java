package Application;
import presentation.Swing.LoginAndSignUp.LogIn;

import javax.swing.*;

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
