package com.api.cadastro_dispositivos.config;

import com.api.cadastro_dispositivos.exceptions.NotFoundExecption;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice
public class GlobalException {

    @ExceptionHandler(value = NotFoundExecption.class)
    public ResponseEntity<String> handleNotFoundException(NotFoundExecption execption) {
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(execption.getMessage());
    }

    @ExceptionHandler(value = Exception.class)
    public ResponseEntity<String> handleNotFoundException(Exception execption) {
        return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(execption.getMessage());
    }
}
