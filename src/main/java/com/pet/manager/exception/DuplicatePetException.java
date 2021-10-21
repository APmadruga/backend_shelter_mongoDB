package com.pet.manager.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(value = HttpStatus.CONFLICT,reason = "Name already Exists, it needs to be Unique")
public class DuplicatePetException extends RuntimeException {
    public DuplicatePetException() {
        super("Pet with that name already exists.");
    }
    public DuplicatePetException(String message) {
        super(message);
    }
}