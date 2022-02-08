package com.mob.casestudy.digitalbanking.exception;

public class InvalidUsernameException extends RuntimeException{
    public InvalidUsernameException() {
        super("Invalid username");
    }
}
