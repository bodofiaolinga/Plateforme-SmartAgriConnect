package com.example.SmartAgriConnect.exception;

import lombok.Getter;

import java.util.List;

public class InvalidNotFoundException extends RuntimeException {

    @Getter
    public ErrorsCode errorsCode;

    @Getter
    public List<String> errors;
    public InvalidNotFoundException(String message) {
        super(message);
    }

    public InvalidNotFoundException(String message, Throwable cause ){
        super(message, cause);
    }

    public InvalidNotFoundException(String message, Throwable cause,ErrorsCode errorsCode){
        super(message);
        this.errorsCode=errorsCode;
    }

    public InvalidNotFoundException( String message,ErrorsCode errorsCode,List<String>errors){

        this.errorsCode=errorsCode;
        this.errors=errors;
    }

}
