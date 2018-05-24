package com.kgl1688.security;

import org.springframework.security.crypto.password.PasswordEncoder;

public class MyPasswordEncoder implements PasswordEncoder {
    @Override
    public String encode(CharSequence rawPassword) {

        System.out.println("Raw Password:" + rawPassword.toString());
        return rawPassword.toString();
    }

    @Override
    public boolean matches(CharSequence rawPassword, String encodedPassword) {
        System.out.println("raw password: " + rawPassword.toString() + ", encoded password:" + encodedPassword);
        return (rawPassword.toString().equals(encodedPassword));
    }
}
