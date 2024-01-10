package com.org.example.employee.controllers;

import com.org.example.employee.models.Employee;
import com.org.example.employee.service.EmployeeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.query.Update;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/employee")
public class EmployeeController {
    @Autowired
    private EmployeeService employeeService;
    @GetMapping(value = {"/",""})
    public List<Employee> getAllEmployee(){
        return employeeService.findAll();
    }

    @GetMapping("/{empId}")
    public Employee getEmployeeById(@PathVariable String _id){

        Optional<Employee> optionalEmployee = employeeService.findById(_id);
        return optionalEmployee.orElse(null);

    }
    @GetMapping("/email/{emailId}")
    public Employee getEmployeeByEmail(@PathVariable(name = "emailId")String email){
        if(!employeeService.existsByEmail(email)){
            System.out.println("Employee doesnot exist");
            return null;
        }
        System.out.println(employeeService.findByEmail(email));
        return employeeService.findByEmail(email);
    }

    @GetMapping("/name/{empName}")
    public List<Employee> getEmployeesByName(@RequestParam(name = "empName")String name){
        return employeeService.findByName(name);
    }
    @PostMapping(value = {"","/"},consumes = "application/*")
    public Employee createEmployee(@RequestBody Employee employee){
        if(employee == null){
            System.out.println("Bad request");
            return null;
        }
        if(employee.getEmail() == null || employee.getName() == null){
            System.out.println("Error: email and name are required");
            return null;
        }
        if(employeeService.existsByEmail(employee.getEmail())){
            System.out.println("Employee with email " + employee.getEmail() + " already exists choose different email address");
            return null;
        }
        return employeeService.save(employee);
    }


    @PatchMapping("/{_id}")
    public Employee updateEmployee(@PathVariable(name = "_id")String _id,@RequestBody Employee employee){
        if(employee == null){
            System.out.println("Update unsuccessful please provide all the details to update an employee");
            return null;
        }
        if(!employeeService.existsById(_id)){
            System.out.println("404. Employee with id " + _id + " not found");
            return null;
        }
        return employeeService.save(employee);
    }

    @PatchMapping("/{_id}/name/{name}")
    public Employee updateEmployeeNameById(@PathVariable(name = "_id")String _id,@PathVariable(name = "name") String name){
        if(_id == null || !employeeService.existsById(_id)){
            System.out.println("Invalid employee id please try with valid id");
            return null;
        }
        Employee employee = employeeService.findById(_id).orElse(null);
        employee.setName(name);
        employeeService.save(employee);
        return employee;
    }
    @DeleteMapping("/{_id}")
    public Employee deleteEmployeeById(@PathVariable(name = "_id")String _id){
        Employee employee = employeeService.findById(_id).orElse(null);
        if(employee == null){
            System.out.println("Employee with id " + _id + " not found");
            return null;
        }

        employeeService.deleteById(_id);
        System.out.println("Deleted employee successfully....");
        return employee;
    }
}
