package com.org.employee.employee_management.controllers;

import com.org.employee.employee_management.models.Employee;
import com.org.employee.employee_management.service.EmployeeService;
import com.org.employee.employee_management.utils.Response;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("api/employees")
public class EmployeeController {
    @Autowired
    EmployeeService employeeService;
    @PostMapping(value = {"/",""})
    public ResponseEntity<Response> createEmployee(@RequestBody @Valid Employee employee) throws Exception{

        Employee createdEmployee = employeeService.createEmployee(employee);
        Response response = new Response("SUCCESS",HttpStatus.CREATED,"created user successfully",createdEmployee);
        return ResponseEntity.status(201).body(response);
    }

    @GetMapping(value = {"","/"})
    public ResponseEntity<Response> getEmployees() throws Exception{
        List<Employee> employees = employeeService.getAllEmployees();
        Response response = new Response("SUCCESS",HttpStatus.OK,"Fetched " + employees.size() + " employees successfully",employees);
        return ResponseEntity.status(200).body(response);
    }

    @GetMapping(value = "/{email}")
    public ResponseEntity<Response> getEmployeeByEmail(@PathVariable(name = "email") String email) throws Exception{
        Employee employee = employeeService.getEmployeeByEmail(email);
        return ResponseEntity.status(200).body(new Response("SUCCESS",HttpStatus.OK,"Fetched employee with email " + email + " successfully",employee));
    }


    @PatchMapping(value = "")
    public ResponseEntity<Response> updateEmployeeAge(@RequestParam(name = "email",required = true) String email,@RequestParam(name = "age",required = true) Integer age) throws Exception{
        Employee employee = employeeService.updateEmployeeAge(email,age);
        return  ResponseEntity.status(200).body(new Response("SUCCESS",HttpStatus.OK,"Updated employee age to " + age + " of employee with email " + email,employee));
    }

    @DeleteMapping(value = "")
    public ResponseEntity<Response> deleteEmployee(@RequestParam(required = true,name = "email") String email) throws Exception{
        //LOG.INFO("EmployeeController::deleteEmployee");
        Employee employee = employeeService.deleteEmployeeByEmail(email);
        return  ResponseEntity.status(200).body(new Response("SUCCESS",HttpStatus.OK,"Deleted employee with email " + email,employee));
    }

}
