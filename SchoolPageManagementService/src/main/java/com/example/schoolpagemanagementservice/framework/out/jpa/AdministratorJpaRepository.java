package com.example.schoolpagemanagementservice.framework.out.jpa;

import com.example.schoolpagemanagementservice.domain.model.Administrator;
import org.springframework.data.jpa.repository.JpaRepository;

public interface AdministratorJpaRepository extends JpaRepository<Administrator, Long> {
}
