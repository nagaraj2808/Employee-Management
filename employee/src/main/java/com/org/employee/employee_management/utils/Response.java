package com.org.employee.employee_management.utils;


import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseBody;


public class Response {
    private String status;
    private HttpStatus statusCode;
    private String message;
    private Object data;

    public Response(String status, HttpStatus statusCode, String message, Object data) {
        this.status = status;
        this.statusCode = statusCode;
        this.message = message;
        this.data = data;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public HttpStatus getStatusCode() {
        return statusCode;
    }

    public void setStatusCode(HttpStatus statusCode) {
        this.statusCode = statusCode;
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
}
