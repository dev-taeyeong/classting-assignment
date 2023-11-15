package com.example.schoolpagemanagementservice.application.inputport;

import com.example.schoolpagemanagementservice.application.outputport.CreateAdministratorOutputPort;
import com.example.schoolpagemanagementservice.application.usecase.CreateAdministratorUseCase;
import com.example.schoolpagemanagementservice.domain.model.Administrator;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@RequiredArgsConstructor
@Transactional
@Service
public class CreateAdministratorInputPort implements CreateAdministratorUseCase {

    private final CreateAdministratorOutputPort createAdministratorOutputPort;

    @Override
    public AdministratorDto createAdministrator(CreateAdministratorCommand command) {
        Administrator administrator = Administrator.createAdministrator(command.name());
        return AdministratorDto.fromDomainModel(administrator);
    }
}
