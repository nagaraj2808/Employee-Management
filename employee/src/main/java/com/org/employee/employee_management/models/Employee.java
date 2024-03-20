package com.org.employee.employee_management.models;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotEmpty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.index.Indexed;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Date;
import java.util.List;

@Document(collection = "employees")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Employee implements UserDetails {
    @Id
    private String _id;

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
    public Employee(String dob,String authority) {
        this.dob = dob;
        Collection<SimpleGrantedAuthority> authorities = new ArrayList<>();
        authorities.add(new SimpleGrantedAuthority("EMPLOYEE"));
        if(authority.equals("MANAGER")){
            authorities.add(new SimpleGrantedAuthority("MANAGER"));
        }
        if (authority.equals("ADMIN")){
            authorities.add(new SimpleGrantedAuthority("MANAGER"));
            authorities.add(new SimpleGrantedAuthority("ADMIN"));
        }

    }






    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        Collection<SimpleGrantedAuthority> authorities = new ArrayList<>();

        authorities.add(new SimpleGrantedAuthority("EMPLOYEE"));
        if(authority.equals("MANAGER")){
            authorities.add(new SimpleGrantedAuthority("MANAGER"));
        }
        if (authority.equals("ADMIN")){
            authorities.add(new SimpleGrantedAuthority("MANAGER"));
            authorities.add(new SimpleGrantedAuthority("ADMIN"));
        }
        return authorities;
    }

    @Override
    public String getPassword() {
        return password;
    }

    @Override
    public String getUsername() {
        return this.email;
    }

    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

    @Override
    public boolean isAccountNonLocked() {
        return true;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    @Override
    public boolean isEnabled() {
        return true;
    }
}
