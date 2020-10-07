package com.example.department.repositories;

import java.util.List;

import com.example.department.models.Department;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface DepartmentRepository extends CrudRepository<Department, String> {
    List<Department> findByOrganizationId(String organizationId);
}
