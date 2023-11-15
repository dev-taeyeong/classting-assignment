package com.example.schoolpagemanagementservice.application.out;

import com.example.schoolpagemanagementservice.domain.model.Administrator;
import com.example.schoolpagemanagementservice.domain.model.SchoolPage;

import java.util.Optional;

public interface CreateSchoolPageOutputPort {

    SchoolPage save(SchoolPage schoolPage);

    Optional<Administrator> fetchAdministratorById(long administratorId);
}
