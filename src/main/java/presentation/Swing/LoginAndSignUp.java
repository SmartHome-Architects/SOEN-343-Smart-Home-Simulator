package src.main.java.presentation;

import src.main.java.presentation.Swing.LogIn;
import src.main.java.presentation.Swing.SignUp;

import javax.swing.*;

public class LoginAndSignUp {
    public static void main(String args[]) {

        LogIn login = new LogIn();
        SignUp signup = new SignUp();

        // Action listener for "Sign Up" button in the login page
        login.setSignUpActionListener(e -> {
            login.setVisible(false);
            signup.setVisible(true);
        });

        // Action listener for "Back to Login" button in the signup page
        signup.setBackToLoginActionListener(e -> {
            signup.setVisible(false);
            login.setVisible(true);
        });

        login.setVisible(true);
        login.pack();
        login.setLocationRelativeTo(null);

        signup.pack();
        signup.setLocationRelativeTo(null);
    }
}
