package com.mob.casestudy.digitalbanking.exception;

public class CustomerFieldMissing extends RuntimeException {
    public CustomerFieldMissing(String field) {
        super("Customer " + field + "Field Missing");
    }
    public CustomerFieldMissing() {
        super("Customer Field Missing");
    }
}
