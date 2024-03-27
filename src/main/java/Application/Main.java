package Application;
import presentation.Swing.LoginAndSignUp.LogIn;

import javax.swing.*;

public class Main {
    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {

            LogIn loginFrame = new LogIn();

            loginFrame.setVisible(true);
            loginFrame.setLocationRelativeTo(null);

        });
    }
}
