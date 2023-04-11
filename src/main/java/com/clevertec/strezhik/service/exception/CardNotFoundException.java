package com.clevertec.strezhik.service.exception;

public class CardNotFoundException extends NullPointerException {

    public CardNotFoundException() {
        super("You entered a non-existent card");
    }
}
