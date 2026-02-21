package com.example.SmartAgriConnect.handler;
import com.example.SmartAgriConnect.exception.EntityNotFoundException;
import com.example.SmartAgriConnect.exception.InvalidNotFoundException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;
@RestControllerAdvice
public class Exception extends ResponseEntityExceptionHandler {
    @ExceptionHandler(EntityNotFoundException.class)
    public ResponseEntity<ErrorDto> handlerException(EntityNotFoundException exception, WebRequest webRequest){
      final HttpStatus notfound=HttpStatus.NOT_FOUND;

      final ErrorDto errorDto=  ErrorDto.builder()
            .httpCode(notfound.value())
            .code(exception.getErrorsCode())
            .message(exception.getMessage())
            .build();

     return new ResponseEntity<>(errorDto,notfound);
    }


  @ExceptionHandler(InvalidNotFoundException.class)
    public ResponseEntity<ErrorDto> handlerException(InvalidNotFoundException e,WebRequest request){

        final HttpStatus bad_Request= HttpStatus.BAD_REQUEST;

        final ErrorDto errorDto= ErrorDto.builder()
                .httpCode(bad_Request.value())
                .code(e.getErrorsCode())
                .message(e.getMessage())
                .errors(e.getErrors())
                .build();

        return new ResponseEntity<>(errorDto,bad_Request);

    }
}
