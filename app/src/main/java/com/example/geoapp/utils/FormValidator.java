package com.example.geoapp.utils;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class FormValidator {
    private static final String emailRegularExpression    =
            "^\\w+([\\.-]?\\w+)*@\\w+([\\.-]?\\w+)*\\.\\w{2,4}$";

    private static final String userNameRegularExpression = "^\\w{6,20}$";
    private static final String passwordRegularExpression = "^\\w{8,20}$";

    private static final Pattern emailPattern = Pattern.compile( emailRegularExpression );
    private static final Pattern userNamePattern = Pattern.compile( userNameRegularExpression );
    private static final Pattern passwordPattern = Pattern.compile( passwordRegularExpression );

    public FormValidator(){}

    public boolean isValidEmail(final String emailAddress){
        Matcher matcher = emailPattern.matcher(emailAddress);
        return matcher.matches();
    }
    public boolean isValidUserName(final String userName){
        Matcher matcher = userNamePattern.matcher(userName);
        return matcher.matches();
    }
    public boolean isValidPassword(final String password){
        Matcher matcher = passwordPattern.matcher(password);
        return matcher.matches();
    }
}
