package com.smart.erp.security.tools;

import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

public class PasswordGenerator {
    public static void main(String[] args) {
        BCryptPasswordEncoder encoder = new BCryptPasswordEncoder();
        String rawPassword = "sysadmin";
        String encodedPassword = encoder.encode(rawPassword);
        System.out.println("BCrypt hash: " + encodedPassword);
    }
}
