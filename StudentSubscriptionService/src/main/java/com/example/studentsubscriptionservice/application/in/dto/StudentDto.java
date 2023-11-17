package com.example.studentsubscriptionservice.application.in.dto;

import com.example.studentsubscriptionservice.domain.model.Student;

public record StudentDto(

        Long id,
        String name
) {

    public static StudentDto fromDomainModel(Student domainModel) {
        return new StudentDto(domainModel.getId(), domainModel.getName());
    }
}
