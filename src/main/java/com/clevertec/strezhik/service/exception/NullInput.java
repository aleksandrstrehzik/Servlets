package com.clevertec.strezhik.service.exception;

public class NullInput extends IllegalArgumentException {

    public NullInput() {
        super("You enter nothing");
    }
}
