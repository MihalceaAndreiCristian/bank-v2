package com.myproject.bankv2.exceptions;

public class UserNotFoundException extends RuntimeException{

    public UserNotFoundException() {
        super("User not found");
    }
}
