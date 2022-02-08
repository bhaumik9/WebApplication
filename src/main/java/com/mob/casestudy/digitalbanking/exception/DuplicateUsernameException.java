package com.mob.casestudy.digitalbanking.exception;

public class DuplicateUsernameException extends RuntimeException{
    public DuplicateUsernameException() {
        super("Duplicate Username Found");
    }
}
