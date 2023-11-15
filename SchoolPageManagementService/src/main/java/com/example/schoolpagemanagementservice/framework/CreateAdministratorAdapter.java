package com.example.schoolpagemanagementservice.framework;

import com.example.schoolpagemanagementservice.application.out.CreateAdministratorOutputPort;
import com.example.schoolpagemanagementservice.domain.model.Administrator;
import com.example.schoolpagemanagementservice.framework.out.jpa.AdministratorJpaRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@RequiredArgsConstructor
@Component
public class CreateAdministratorAdapter implements CreateAdministratorOutputPort {

    private final AdministratorJpaRepository administratorJpaRepository;

    @Override
    public Administrator save(Administrator administrator) {
        return administratorJpaRepository.save(administrator);
    }
}
