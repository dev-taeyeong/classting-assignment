package com.example.schoolpagemanagementservice.framework.in.web;

import com.example.schoolpagemanagementservice.application.in.CreateSchoolPageCommand;

public record CreateSchoolPageRequest(

        long administratorId,
        String location,
        String name
) {

    public CreateSchoolPageCommand toCommand() {
        return new CreateSchoolPageCommand(administratorId, location, name);
    }
}
