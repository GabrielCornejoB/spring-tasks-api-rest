package co.com.bancolombia.config;

import co.com.bancolombia.model.exceptions.AlreadyExistsException;
import co.com.bancolombia.model.exceptions.CustomError;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import java.util.Arrays;
import java.util.Date;

@ControllerAdvice
public class ExceptionsConfig {

    @ExceptionHandler(AlreadyExistsException.class)
    public ResponseEntity<CustomError> handleAlreadyExistsException(AlreadyExistsException exception) {
        System.out.println(exception.getMessage());
        return new ResponseEntity<>(
                new CustomError(HttpStatus.BAD_REQUEST.value(), exception.getMessage(), new Date()),
                HttpStatus.BAD_REQUEST
        );
    }

    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<CustomError> handleMethodArgumentNotValidError(MethodArgumentNotValidException exception) {
        String message = "Un argumento es invalido";

        if (exception.getDetailMessageArguments() != null) {
            message = "Error en el campo " +
                    Arrays.stream(exception.getDetailMessageArguments()).toList().get(1);
        }

        return new ResponseEntity<>(
                new CustomError(HttpStatus.BAD_REQUEST.value(), message, new Date()),
                HttpStatus.BAD_REQUEST
        );
    }

}
