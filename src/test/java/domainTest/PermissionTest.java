package domainTest;

import domain.Permission.Permission;
import org.junit.Test;

import static org.junit.Assert.*;

public class PermissionTest {

    @Test
    public void testConstructorAndGetters() {
        Permission permission = new Permission(true, false, true, false, true, false, true, false, false, false);

        assertTrue(permission.isHasWindowPermissionOutside());
        assertFalse(permission.isHasWindowPermissionInsideHome());
        assertTrue(permission.isHasDoorPermissionOutside());
        assertFalse(permission.isHasDoorPermissionInsideHome());
        assertTrue(permission.isHasLightPermissionOutside());
        assertFalse(permission.isHasLightPermissionInsideHome());
        assertTrue(permission.isHasHeaterPermissionOutside());
        assertFalse(permission.isHasHeaterPermissionInsideHome());
        assertFalse(permission.isHasSHPPermissionOutside());
        assertFalse(permission.isHasSHPPermissionInsideHome());
    }

    @Test
    public void testSettersAndGetters() {
        Permission permission = new Permission(false, true, false, true, false, true, false, true, true, true);

        permission.setHasWindowPermissionOutside(true);
        assertTrue(permission.isHasWindowPermissionOutside());

        permission.setHasWindowPermissionInsideHome(false);
        assertFalse(permission.isHasWindowPermissionInsideHome());

        permission.setHasDoorPermissionOutside(true);
        assertTrue(permission.isHasDoorPermissionOutside());

        permission.setHasDoorPermissionInsideHome(false);
        assertFalse(permission.isHasDoorPermissionInsideHome());

        permission.setHasLightPermissionOutside(true);
        assertTrue(permission.isHasLightPermissionOutside());

        permission.setHasLightPermissionInsideHome(false);
        assertFalse(permission.isHasLightPermissionInsideHome());

        permission.setHasHeaterPermissionOutside(true);
        assertTrue(permission.isHasHeaterPermissionOutside());

        permission.setHasHeaterPermissionInsideHome(false);
        assertFalse(permission.isHasHeaterPermissionInsideHome());

        permission.setHasSHPPermissionOutside(true);
        assertTrue(permission.isHasSHPPermissionOutside());

        permission.setHasSHPPermissionInsideHome(false);
        assertFalse(permission.isHasSHPPermissionInsideHome());
    }

    @Test
    public void testToString() {
        Permission permission = new Permission(true, true, true, true, true, true, true, true, true, true);
        String expectedToString = "Permission{" +
                "hasWindowPermissionOutside=true, " +
                "hasWindowPermissionInsideHome=true, " +
                "hasDoorPermissionOutside=true, " +
                "hasDoorPermissionInsideHome=true, " +
                "hasLightPermissionOutside=true, " +
                "hasLightPermissionInsideHome=true, " +
                "hasHeaterPermissionOutside=true, " +
                "hasHeaterPermissionInsideHome=true" +
                "hasSHPPermissionOutside=true, " +
                "hasSHPPermissionInsideHome=true" +
                "}";

        assertEquals(expectedToString, permission.toString());
    }
}
