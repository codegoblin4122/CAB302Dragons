package com.example.cab302.utils;

import java.util.regex.Pattern;

public class InputValidation {
    public static boolean validateEmail(String email) {
        return Pattern.matches("^[\\w-\\.]+@([\\w-]+\\.)+[\\w-]{2,4}$", email);
    }
    public static boolean validatePassword(String pass) { // Uppercase, lowercase & number
        return Pattern.matches("^(?=.*[A-Za-z])(?=.*\\d)[A-Za-z\\d]{8,}$", pass);
    }
    public static boolean validateConfPassword(String pass, String confPass) {
        return confPass.equals(pass);
    }
}
