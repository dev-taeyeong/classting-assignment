package com.example.schoolpagemanagementservice.application.usecase;

import com.example.schoolpagemanagementservice.application.in.dto.AdministratorDto;
import com.example.schoolpagemanagementservice.application.in.dto.CreateAdministratorCommand;

public interface CreateAdministratorUseCase {

    AdministratorDto createAdministrator(CreateAdministratorCommand command);
}
