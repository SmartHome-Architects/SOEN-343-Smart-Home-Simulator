package domainTest;



import domain.Permission.Permission;
import domain.editbutton.EditHouseInhabitantsDialog;
import domain.house.House;
import domain.user.LoggedInUser;
import domain.user.Users;
import org.junit.Before;
import org.junit.Test;
import presentation.Swing.command.UserAccountManager;

import javax.swing.*;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

public class EditHouseInhabitantsDialogTest {

    private EditHouseInhabitantsDialog editDialog;
    private UserAccountManager userAccountManager;
    private House houseInstance;
    private Map<String, JLabel> userLabels;
    private LoggedInUser user;

    @Before
    public void setUp() {
        // Create a mock Permission object (if needed)
        Permission permission = new Permission(true, true, true, true, true, true, true, true);

        // Pass the mock Permission object to the Users constructor
        Users testUser = new Users("username", "password", "firstName", "lastName", "email", permission);

        // Instantiate LoggedInUser with the test user object
        userAccountManager = new UserAccountManager("database/Users.txt");
        houseInstance = new House();  // You may need to provide a mock house instance
        userLabels = new HashMap<>();
        user = new LoggedInUser(testUser);
    }



    @Test
    public void testConstructor() {
        List<String> usernames = new ArrayList<>();
        usernames.add("user1");
        usernames.add("user2");
        usernames.add("user3");

        String selectedUsername = "user2";
        editDialog = new EditHouseInhabitantsDialog(null, userAccountManager, usernames, selectedUsername, houseInstance, userLabels, user);

        assertNotNull(editDialog);
        assertEquals("Edit House Inhabitants", editDialog.getTitle());
        assertEquals(300, editDialog.getWidth());
        assertEquals(150, editDialog.getHeight());
        assertEquals(selectedUsername, editDialog.getSelectedUsername());
    }


}
