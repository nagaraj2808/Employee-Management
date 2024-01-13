package com.org.employee.employee_management.repositories;

import com.org.employee.employee_management.models.Employee;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface EmployeeRepository extends MongoRepository<Employee,String> {
    Optional<Employee> findByEmail(String email);
    Boolean existsByEmail(String email);


}
