package com.example.schoolpagemanagementservice.framework.in.web.request;

import com.example.schoolpagemanagementservice.application.in.dto.CreateSchoolPageCommand;

public record CreateSchoolPageRequest(

        long administratorId,
        String location,
        String name
) {

    public CreateSchoolPageCommand toCommand() {
        return new CreateSchoolPageCommand(administratorId, location, name);
    }
}
