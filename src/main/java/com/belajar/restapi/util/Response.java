package com.belajar.restapi.util;

import lombok.Data;


/**
 * This is a Javadoc comment
 * @param <T> the parameter of the class
 */

@Data
public class Response<T> {
    private String message;
    private T data;
}
