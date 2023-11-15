package com.example.schoolpagemanagementservice.framework.in.web.request;

import com.example.schoolpagemanagementservice.application.in.dto.CreateAdministratorCommand;

public record CreateAdministratorRequest(

        String name
) {

    public CreateAdministratorCommand toCommand() {
        return new CreateAdministratorCommand(name);
    }
}
