package domainTest;

import domain.sensors.Door;
import domain.sensors.Window;
import domain.smartHomeSimulator.modules.SmartHomeSecurity;
import org.junit.jupiter.api.Test;
import presentation.Swing.command.UserAccountManager;

import javax.swing.*;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static org.junit.jupiter.api.Assertions.*;

public class SmartHomeSecurityTest {

    @Test
    public void testToggleAwayMode_AllUsersOutside() {
        // Create a UserAccountManager instance with a test user
        UserAccountManager userAccountManager = new UserAccountManager("testUser");

        // Create a JTextArea instance
        JTextArea textArea = new JTextArea();

        // Create a SmartHomeSecurity instance
        SmartHomeSecurity smartHomeSecurity = new SmartHomeSecurity(userAccountManager, "testUser", textArea);

        // Create mock data for windows and doors
        List<Window> windows = new ArrayList<>();
        Map<Window, JLabel> windowLabels = new HashMap<>();
        List<Door> doors = new ArrayList<>();
        Map<Door, JLabel> doorLabels = new HashMap<>();
        // Populate windows and doors lists with mock data

        // Call the methods to set windows and doors
        smartHomeSecurity.setWindowsAndLabels(windows, windowLabels);
        smartHomeSecurity.setDoorsAndLabels(doors, doorLabels);

        // Initially, away mode should not be active
        assertFalse(smartHomeSecurity.isAwayModeActive());

        // Toggle away mode when all users are outside
        smartHomeSecurity.toggleAwayMode();
        assertTrue(smartHomeSecurity.isAwayModeActive());

        // Toggle away mode again to deactivate it
        smartHomeSecurity.toggleAwayMode();
        assertFalse(smartHomeSecurity.isAwayModeActive());
    }

    // You can write more tests for other methods as needed
}
