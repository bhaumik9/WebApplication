package com.mob.casestudy.digitalbanking.exception;

public class InvalidEmailException extends RuntimeException{
    public InvalidEmailException() {
        super("Invalid Email Address");
    }
}
