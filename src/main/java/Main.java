package src.main.java;

import src.main.java.presentation.Swing.LoginAndSignUp.AuthenticationManager;
import src.main.java.presentation.Swing.LoginAndSignUp.LogIn;
import src.main.java.presentation.Swing.MainFrame;

import javax.swing.*;

public class Main {
    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {

            LogIn loginFrame = new LogIn();

            // Action listener for "Login" button
            loginFrame.setLoginActionListener(e -> {
                String email = loginFrame.getEmailText();
                String password = loginFrame.getPasswordText();

                boolean isAuthenticated = AuthenticationManager.authenticateUser(email, password);

                if (isAuthenticated) {
                    // Authentication successful, open dashboard
                    MainFrame mainFrame = new MainFrame();
                    mainFrame.showMainFrame();
                    loginFrame.dispose(); // Close the login frame
                } else {
                    // Authentication failed, show error message
                    JOptionPane.showMessageDialog(loginFrame, "Invalid email or password. Please try again.");
                }
            });

            loginFrame.setVisible(true);
            loginFrame.setLocationRelativeTo(null);

        });







    }
}