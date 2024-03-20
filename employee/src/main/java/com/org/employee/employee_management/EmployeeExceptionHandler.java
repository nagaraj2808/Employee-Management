package com.org.employee.employee_management;

import com.org.employee.employee_management.exceptions.EmployeeAlreadyExistsException;
import com.org.employee.employee_management.exceptions.EmployeeNotFoundException;
import com.org.employee.employee_management.utils.ErrorResponse;


import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.security.SignatureException;
import java.util.ArrayList;
import java.util.List;

@RestControllerAdvice
public class EmployeeExceptionHandler {
    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<ErrorResponse> handleInvalidArgumentException(MethodArgumentNotValidException exception){
        List<String> errors = new ArrayList<>();
        exception.getBindingResult().getFieldErrors().forEach(fieldError -> {
            errors.add(fieldError.getField() + " : " + fieldError.getDefaultMessage());
        });

        return ResponseEntity.status(400).body(new ErrorResponse("FAILED",HttpStatus.BAD_REQUEST,errors));
    }


    @ExceptionHandler(EmployeeAlreadyExistsException.class)
    public ResponseEntity<ErrorResponse> handleEmployeeAlreadyExistsException(EmployeeAlreadyExistsException exception){
        List<String> errors = new ArrayList<>();
        errors.add(exception.getMessage());
        return  ResponseEntity.status(400).body(new ErrorResponse("FAILED",HttpStatus.BAD_REQUEST,errors));
    }

    @ExceptionHandler(EmployeeNotFoundException.class)
    public ResponseEntity<ErrorResponse> handleEmployeeNotFoundException(EmployeeNotFoundException exception){
        List<String> errors = new ArrayList<>();
        errors.add(exception.getMessage());
        return  ResponseEntity.status(404).body(new ErrorResponse("FAILED",HttpStatus.NOT_FOUND,errors));
    }

    @ExceptionHandler(SignatureException.class)
    public ResponseEntity<ErrorResponse> handleInvalidTokenException(SignatureException exception){
        List<String> errors = new ArrayList<>();
        errors.add(exception.getMessage());
        return  ResponseEntity.status(404).body(new ErrorResponse("FAILED",HttpStatus.NOT_FOUND,errors));
    }

    @ExceptionHandler(Exception.class)
    public ResponseEntity<ErrorResponse> handleOtherExceptions(Exception exception){
        List<String> errors = new ArrayList<>();
        errors.add(exception.getMessage());
        return ResponseEntity.status(500).body(new ErrorResponse("FAILED",HttpStatus.INTERNAL_SERVER_ERROR,errors));
    }

}
