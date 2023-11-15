package com.example.orderapp.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

import java.io.Serial;

@ResponseStatus(value = HttpStatus.BAD_REQUEST)
public class NotEnoughGoodsToBuy extends RuntimeException {
    @Serial
    private static final long serialVersionUID = 1L;

    public NotEnoughGoodsToBuy(String message) {
        super(message);
    }
}