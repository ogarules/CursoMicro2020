package com.example.organization.services;

import java.util.List;

import com.example.organization.models.Department;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@FeignClient(name = "department")
public interface DepartmentService {
    @GetMapping(value="/organization/{organizationId}")
    public List<Department> findByOrganizationId(@PathVariable String organizationId);

    @GetMapping(value="/organization/{organizationId}/with-employees")
    public List<Department> findByOrganizationIdWithEmployees(@PathVariable String organizationId);
}
