package presentation.Swing.LoginAndSignUp;

public class LoginAndSignUp {
    public static void main(String args[]) {

        LogIn login = new LogIn();
        SignUp signup = new SignUp();

        // Action listener for "Sign Up" button in the login page
        login.setSignUpActionListener(e -> {
            login.setVisible(false);
            signup.setVisible(true);
            signup.setLocationRelativeTo(null); // Set location after making it visible
        });

        // Action listener for "Back to Login" button in the signup page
        signup.setBackToLoginActionListener(e -> {
            signup.setVisible(false);
            login.setVisible(true);
            login.setLocationRelativeTo(null); // Set location after making it visible
        });

        login.pack();
        login.setLocationRelativeTo(null);
        login.setVisible(true);

        signup.pack();
    }
}
