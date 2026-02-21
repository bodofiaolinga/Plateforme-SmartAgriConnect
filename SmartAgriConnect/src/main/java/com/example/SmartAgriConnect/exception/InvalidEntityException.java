package com.example.SmartAgriConnect.exception;

import lombok.Getter;

public class InvalidEntityException extends RuntimeException{

    @Getter
    public ErrorsCode errorsCode;
    public InvalidEntityException(String message){
        super(message);
    }

    public InvalidEntityException(String message,Throwable cause){
        super(message,cause);
    }

    public InvalidEntityException(String message,ErrorsCode errorsCode){
        super(message);
        this.errorsCode=errorsCode;
    }
}
