package com.cg.iba.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.NOT_FOUND)
public class DetailsNotFoundException extends RuntimeException {

    public DetailsNotFoundException() {
        super();
    }

    public DetailsNotFoundException(String message) {
        super(message);
    }

    public DetailsNotFoundException(String message, Throwable clause) {
        super(message, clause);
    }

    public DetailsNotFoundException(Throwable clause) {
        super(clause);
    }

}
