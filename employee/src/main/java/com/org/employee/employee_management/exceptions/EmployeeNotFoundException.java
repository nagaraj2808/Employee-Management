package com.org.employee.employee_management.exceptions;

public class EmployeeNotFoundException extends RuntimeException{
    public EmployeeNotFoundException(){
        super("Employee not found");
    }
}
