package com.chatico.userservice.security;

import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

public class BCryptEncoderExample {
    public static void main(String[] args) {
        // Create a BCryptPasswordEncoder instance with desired strength (e.g., 12)
        BCryptPasswordEncoder bCryptPasswordEncoder = new BCryptPasswordEncoder();

        // String to be encoded
        String originalPassword = "12345";

        // Encode the string
        String encodedPassword = bCryptPasswordEncoder.encode(originalPassword);

        // Print the encoded password
        System.out.println("Original Password: " + originalPassword);
        System.out.println("Encoded Password: " + encodedPassword);
    }
}
