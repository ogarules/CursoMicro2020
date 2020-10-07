package com.example.employee.repositories;
import org.springframework.data.repository.CrudRepository;
import com.example.employee.models.*;
import java.util.List;

public interface EmployeeRepository extends CrudRepository<Employee, String> {
    List<Employee> findByDepartmentId(String departmentId);
    List<Employee> findByOrganizationId(String organizationId);
}
