package presentation.Swing.LoginAndSignUp;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

public class AuthenticationManager {

    private static final String HASH_ALGORITHM = "SHA-256";

    public static boolean authenticateUser(String email, String password) {
        // Validate inputs
        if (email == null || email.isEmpty() || password == null || password.isEmpty()) {
            return false;
        }

        String filePath = "database/Users.txt"; // Configure file path
        try (BufferedReader reader = new BufferedReader(new FileReader(filePath))) {
            String line;
            while ((line = reader.readLine()) != null) {
                String[] userInfo = line.split("\\|");
                String storedEmail = userInfo[1];
                String storedHashedPassword = userInfo[2]; // Assuming password is hashed in the file
                if (storedEmail.equals(email) && verifyPassword(password, storedHashedPassword)) {
                    return true; // Authentication successful
                }
            }
        } catch (IOException e) {
            e.printStackTrace(); // Handle file reading errors
        }
        return false; // No matching email and password found
    }

    private static boolean verifyPassword(String password, String storedHashedPassword) {
        // Hash the provided password using the same algorithm and compare it with the stored hashed password
        try {
            MessageDigest digest = MessageDigest.getInstance(HASH_ALGORITHM);
            byte[] hashedPasswordBytes = digest.digest(password.getBytes(StandardCharsets.UTF_8));
            String hashedPassword = bytesToHex(hashedPasswordBytes);
            return hashedPassword.equals(storedHashedPassword);
        } catch (NoSuchAlgorithmException e) {
            e.printStackTrace(); // Handle hash algorithm not found
            return false;
        }
    }

    public static String authenticateAndGetUsername(String email, String password) {
        String filePath = "database/Users.txt";
        try (BufferedReader reader = new BufferedReader(new FileReader(filePath))) {
            String line;
            while ((line = reader.readLine()) != null) {
                String[] userInfo = line.split("\\|");
                String storedEmail = userInfo[1];
                String storedPassword = userInfo[2];
                String username = userInfo[0]; // Assuming username is the first field
                if (storedEmail.equals(email) && storedPassword.equals(password)) {
                    return username; // Authentication successful, return the username
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return null; // No matching email and password found, return null
    }

    private static String bytesToHex(byte[] bytes) {
        StringBuilder result = new StringBuilder();
        for (byte b : bytes) {
            result.append(String.format("%02x", b));
        }
        return result.toString();
    }
}
