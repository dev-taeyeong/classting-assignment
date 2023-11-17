package com.example.schoolpagemanagementservice.framework.out;

import com.example.schoolpagemanagementservice.application.out.SchoolPageOutputPort;
import com.example.schoolpagemanagementservice.framework.out.jpa.SchoolPageJpaRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@RequiredArgsConstructor
@Component
public class SchoolPageAdapter implements SchoolPageOutputPort {

    private final SchoolPageJpaRepository schoolPageJpaRepository;

    @Override
    public boolean existByIdAndAdministratorId(long administratorId, long schoolPageId) {
        return false;
    }
}
