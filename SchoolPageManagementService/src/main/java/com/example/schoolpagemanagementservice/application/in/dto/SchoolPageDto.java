package com.example.schoolpagemanagementservice.application.in.dto;

import com.example.schoolpagemanagementservice.domain.model.SchoolPage;

public record SchoolPageDto(

        long id,
        long administratorId,
        String location,
        String name
) {

    public static SchoolPageDto fromDomainModel(SchoolPage domainModel) {
        return new SchoolPageDto(
                domainModel.getId(),
                domainModel.getAdministratorId(),
                domainModel.getLocation(),
                domainModel.getName());
    }
}
