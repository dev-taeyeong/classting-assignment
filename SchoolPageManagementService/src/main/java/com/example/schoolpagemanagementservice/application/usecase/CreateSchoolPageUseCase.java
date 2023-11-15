package com.example.schoolpagemanagementservice.application.usecase;

import com.example.schoolpagemanagementservice.application.in.CreateSchoolPageCommand;
import com.example.schoolpagemanagementservice.application.in.SchoolPageDto;

public interface CreateSchoolPageUseCase {

    SchoolPageDto createSchoolPage(CreateSchoolPageCommand command);
}
