package com.example.employee.controllers;

import org.slf4j.LoggerFactory;
import org.slf4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import com.example.employee.repositories.*;
import com.example.employee.models.*;
import java.util.Optional;
import java.util.List;

@RestController
public class EmployeeController {
    private static final Logger logger = LoggerFactory.getLogger(EmployeeController.class);

    @Autowired
    EmployeeRepository repository;

    @GetMapping(value="/")
    public Iterable<Employee> findAll(){
        logger.info("List employees");
        return this.repository.findAll();
    }

    @GetMapping(value="/{id}")
    public Optional<Employee> findById(@PathVariable String id){
        logger.info("Employee to find:{}", id);
        return this.repository.findById(id);
    }

    @PostMapping(value="/")
    public Employee saveEmployee(@RequestBody Employee employee){
        logger.info("Save employee");
        return this.repository.save(employee);
    }

    @GetMapping(value="/department/{departmentId}")
    public List<Employee> findByDepartmentID(@PathVariable String departmentId){
        logger.info("Employees to find by department Id:{}", departmentId);
        return this.repository.findByDepartmentId(departmentId);
    }

    @GetMapping(value="/organization/{organizationId}")
    public List<Employee> findByOrganizationId(@PathVariable String organizationId){
        logger.info("Employees to find by organization Id:{}", organizationId);
        return this.repository.findByOrganizationId(organizationId);
    }
}
