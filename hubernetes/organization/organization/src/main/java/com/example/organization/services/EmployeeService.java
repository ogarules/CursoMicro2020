package com.example.organization.services;

import java.util.List;

import com.example.organization.models.Employee;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;


@FeignClient(name = "employee")
public interface EmployeeService {
  
    @GetMapping(value="/organization/{organizationId}")
    public List<Employee> findByOrganizationId(@PathVariable String organizationId);
}
