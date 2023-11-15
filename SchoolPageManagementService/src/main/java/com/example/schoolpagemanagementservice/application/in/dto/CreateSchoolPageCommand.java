package com.example.schoolpagemanagementservice.application.in.dto;

public record CreateSchoolPageCommand(

        long administratorId,
        String location,
        String name
) {
}
