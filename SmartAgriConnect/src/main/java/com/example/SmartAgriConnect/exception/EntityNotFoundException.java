package com.example.SmartAgriConnect.exception;

import lombok.Getter;

public class EntityNotFoundException extends RuntimeException {

    @Getter
   public ErrorsCode errorsCode;

    public EntityNotFoundException(String message) {
       super(message);
    }

    public EntityNotFoundException(String message,Throwable cause){
        super(message, cause);
    }

    public EntityNotFoundException(String message,ErrorsCode errorsCode ){
        super(message);
        this.errorsCode=errorsCode;
    }

}
