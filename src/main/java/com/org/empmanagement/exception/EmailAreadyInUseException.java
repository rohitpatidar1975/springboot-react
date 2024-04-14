package com.org.empmanagement.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.FOUND)
public class EmailAreadyInUseException extends RuntimeException{
    public EmailAreadyInUseException(String message){
        super(message);
    }
}
