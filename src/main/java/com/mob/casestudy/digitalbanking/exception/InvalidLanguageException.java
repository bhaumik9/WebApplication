package com.mob.casestudy.digitalbanking.exception;

public class InvalidLanguageException extends RuntimeException{
    public InvalidLanguageException() {
        super("Invalid Preferred Language");
    }
}
