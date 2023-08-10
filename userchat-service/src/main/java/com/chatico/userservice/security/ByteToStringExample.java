package com.chatico.userservice.security;

public class ByteToStringExample {
    public static void main(String[] args) {
        byte[] byteArray = {117, 115, 101, 114, 110, 97, 109, 101}; // Example byte array representing "Hello"

        String result = new String(byteArray);
        System.out.println("result  "  + result); // Output: Hello
    }
}
