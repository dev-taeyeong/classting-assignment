package com.example.schoolpagemanagementservice.application.in;

import com.example.schoolpagemanagementservice.domain.model.Administrator;

public record AdministratorDto(

        long id,
        String name
) {

    public static AdministratorDto fromDomainModel(Administrator domainModel) {
        return new AdministratorDto(domainModel.getId(), domainModel.getName());
    }
}
