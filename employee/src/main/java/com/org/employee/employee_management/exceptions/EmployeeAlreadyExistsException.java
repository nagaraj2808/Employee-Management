package com.org.employee.employee_management.exceptions;

public class EmployeeAlreadyExistsException extends RuntimeException{
    public EmployeeAlreadyExistsException(String email){
        super("Employee with email " + email + " already exists");
    }
}
