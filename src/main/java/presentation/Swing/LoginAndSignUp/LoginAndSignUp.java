package src.main.java.presentation.Swing.LoginAndSignUp;

public class LoginAndSignUp {
    private LogIn login;
    private SignUp signup;

    public LoginAndSignUp() {
        login = new LogIn();
        signup = new SignUp();

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
    }

    public void showLogin() {
        login.pack();
        login.setLocationRelativeTo(null);
        login.setVisible(true);
    }

    public static void main(String args[]) {
        LoginAndSignUp loginAndSignUp = new LoginAndSignUp();
        loginAndSignUp.showLogin();
    }
}
