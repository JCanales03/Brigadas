package com.conaf.microservicio.brigadas.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.NOT_FOUND) 
public class BrigadaNotFoundException extends RuntimeException {
    
    public BrigadaNotFoundException(String message) {
        super(message);
    }
}
