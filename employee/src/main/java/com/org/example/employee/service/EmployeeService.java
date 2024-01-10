package com.org.example.employee.service;

import com.org.example.employee.models.Employee;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Service;
import java.util.List;
@Service
public interface EmployeeService extends MongoRepository<Employee,String> {
    Boolean existsByEmail(String email);
    Employee findByEmail(String email);

    List<Employee> findByName(String name);

}
