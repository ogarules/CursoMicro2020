package com.example.department.controllers;

import java.util.List;
import java.util.Optional;

import com.example.department.models.Department;
import com.example.department.repositories.DepartmentRepository;
import com.example.department.services.EmployeeService;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

@RestController
public class DepartmentController {
    private static final Logger logger = LoggerFactory.getLogger(DepartmentController.class);

    @Autowired
    DepartmentRepository repository;

    @Autowired
    EmployeeService employeeService;

    @GetMapping
    public Iterable<Department> getAllDepartments() {
        logger.info("Getting all departments");
        return this.repository.findAll();
    }
    
    @GetMapping("/{id}")
    public Optional<Department> getDepartment(@PathVariable("id") String id) {
        logger.info("Getting department {}", id);
        return this.repository.findById(id);
    }
    
    @PostMapping
    public Department saveDepartment(@RequestBody Department entity) {
        logger.info("SAving department");
        
        return this.repository.save(entity);
    }
    
    @GetMapping("/organization/{organizationId}")
    public Iterable<Department> getByOrganizationId(@PathVariable("organizationId") String organizationId)
    {
        logger.info("Getting department by organizationid {}", organizationId);
        return this.repository.findByOrganizationId(organizationId);
    }

    @GetMapping("/organization/{organizationId}/with-employees")
    public Iterable<Department> getByOrganizationIdWithEmployees(@PathVariable("organizationId") String organizationId)
    {
        logger.info("Getting department by organizationid {}", organizationId);
        List<Department> departments = this.repository.findByOrganizationId(organizationId);
        departments.forEach(d -> d.setEmployees(employeeService.findByDepartmentID(d.getId())));

        return departments;
    }
}
