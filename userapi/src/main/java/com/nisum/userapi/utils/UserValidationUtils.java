package com.nisum.userapi.utils;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import java.util.regex.Pattern;

@Component
public class UserValidationUtils {

    @Value("${validation.password.regex}")
    private String passwordRegex;

    @Value("${email.validation.regex}")
    private String emailRegex;

    public boolean isValidPasswordFormat(String password){
        Pattern pattern = Pattern.compile(passwordRegex);
        return pattern.matcher(password).matches();
    }

    public boolean isValidEmailFormat(String email){
        Pattern pattern = Pattern.compile(emailRegex);
        return pattern.matcher(email).matches();
    }


}
