package com.example.schoolpagemanagementservice.framework.out;

import com.example.schoolpagemanagementservice.application.out.CreateSchoolPageOutputPort;
import com.example.schoolpagemanagementservice.domain.model.SchoolPage;
import com.example.schoolpagemanagementservice.framework.out.jpa.SchoolPageJpaRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@RequiredArgsConstructor
@Component
public class CreateSchoolPageAdapter implements CreateSchoolPageOutputPort {

    private final SchoolPageJpaRepository schoolPageJpaRepository;

    @Override
    public SchoolPage save(SchoolPage schoolPage) {
        return schoolPageJpaRepository.save(schoolPage);
    }
}
