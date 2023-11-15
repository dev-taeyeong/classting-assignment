package com.example.schoolpagemanagementservice.application.in;

import com.example.schoolpagemanagementservice.application.in.dto.AdministratorDto;
import com.example.schoolpagemanagementservice.application.in.dto.CreateAdministratorCommand;
import com.example.schoolpagemanagementservice.application.out.CreateAdministratorOutputPort;
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
        Administrator savedAdministrator = createAdministratorOutputPort.save(administrator);
        return AdministratorDto.fromDomainModel(savedAdministrator);
    }
}
