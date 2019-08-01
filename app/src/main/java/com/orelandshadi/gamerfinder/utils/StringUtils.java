package com.orelandshadi.gamerfinder.utils;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class StringUtils {

    public static Boolean isValidEmail(String email){
        // The Validation for Email
        String validemail = "[a-zA-Z0-9\\+\\.\\_\\@\\+]{1,256}"+
                "\\@"+
                "[a-zA-Z0-9][a-zA-Z0-9\\-]{0,64}"+
                "("+
                "\\."+
                "[a-zA-Z0-9][a-zA-Z0-9\\-]{0,25}"+
                ")+";
        Matcher matcher = Pattern.compile(validemail).matcher(email);
        return matcher.matches();
    }
}
