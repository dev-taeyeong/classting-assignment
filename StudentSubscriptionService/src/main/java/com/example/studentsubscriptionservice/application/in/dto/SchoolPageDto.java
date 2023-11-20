package com.example.studentsubscriptionservice.application.in.dto;

public record SchoolPageDto(

        Long id,
        Long administratorId,
        String location,
        String name
) {
}
