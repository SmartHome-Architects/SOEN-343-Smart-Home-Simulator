package presentationTest;

import org.junit.Test;
import presentation.Swing.command.UserAccountManager;
import presentation.Swing.command.EditProfileCommand;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import static org.junit.Assert.assertTrue;

public class EditProfileCommandTest {

    @Test
    public void testExecute() {
        // Use a test file path
        String testFilePath = "testUsers.txt";

        // Initialize UserAccountManager with the same test file path
        UserAccountManager accountManager = new UserAccountManager(testFilePath);

        // Add a user for testing
        String oldUsername = "oldUser";
        String newUsername = "newUser";
        String email = "newUser@example.com";
        String password = "newPassword";
        String accessibility = "admin";
        accountManager.addUser(oldUsername, email, password, accessibility);

        // Create an instance of EditProfileCommand with the UserAccountManager instance
        EditProfileCommand command = new EditProfileCommand(accountManager, oldUsername, newUsername, email, password, accessibility);

        // Call the execute method
        command.execute();

        // Check if user has been edited successfully in the file
        try (BufferedReader reader = new BufferedReader(new FileReader(testFilePath))) {
            String line;
            boolean found = false;
            while ((line = reader.readLine()) != null) {
                if (line.startsWith(newUsername + "|")) {
                    found = true;
                    break;
                }
            }
            assertTrue(found); // Assert that the updated username exists in the file
        } catch (IOException e) {
            e.printStackTrace();
        }

    }
}
