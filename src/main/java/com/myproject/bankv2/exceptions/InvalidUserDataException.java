package com.myproject.bankv2.exceptions;

public class InvalidUserDataException extends RuntimeException{
    public InvalidUserDataException() {
        super("Username already exist in database");
    }
}
