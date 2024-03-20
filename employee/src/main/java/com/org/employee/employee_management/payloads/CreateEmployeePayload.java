package com.org.employee.employee_management.payloads;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotEmpty;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.security.core.GrantedAuthority;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Collection;
import java.util.Date;



@Data
public class CreateEmployeePayload {

    @NotBlank
    @Email(message = "Email should be valid")
    private String email;

    @NotEmpty(message = "name cannot be empty")
    private String name;

    private String designation;

    @NotBlank
    private String dob;

    private String authority;

    @NotBlank
    private String password;

    public CreateEmployeePayload() throws ParseException {

    }

    public CreateEmployeePayload(String email, String name, String designation, String dob, String authority, String password) throws ParseException {
        this.email = email;
        this.name = name;
        this.designation = designation;
        DateFormat df = new SimpleDateFormat("dd/MM/yyyy");

        Date d1 = df.parse(dob);
        this.dob = df.format(d1);
        this.authority = authority;
        this.password = password;
    }

    public CreateEmployeePayload(String email, String name, String dob, String authority) throws ParseException {

        DateFormat df = new SimpleDateFormat("dd/MM/yyyy");

        Date d1 = df.parse(dob);
        this.dob = df.format(d1);
        this.email = email;
        this.name = name;
        this.authority = authority;


    }


}
