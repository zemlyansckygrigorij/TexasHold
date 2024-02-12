package com.example.texashold.exception;

import lombok.Getter;

/**
 * @author Grigoriy Zemlyanskiy
 * @version 1.0
 * class WrongArgException for handling exception
 * */

@Getter
public class WrongArgException extends RuntimeException{
    private String message;
    public WrongArgException(String message){
        this.message =  message;
    }
}
