package com.example.schoolpagemanagementservice.framework.out;

import com.example.schoolpagemanagementservice.application.out.CreateSchoolPageOutputPort;
import com.example.schoolpagemanagementservice.domain.model.Administrator;
import com.example.schoolpagemanagementservice.domain.model.SchoolPage;
import com.example.schoolpagemanagementservice.framework.out.jpa.AdministratorJpaRepository;
import com.example.schoolpagemanagementservice.framework.out.jpa.SchoolPageJpaRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.Optional;

@RequiredArgsConstructor
@Component
public class CreateSchoolPageAdapter implements CreateSchoolPageOutputPort {

    private final SchoolPageJpaRepository schoolPageJpaRepository;
    private final AdministratorJpaRepository administratorJpaRepository;

    @Override
    public SchoolPage save(SchoolPage schoolPage) {
        return schoolPageJpaRepository.save(schoolPage);
    }

    @Override
    public Optional<Administrator> fetchAdministratorById(long administratorId) {
        return administratorJpaRepository.findById(administratorId);
    }
}
