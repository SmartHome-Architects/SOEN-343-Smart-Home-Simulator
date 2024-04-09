package presentationTest;

import org.junit.Before;
import org.junit.Test;
import presentation.Swing.command.UserAccountManager;

import static org.junit.Assert.*;
import java.io.*;

public class UserAccountManagerTest {

    private UserAccountManager userAccountManager;

    @Before
    public void setUp() {
        String usersFilePath = "f.txt"; // Provide a test file path if necessary
        userAccountManager = new UserAccountManager(usersFilePath);
    }

    @Test
    public void testAddUser() {
        String username = "newUser";
        String email = "newUser@example.com";
        String password = "newPassword";
        String accessibility = "admin";

        userAccountManager.addUser(username, email, password, accessibility);

        // Print the current directory to check where the files are being created
        System.out.println("Current Directory: " + System.getProperty("user.dir"));

        // Check if the user was added successfully by verifying if the user file exists
        File userFile = new File("database/f.txt");
        System.out.println("Absolute Path of User File: " + userFile.getAbsolutePath()); // Add this line
        assertTrue(userFile.exists());

        // Check if the user details were correctly written to the file
        try (BufferedReader reader = new BufferedReader(new FileReader(userFile))) {
            String line = reader.readLine();
            assertEquals("newUser|newUser@example.com|newPassword|admin", line);
        } catch (IOException e) {
            fail("Exception thrown while reading user file: " + e.getMessage());
        }
    }


}


