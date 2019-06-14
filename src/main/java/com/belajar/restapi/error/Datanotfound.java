package com.belajar.restapi.error;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

/**
 *
 * This is a Javadoc comment
 */
@ResponseStatus(HttpStatus.NOT_FOUND)
public class Datanotfound extends RuntimeException{
    /**
     * This is a Javadoc comment
     * @param message the parameter of the class
     */
    public Datanotfound(String message){
        super(message);
    }
}