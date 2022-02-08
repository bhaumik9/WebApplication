package com.mob.casestudy.digitalbanking.exception.customer_update_exceptions;

public class MandatoryFieldMissing extends RuntimeException{
    public MandatoryFieldMissing(){
        super("Mandatory Field Missing");
    }
}
