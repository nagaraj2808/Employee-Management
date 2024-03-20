package com.org.employee.employee_management.config;

import com.org.employee.employee_management.config.jwt.TokenGenerator;
import com.org.employee.employee_management.models.Employee;
import com.org.employee.employee_management.service.EmployeeService;
import io.jsonwebtoken.Claims;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.SneakyThrows;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.web.authentication.WebAuthenticationDetailsSource;
import org.springframework.util.StringUtils;
import org.springframework.web.filter.OncePerRequestFilter;

import java.io.IOException;

public class AuthFilter extends OncePerRequestFilter {

    @Autowired
    TokenGenerator tokenGenerator;

    @Autowired
    EmployeeService employeeService;
    @Override
    @SneakyThrows
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain) throws ServletException, IOException{



                String token = getJWTFromRequest(request);

                if (StringUtils.hasText(token)) {

                    Claims claims = tokenGenerator.validateToken(token);

                    String email = tokenGenerator.getSubject(claims);
                    System.out.println(email);
                    Employee employee = employeeService.getEmployeeByEmail(email);

                    System.out.println(employee);
                    UsernamePasswordAuthenticationToken authenticationToken = new UsernamePasswordAuthenticationToken(employee, employee.getPassword(), employee.getAuthorities());
                    System.out.println(authenticationToken);


                    authenticationToken.setDetails(new WebAuthenticationDetailsSource().buildDetails(request));
                    System.out.println(authenticationToken);
                    SecurityContextHolder.getContext().setAuthentication(authenticationToken);
                    System.out.println("done");
                    System.out.println(SecurityContextHolder.getContext().getAuthentication());
                }
                filterChain.doFilter(request,response);



//                return;





    }

    private String getJWTFromRequest(HttpServletRequest request) {
        String bearerToken = request.getHeader("Authorization");
        if(StringUtils.hasText(bearerToken) && bearerToken.startsWith("Bearer "))
            return bearerToken.substring(7);
        return null;
    }
}
