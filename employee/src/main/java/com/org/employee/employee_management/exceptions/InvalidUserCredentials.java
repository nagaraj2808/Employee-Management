package com.org.employee.employee_management.exceptions;

public class InvalidUserCredentials extends Exception {
    public InvalidUserCredentials(){
        super("Invalid credentials");
    }
}
