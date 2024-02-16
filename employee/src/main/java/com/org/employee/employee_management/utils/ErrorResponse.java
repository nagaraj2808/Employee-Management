package com.org.employee.employee_management.utils;

import org.springframework.http.HttpStatus;

import java.util.List;

public class ErrorResponse {
    private String status;
    private HttpStatus statusCode;


    private List<String> errors;

    public ErrorResponse( String status,HttpStatus statusCode, List<String> errors) {
        this.statusCode = statusCode;
        this.status = status;
        this.errors = errors;
    }

    public HttpStatus getStatusCode() {
        return statusCode;
    }

    public void setStatusCode(HttpStatus statusCode) {
        this.statusCode = statusCode;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public List<String> getErrors() {
        return errors;
    }

    public void setErrors(List<String> errors) {
        this.errors = errors;
    }
}
