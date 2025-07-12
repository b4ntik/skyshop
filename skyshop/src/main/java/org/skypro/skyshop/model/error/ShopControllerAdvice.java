package org.skypro.skyshop.model.error;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.method.annotation.MethodArgumentTypeMismatchException;

@ControllerAdvice
public class ShopControllerAdvice {

    @ExceptionHandler (NoSuchProductException.class)
    public ResponseEntity<String> noSuchProductException(NoSuchProductException exc){
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(exc.getMessage());
    }
    @ExceptionHandler (Exception.class)
    public ResponseEntity<String> anotherExceptions(Exception exc){
        return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Внутренняя ошибка сервера");
    }
}
