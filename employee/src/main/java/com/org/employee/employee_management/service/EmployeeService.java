package com.org.employee.employee_management.service;

import com.org.employee.employee_management.config.jwt.CustomPasswordEncoder;
import com.org.employee.employee_management.config.jwt.TokenGenerator;
import com.org.employee.employee_management.exceptions.EmployeeAlreadyExistsException;
import com.org.employee.employee_management.exceptions.EmployeeNotFoundException;
import com.org.employee.employee_management.exceptions.InvalidUserCredentials;
import com.org.employee.employee_management.models.Employee;
import com.org.employee.employee_management.payloads.CreateEmployeePayload;
import com.org.employee.employee_management.payloads.LoginRequest;
import com.org.employee.employee_management.repositories.EmployeeRepository;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

@Service
public class EmployeeService implements UserDetailsService {
    @Autowired
    EmployeeRepository employeeRepository;

    @Autowired
    CustomPasswordEncoder customPasswordEncoder;
    @Autowired
    TokenGenerator tokenGenerator;
    Logger logger = LoggerFactory.getLogger(EmployeeService.class);

    /**
     * This method retrieves all employee records
     * @return List of employee object
     * @throws EmployeeNotFoundException  This exception arises when no employee record is found
     */
    public List<Employee> getAllEmployees() throws EmployeeNotFoundException{
        logger.info("Retrieving all the employee records");
        List<Employee> employees =  employeeRepository.findAll();
        if(employees.isEmpty()){
            logger.error("No employees found");
            throw new EmployeeNotFoundException();
        }
        logger.info("Retrieved " + employees.size() + " employee records");
        return employees;
    }

    /**
     * This method retrieves employee record
     * @param email email of an employee
     * @return employee object
     * @throws EmployeeNotFoundException  This exception arises when employee is not found
     */
    public Employee getEmployeeByEmail(String email) throws EmployeeNotFoundException{
        logger.info("Retrieving employee record with email");
        if(!employeeRepository.existsByEmail(email)) {
            logger.error("Employee record not found");
            throw new EmployeeNotFoundException();
        }
        logger.info("Successfully retrieved employee record");
        return employeeRepository.findByEmail(email).get();
    }

    /**
     * This method retrieves employee record
     * @param _id  mongodb id of an employee record
     * @return employee object
     * @throws EmployeeNotFoundException  This exception arises when employee is not found
     */
    public Employee getEmployeeById(String _id) throws EmployeeNotFoundException{
        logger.info("Retrieving employee record with id");
        if(employeeRepository.existsById(_id)) {
            logger.error("Employee record with id does not exist");
            throw new EmployeeNotFoundException();
        }
        logger.info("Employee record retrieved successfully");
        return employeeRepository.findById(_id).get();

    }

    /**
     * This method creates an employee record
     * @param employee  object
     * @return employee object
     * @throws EmployeeAlreadyExistsException This exception arises when given employee with email already exist
     */
    public Employee createEmployee(Employee employee) throws EmployeeAlreadyExistsException {
        logger.info("Creating a new employee record");
//        Employee employee = employeeRepository.findByEmail(employee_.getEmail()).orElse(null);
        if(employeeRepository.existsByEmail(employee.getEmail())){
            logger.error("Employee already exist with this email");
            throw new EmployeeAlreadyExistsException(employee.getEmail());
        }


        String password = customPasswordEncoder.encode(employee.getPassword());
        employee.setPassword(password);
        logger.info("Successfully created an employee record");

        return employeeRepository.save(employee);
    }

    /**
     * This method updates employee's age
     * @param email  email of the employee
     * @param age  updated age of an employee
     * @return employee object
     * @throws EmployeeNotFoundException This exception arises when employee is not found
     */

    @Deprecated(since = "Since we are not handling age anymore this function support is no more appreciated")
    public Employee updateEmployeeAge(String email,Integer age) throws EmployeeNotFoundException{
        logger.info("Updating employee age for a given employee with email");
        Employee employee = employeeRepository.findByEmail(email).orElse(null);
        if(employee == null){
            logger.error("No employee records found with email");
            throw new EmployeeNotFoundException();
        }
        logger.info("Updating employee age");
//        employee.setAge(age);
        logger.info("Successfully updated employee age");
        return employeeRepository.save(employee);
    }


    @PreAuthorize("hasRole('ADMIN')")
    public Employee updateEmployeeName(String email,String name) throws EmployeeNotFoundException
    {
        logger.info("Updating employee name for a given employee with email");
        Employee employee = employeeRepository.findByEmail(email).orElse(null);
        if(employee == null){
            logger.error("No employee records found with email");
            throw new EmployeeNotFoundException();
        }
        logger.info("Updating employee name");
        employee.setName(name);
        logger.info("Successfully updated employee name");
        return  employeeRepository.save(employee);
    }

    /**
     * This method deletes the employee record
     * @param email email of an employee
     * @return employee object
     * @throws EmployeeNotFoundException This exception arises when employee is not found
     */
    @PreAuthorize("hasRole('ADMIN')")
    public Employee deleteEmployeeByEmail(String email)throws EmployeeNotFoundException{
        logger.info("Deleting an employee with email");
        Employee employee = employeeRepository.findByEmail(email).orElse(null);
//        log.debug("Employee object" +employee);
        if(employee == null){
            logger.error("Employee with email not found");
            throw new EmployeeNotFoundException();
        }
        employeeRepository.delete(employee);
        logger.info("Successfully deleted employee record with given email");
        return employee;
    }


    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        try {
            return this.getEmployeeByEmail(username);
        } catch (EmployeeNotFoundException e) {
            throw new UsernameNotFoundException("User with " + username + " not found");
        }
    }


    public String login(LoginRequest request) throws EmployeeNotFoundException, InvalidUserCredentials {
        Employee employee = employeeRepository.findByEmail(request.getEmail()).orElse(null);
        if(employee == null){
            throw new EmployeeNotFoundException();
        }
        if(!customPasswordEncoder.matches(request.getPassword(),employee.getPassword() )){
            throw new InvalidUserCredentials();
        }
        System.out.println(employee);
        String token = tokenGenerator.createToken(employee);
        return token;
    }
}
