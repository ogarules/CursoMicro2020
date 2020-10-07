package com.example.organization.repositories;

import com.example.organization.models.Organization;

import org.springframework.data.repository.CrudRepository;

public interface OrganizationRepository extends CrudRepository<Organization, String> {
    
}
