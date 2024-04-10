package presentationTest;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.*;

public class SignUpTest {

    private SignUp signUp;

    @Before
    public void setUp() {
        signUp = new SignUp();
    }

    @After
    public void tearDown() {
        signUp = null;
    }

    @Test
    public void testIsValidEmail() {
        assertTrue(signUp.isValidEmail("user123@gmail.com"));
        assertFalse(signUp.isValidEmail("invalidemail"));
    }

}
