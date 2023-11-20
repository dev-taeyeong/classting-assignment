package com.example.schoolpagemanagementservice.application.usecase;

import com.example.schoolpagemanagementservice.application.in.dto.AdministratorDto;

import java.util.List;

public interface GetAllAdministratorsUseCase {

    List<AdministratorDto> getAllAdministrators();
}
