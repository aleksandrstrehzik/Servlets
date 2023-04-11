package com.clevertec.strezhik.service.exception;

public class IncorrectInput extends IllegalArgumentException{
    public IncorrectInput() {
        super("Your order contains an error");
    }
}
