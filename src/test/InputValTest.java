import com.example.cab302.model.Contact;

import org.junit.jupiter.api.Test;

import java.util.regex.Pattern;

import static com.example.cab302.utils.InputValidation.*;
import static org.junit.jupiter.api.Assertions.*;
import com.example.cab302.utils.InputValidation;
/**
 * Tests the email validation logic provided by the InputValidation class.
 * This class contains unit tests to verify both valid and invalid email scenarios.
 */
public class InputValTest {
    /**
     * Tests a variety of valid email formats to ensure they are correctly recognized as valid.
     * These emails include examples with different domain formats, use of special characters, and subdomains.
     */
    @Test
    void testValidEmails() {
        assertTrue(validateEmail("example@example.com"));
        assertTrue(validateEmail("user.name@domain.co.in"));
        assertTrue(validateEmail("email@example.com"));
        assertTrue(validateEmail("firstname.lastname@example.com"));
        assertTrue(validateEmail("email@subdomain.example.com"));
        assertTrue(validateEmail("firstname+lastname@example.com"));
        assertTrue(validateEmail("email@123.123.123.123"));
        assertTrue(validateEmail("email@[123.123.123.123]"));
        assertTrue(validateEmail("\"email\"@example.com"));
        assertTrue(validateEmail("1234567890@example.com"));
        assertTrue(validateEmail("email@example-one.com"));
        assertTrue(validateEmail("_______@example.com"));
        assertTrue(validateEmail("email@example.name"));
        assertTrue(validateEmail("email@example.museum"));
        assertTrue(validateEmail("email@example.co.jp"));
        assertTrue(validateEmail("firstname-lastname@example.com"));
    }
    /**
     * Tests various invalid email formats to ensure they are correctly recognized as invalid.
     * These emails include common mistakes and unusual constructs not permitted in standard email formats.
     */
    @Test
    void testInvalidEmails() {
        assertFalse(validateEmail("plainaddress"));
        assertFalse(validateEmail("#@%^%#$@#$@#.com"));
        assertFalse(validateEmail("@example.com"));
        assertFalse(validateEmail("Joe Smith <email@example.com>"));
        assertFalse(validateEmail("email.example.com"));
        assertFalse(validateEmail("email@example@example.com"));
        assertFalse(validateEmail(".email@example.com"));
        assertFalse(validateEmail("email.@example.com"));
        assertFalse(validateEmail("email..email@example.com"));
        assertFalse(validateEmail("あいうえお@example.com"));
        assertFalse(validateEmail("email@example.com (Joe Smith)"));
        assertFalse(validateEmail("email@example"));
        assertFalse(validateEmail("email@-example.com"));
        assertFalse(validateEmail("email@example.web"));
        assertFalse(validateEmail("email@111.222.333.44444"));
        assertFalse(validateEmail("email@example..com"));
        assertFalse(validateEmail("Abc..123@example.com"));
    }
}
