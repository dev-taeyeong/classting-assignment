package com.example.schoolpagemanagementservice.application.in;

public record CreateSchoolPageCommand(

        long administratorId,
        String location,
        String name
) {
}
