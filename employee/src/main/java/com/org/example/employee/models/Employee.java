package com.org.example.employee.models;

import com.mongodb.lang.NonNull;
import org.springframework.data.annotation.Id;


public class Employee {
    @Id
    private String _id;


    private String email;

    private String name;

    private Double salary;

    private Integer age;

    public Employee(String email, String name, Double salary, Integer age) {
        this.email = email;
        this.name = name;
        this.salary = salary;
        this.age = age;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setSalary(Double salary) {
        this.salary = salary;
    }

    public void setAge(Integer age) {
        this.age = age;
    }


    public String get_id() {
        return _id;
    }

    public String getEmail() {
        return email;
    }

    public String getName() {
        return name;
    }

    public Double getSalary() {
        return salary;
    }

    public Integer getAge() {
        return age;
    }
}
