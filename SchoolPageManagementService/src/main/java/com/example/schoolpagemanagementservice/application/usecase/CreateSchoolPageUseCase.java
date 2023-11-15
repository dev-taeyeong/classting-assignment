package com.example.schoolpagemanagementservice.application.usecase;

import com.example.schoolpagemanagementservice.application.in.dto.CreateSchoolPageCommand;
import com.example.schoolpagemanagementservice.application.in.dto.SchoolPageDto;

public interface CreateSchoolPageUseCase {

    SchoolPageDto createSchoolPage(CreateSchoolPageCommand command);
}
