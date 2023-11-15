package com.example.schoolpagemanagementservice.application.out;

import com.example.schoolpagemanagementservice.domain.model.SchoolPage;

public interface CreateSchoolPageOutputPort {

    SchoolPage save(SchoolPage schoolPage);
}
