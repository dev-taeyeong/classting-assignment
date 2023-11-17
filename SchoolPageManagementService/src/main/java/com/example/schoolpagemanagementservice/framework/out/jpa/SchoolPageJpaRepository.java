package com.example.schoolpagemanagementservice.framework.out.jpa;

import com.example.schoolpagemanagementservice.domain.model.SchoolPage;
import org.springframework.data.jpa.repository.JpaRepository;

public interface SchoolPageJpaRepository extends JpaRepository<SchoolPage, Long> {

    boolean existsByIdAndAdministratorId(long schoolPageId, long administratorId);
}
