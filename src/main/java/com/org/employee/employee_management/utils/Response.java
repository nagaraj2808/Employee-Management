package com.org.employee.employee_management.utils;


import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseBody;


public class Response {
    private HttpStatus status;
    private String message;
    private Object data;

    public HttpStatus getStatus() {
        return status;
    }

    public void setStatus(HttpStatus status) {
        this.status = status;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public Object getData() {
        return data;
    }

    public void setData(Object data) {
        this.data = data;
    }

    public Response(HttpStatus status, String message, Object data) {
        this.status = status;
        this.message = message;
        this.data = data;
    }
}