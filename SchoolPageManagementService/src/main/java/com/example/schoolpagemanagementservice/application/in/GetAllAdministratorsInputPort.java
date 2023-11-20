package com.example.schoolpagemanagementservice.application.in;

import com.example.schoolpagemanagementservice.application.in.dto.AdministratorDto;
import com.example.schoolpagemanagementservice.application.out.AdministratorOutputPort;
import com.example.schoolpagemanagementservice.application.usecase.GetAllAdministratorsUseCase;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@RequiredArgsConstructor
@Transactional(readOnly = true)
@Service
public class GetAllAdministratorsInputPort implements GetAllAdministratorsUseCase {

    private final AdministratorOutputPort administratorOutputPort;

    @Override
    public List<AdministratorDto> getAllAdministrators() {
        return administratorOutputPort.fetchAll().stream()
                .map(AdministratorDto::fromDomainModel)
                .toList();
    }
}
