package com.mob.casestudy.digitalbanking.exception;

public class CustomerSecurityQuestionsNotFound extends RuntimeException{
    public CustomerSecurityQuestionsNotFound() {
        super("List of Customer Security Questions is empty");
    }
}
