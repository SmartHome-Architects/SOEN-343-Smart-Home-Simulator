package domainTest;

import domain.Permission.Permission;
import domain.user.Users;
import org.junit.Test;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

public class UsersTest {

    @Test
    public void testConstructorAndGetters() {
        String username = "testUser";
        String email = "test@example.com";
        String password = "password123";
        String type = "Parent";
        String location = "Living Room";
        Permission permissions = new Permission(true, true, true, true, true, true, true, true, true, true);

        Users user = new Users(username, email, password, type, location, permissions);

        assertNotNull(user);
        assertEquals(username, user.getUsername());
        assertEquals(email, user.getEmail());
        assertEquals(password, user.getPassword());
        assertEquals(type, user.getType());
        assertEquals(location, user.getLocation());
        assertEquals(permissions, user.getPermissions());
    }

    @Test
    public void testSetters() {
        Users user = new Users("testUser", "test@example.com", "password123", "Parent", "Living Room", null);

        user.setUsername("newUsername");
        user.setEmail("newemail@example.com");
        user.setPassword("newpassword123");
        user.setType("Child");
        user.setLocation("Bedroom");
        Permission permissions = new Permission(false, false, false, false, false, false, false, false, false, false);
        user.setPermissions(permissions);

        assertEquals("newUsername", user.getUsername());
        assertEquals("newemail@example.com", user.getEmail());
        assertEquals("newpassword123", user.getPassword());
        assertEquals("Child", user.getType());
        assertEquals("Bedroom", user.getLocation());
        assertEquals(permissions, user.getPermissions());
    }
}
