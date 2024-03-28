package presentation.Swing.LoginAndSignUp;


import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class AuthenticationManagerTest {

    @Test
    void authenticateUser_InvalidCredentials_ReturnsFalse() {
        // Arrange
        String invalidEmail = "invalid@example.com";
        String invalidPassword = "invalidPassword";

        // Act
        boolean isAuthenticated = AuthenticationManager.authenticateUser(invalidEmail, invalidPassword);

        // Assert
        assertFalse(isAuthenticated, "Authentication should fail for invalid credentials");
    }

    @Test
    void authenticateUser_NullOrEmptyInputs_ReturnsFalse() {
        // Arrange
        String nullEmail = null;
        String nullPassword = null;
        String emptyEmail = "";
        String emptyPassword = "";

        // Act & Assert
        assertFalse(AuthenticationManager.authenticateUser(nullEmail, "password"), "Authentication should fail for null email");
        assertFalse(AuthenticationManager.authenticateUser("", "password"), "Authentication should fail for empty email");
        assertFalse(AuthenticationManager.authenticateUser("test@example.com", nullPassword), "Authentication should fail for null password");
        assertFalse(AuthenticationManager.authenticateUser("test@example.com", ""), "Authentication should fail for empty password");
    }
}

