package com.example.schoolpagemanagementservice.application.usecase;

import com.example.schoolpagemanagementservice.application.in.AdministratorDto;
import com.example.schoolpagemanagementservice.application.in.CreateAdministratorCommand;

public interface CreateAdministratorUseCase {

    AdministratorDto createAdministrator(CreateAdministratorCommand command);
}
