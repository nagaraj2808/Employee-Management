package com.org.employee.employee_management.utils;

import org.springframework.http.HttpStatus;

import java.util.List;

public class ErrorResponse {
    private HttpStatus status;

    public HttpStatus getStatus() {
        return status;
    }

    public void setStatus(HttpStatus status) {
        this.status = status;
    }

    public List<String> getErrors() {
        return errors;
    }

    public void setErrors(List<String> errors) {
        this.errors = errors;
    }

    private List<String> errors;

    public ErrorResponse(HttpStatus status, List<String> errors) {
        this.status = status;
        this.errors = errors;
    }
}
