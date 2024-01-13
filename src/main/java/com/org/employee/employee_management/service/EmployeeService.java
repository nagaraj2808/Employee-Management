package com.org.employee.employee_management.service;

import com.org.employee.employee_management.exceptions.EmployeeAlreadyExistsException;
import com.org.employee.employee_management.exceptions.EmployeeNotFoundException;
import com.org.employee.employee_management.models.Employee;
import com.org.employee.employee_management.repositories.EmployeeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class EmployeeService {
    @Autowired
    EmployeeRepository employeeRepository;

    public List<Employee> getAllEmployees() throws EmployeeNotFoundException{
        List<Employee> employees =  employeeRepository.findAll();
        if(employees.isEmpty()){
            throw new EmployeeNotFoundException();
        }
        return employees;
    }

    public Employee getEmployeeByEmail(String email) throws EmployeeNotFoundException{
        if(!employeeRepository.existsByEmail(email)) throw new EmployeeNotFoundException();
        return employeeRepository.findByEmail(email).get();
    }

    public Employee getEmployeeById(String _id) throws EmployeeNotFoundException{
        if(employeeRepository.existsById(_id)) throw new EmployeeNotFoundException();
        return employeeRepository.findById(_id).get();

    }

    public Employee createEmployee(Employee employee) throws EmployeeAlreadyExistsException {
        if(employeeRepository.existsByEmail(employee.getEmail())){
            throw new EmployeeAlreadyExistsException(employee.getEmail());
        }

        return employeeRepository.save(employee);
    }

    public Employee updateEmployeeAge(String email,Integer age) throws EmployeeNotFoundException{
        Employee employee = employeeRepository.findByEmail(email).orElse(null);
        if(employee == null){
            throw new EmployeeNotFoundException();
        }
        employee.setAge(age);
        return employeeRepository.save(employee);
    }


    public Employee deleteEmployeeByEmail(String email)throws EmployeeNotFoundException{
        Employee employee = employeeRepository.findByEmail(email).orElse(null);
        if(employee == null){
            throw new EmployeeNotFoundException();
        }
        employeeRepository.delete(employee);
        return employee;
    }


}
