
package domainTest;

import domain.Permission.Permission;
import domain.user.UserPermissionInitializer;
import org.junit.Test;

import static org.junit.Assert.*;

public class UserPermissionInitializerTest {

    @Test
    public void testInitializePermission() {
        String doorPermissionFile = "door_permissions.txt";
        String lightPermissionFile = "light_permissions.txt";
        String windowPermissionFile = "window_permissions.txt";
        String shhPermissionFile = "shh_permissions.txt";
        String shpPermissionFile = "shp_permissions.txt";
        String userType = "admin"; // or any user type you want to test

        Permission permission = UserPermissionInitializer.initializePermission(doorPermissionFile, lightPermissionFile, windowPermissionFile, shhPermissionFile,shpPermissionFile, userType);

        assertNotNull(permission);
        // Add more assertions as needed to test the initialized Permission object
    }
}
