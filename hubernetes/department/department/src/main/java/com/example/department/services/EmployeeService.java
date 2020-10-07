package com.example.department.services;

import java.util.List;

import com.example.department.models.Employee;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@FeignClient(name = "employee")
public interface EmployeeService {
    @GetMapping(value="/department/{departmentId}")
    List<Employee> findByDepartmentID(@PathVariable String departmentId);
}
