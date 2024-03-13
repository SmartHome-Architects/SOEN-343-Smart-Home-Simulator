package presentation.Swing.LoginAndSignUp;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;

public class AuthenticationManager {

    public static boolean authenticateUser(String email, String password) {
        String filePath = "database/Users.txt"; // Update with the correct file path
        try (BufferedReader reader = new BufferedReader(new FileReader(filePath))) {
            String line;
            while ((line = reader.readLine()) != null) {
                String[] userInfo = line.split("\\|");
                String storedEmail = userInfo[1];
                String storedPassword = userInfo[2];
                if (storedEmail.equals(email) && storedPassword.equals(password)) {
                    return true; // Authentication successful
                }
            }
        } catch (IOException e) {
            e.printStackTrace(); // Handle file reading errors
        }
        return false; // No matching email and password found
    }
}
