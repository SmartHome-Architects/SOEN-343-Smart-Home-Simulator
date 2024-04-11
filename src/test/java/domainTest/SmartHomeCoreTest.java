//package domainTest;
//
//import domain.house.House;
//import domain.sensors.Window;
//import domain.smartHomeSimulator.modules.SmartHomeCore;
//import org.junit.jupiter.api.Test;
//
//import java.util.ArrayList;
//import java.util.List;
//
//import static org.junit.jupiter.api.Assertions.assertTrue;
//
//public class SmartHomeCoreTest {
//
//    @Test
//    public void testWindowAction_OpenWindow() {
//        // Create test data
//        Window window = new Window("Living Room", "Window 1", 1, null); // Assuming Coordinate is nullable
//        window.setOpen(false); // Initially closed
//        List<Window> windows = new ArrayList<>();
//        windows.add(window);
//        House house = new House(); // Create an instance of House
//        windows.add(window);
//
//        // Create SmartHomeCore instance
//        SmartHomeCore smartHomeCore = new SmartHomeCore();
//
//        // Perform window action
//        smartHomeCore.windowAction(house, "Living Room Window 1", true); // Open the window
//
//        // Check if window is open
//        assertTrue(window.isOpen());
//    }
//}
