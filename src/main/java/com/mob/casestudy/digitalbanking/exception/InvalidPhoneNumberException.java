package com.mob.casestudy.digitalbanking.exception;

public class InvalidPhoneNumberException extends RuntimeException{
    public InvalidPhoneNumberException(){
        super("Invalid Phone Number");
    }
}
