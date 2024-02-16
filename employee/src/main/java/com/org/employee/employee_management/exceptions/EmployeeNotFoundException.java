package com.org.employee.employee_management.exceptions;

public class EmployeeNotFoundException extends Exception{
    public EmployeeNotFoundException(){
        super("Employee not found");
    }
}
