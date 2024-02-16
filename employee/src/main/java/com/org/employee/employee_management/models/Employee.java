package com.org.employee.employee_management.models;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotEmpty;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.index.Indexed;
import org.springframework.data.mongodb.core.mapping.Document;
@Document(collection = "employees")
public class Employee {
    @Id
    private String _id;

    @NotBlank
    @Email(message = "Email should be valid")
    private String email;

    @NotEmpty(message = "name cannot be empty")
    private String name;

    private String designation;
    private Integer age;

    public String get_id() {
        return _id;
    }

    public String getEmail() {
        return email;
    }

    public String getName() {
        return name;
    }

    public String getDesignation() {
        return designation;
    }

    public Integer getAge() {
        return age;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setDesignation(String designation) {
        this.designation = designation;
    }

    public void setAge(Integer age) {
        this.age = age;
    }
}
