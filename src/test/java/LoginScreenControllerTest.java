import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.AfterEach;
import static org.junit.jupiter.api.Assertions.*;

class LoginScreenControllerTest {

    LoginScreenController controller;

    @BeforeEach
    void setUp() {
        controller = new LoginScreenController();
    }

    @Test
    void testValidUsernameAndPassword() {
        assertTrue(controller.isValidInput("bigName0", "superSafePassword"));
    }

    @Test
    void testEmptyUsernameIsInvalid() {
        assertFalse(controller.isValidInput("", "superSafePassword"));
    }

    @Test
    void testNullUsernameIsInvalid() {
        assertFalse(controller.isValidInput(null, "SuperSafePassword"));
    }

    @Test
    void testPasswordTooShortIsInvalid() {
        assertFalse(controller.isValidInput("GeneralKenobi", "123"));
    }

    @Test
    void testNullPasswordIsInvalid() {
        assertFalse(controller.isValidInput("GeneralKenobi", null));
    }

    @Test
    void testSpaceOnlyUsernameIsInvalid() {
        assertFalse(controller.isValidInput("   ", "passwordPassword"));
    }
}