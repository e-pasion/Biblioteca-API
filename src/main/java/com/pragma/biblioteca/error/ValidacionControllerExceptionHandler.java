package com.pragma.biblioteca.error;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;
@RestControllerAdvice// manejar las excepciones
public class ValidacionControllerExceptionHandler extends ResponseEntityExceptionHandler {
    @ExceptionHandler(value = {ValidacionException.class})
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public ResponseEntity<ErrorMensaje> handleValidacionException(RuntimeException exception){
        ErrorMensaje errorMensaje=new ErrorMensaje(HttpStatus.BAD_REQUEST, exception.getMessage());
        return ResponseEntity.badRequest().body(errorMensaje);
    }


}
