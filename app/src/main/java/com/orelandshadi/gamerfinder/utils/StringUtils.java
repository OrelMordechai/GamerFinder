package com.orelandshadi.gamerfinder.utils;

import android.util.Patterns;

import java.util.regex.Pattern;

public class StringUtils {

    private static final Pattern PASSWORD_PATTERN =
            Pattern.compile("^(?=.*[0-9])(?=.*[a-z])(?=.*[A-Z])(?=.*[@#$%^&+=])(?=\\S+$).{0,}$");

    public static Boolean isValidEmail(String email) {
        return Patterns.EMAIL_ADDRESS.matcher(email).matches();
    }

    public static Boolean isValidPassword(String passwordInput) {
        return PASSWORD_PATTERN.matcher(passwordInput).matches();
    }

}

