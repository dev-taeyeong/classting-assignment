package com.example.schoolpagemanagementservice.application.usecase;

import com.example.schoolpagemanagementservice.application.inputport.AdministratorDto;
import com.example.schoolpagemanagementservice.application.inputport.CreateAdministratorCommand;

public interface CreateAdministratorUseCase {

    AdministratorDto createAdministrator(CreateAdministratorCommand command);
}
