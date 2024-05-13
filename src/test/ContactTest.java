import com.example.cab302.model.Contact;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.assertEquals;

public class ContactTest {
    private Contact contact;
    @BeforeEach
    public void setUp() {
        contact = new Contact("test", "password", "password");
    }
    @Test
    public void testGetId() {
        contact.setId(1);
        assertEquals(1, contact.getId());
    }

    @Test
    public void testGetEmail() {
        assertEquals("test", contact.getEmail());
    }
    @Test
    public void testSetEmail() {
        contact.setEmail("test");
        assertEquals("test", contact.getEmail());
    }

    @Test
    public void testGetPassword() {
        assertEquals("password", contact.getPassword());
    }

    @Test
    public void testSetPassword() {
        contact.setPassword("password");
        assertEquals("password", contact.getPassword());
    }

    @Test
    public void testGetConfirmPassword() {
        assertEquals("password", contact.getPasswordConfirm());
    }

    @Test
    public void testSetConfirmPassword() {
        contact.setPasswordConfirm("password");
        assertEquals("password", contact.getPasswordConfirm());
    }
}
