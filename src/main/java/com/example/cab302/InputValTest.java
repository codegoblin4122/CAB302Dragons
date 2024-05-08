package com.example.cab302;

import org.junit.jupiter.api.Test;

import java.util.regex.Pattern;

import static com.example.cab302.utils.InputValidation.*;
import static org.junit.jupiter.api.Assertions.*;
import com.example.cab302.utils.InputValidation;


public class InputValTest {


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
