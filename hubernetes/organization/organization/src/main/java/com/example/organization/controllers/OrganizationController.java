package com.example.organization.controllers;

import java.util.Optional;

import com.example.organization.models.Organization;
import com.example.organization.repositories.OrganizationRepository;
import com.example.organization.services.DepartmentService;
import com.example.organization.services.EmployeeService;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;



@RestController
public class OrganizationController {
    private static final Logger LOGGER = LoggerFactory.getLogger(OrganizationController.class);
    
    @Autowired
    OrganizationRepository repository;

    @Autowired
    DepartmentService departmentService;

    @Autowired
    EmployeeService employeeService;

    @GetMapping
    public Iterable<Organization> getAllOrganizations() {
        LOGGER.info("Getting all organizations");
        return this.repository.findAll();
    }
    
    @GetMapping("/{id}")
    public Optional<Organization> getOrganizations(@PathVariable("id") String id) {
        LOGGER.info("Getting Organization {}", id);
        return this.repository.findById(id);
    }
    
    @PostMapping
    public Organization saveOrganization(@RequestBody Organization entity) {
        LOGGER.info("Adding Organization");
        return this.repository.save(entity);
    }

    @DeleteMapping("/{id}")
    public void saveOrganization(@PathVariable("id") String id) {
        LOGGER.info("Deleting Organization");
        this.repository.deleteById(id);
    }

    @GetMapping("/{id}/departments")
    public Optional<Organization> getOrganizationWithDepartments(@PathVariable("id") String id) {
        LOGGER.info("Getting Organization with departments {}", id);
        Optional<Organization> organization = this.repository.findById(id);

        if(organization.isPresent()){
            Organization o = organization.get();
            o.setDepartments(this.departmentService.findByOrganizationId(id));            
        }

        return organization;
    }

    @GetMapping("/{id}/departmentemployees")
    public Optional<Organization> getOrganizationWithDepartmentsAndEmployees(@PathVariable("id") String id) {
        LOGGER.info("Getting Organization with departments and employees {}", id);
        Optional<Organization> organization = this.repository.findById(id);

        if(organization.isPresent()){
            Organization o = organization.get();
            o.setDepartments(this.departmentService.findByOrganizationIdWithEmployees(id));            
        }

        return organization;
    }

    @GetMapping("/{id}/employees")
    public Optional<Organization> getOrganizationWithEmployees(@PathVariable("id") String id) {
        LOGGER.info("Getting Organization {}", id);
        Optional<Organization> organization = this.repository.findById(id);

        if(organization.isPresent()){
            Organization o = organization.get();
            o.setEmployees(this.employeeService.findByOrganizationId(id));            
        }

        return organization;
    }


}
