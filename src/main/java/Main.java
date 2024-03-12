package src.main.java;

import src.main.java.presentation.Swing.LoginAndSignUp.LoginAndSignUp;
import src.main.java.presentation.Swing.MainFrame;

import javax.swing.SwingUtilities;

public class Main {
    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            LoginAndSignUp loginAndSignUp = new LoginAndSignUp();
            loginAndSignUp.showLogin(); // Show the login page first

            // Assuming there's a method to check if the user is logged in
            if (isUserLoggedIn()) {
                // Proceed to show the main frame if the user is logged in
                MainFrame mainFrame = new MainFrame();
                mainFrame.showMainFrame();
            } else {
                // Handle the case where the user is not logged in
                // This might involve showing an error message or taking appropriate action
            }
        });
    }

    private static boolean isUserLoggedIn() {
        // Implement your logic to check if the user is logged in
        // For example, you might check if the user has valid credentials stored in a file or database
        return false; // Replace this with your actual logic
    }
}
