package com.core.velocity.spirent.birdapi.config.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(value = HttpStatus.NOT_FOUND)
public class ResourceNotFoundException extends RuntimeException {
    private static final long serialVersionUID = -6958128182491079251L;

    public ResourceNotFoundException() {
        super();
    }

    private String value;
    private String message;

    public ResourceNotFoundException(String value, String message) {
        this.value = value;
        this.message = message;
    }

    public String getValue() {
        return value;
    }

    public String getMessage() {
        return this.message;
    }

}