package com.example.SmartAgriConnect.exception;

import lombok.Getter;

public class InvalidOperationException extends RuntimeException {

    @Getter
    public ErrorsCode errorsCode;
    public InvalidOperationException(String message){
        super(message);
    }

    public InvalidOperationException(String message,Throwable cause){
        super(message,cause);
    }

    public InvalidOperationException(String message,ErrorsCode errorsCode){
        super(message);
        this.errorsCode=errorsCode;
    }
}
