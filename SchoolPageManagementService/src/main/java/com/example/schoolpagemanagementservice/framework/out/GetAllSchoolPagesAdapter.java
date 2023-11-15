package com.example.schoolpagemanagementservice.framework.out;

import com.example.schoolpagemanagementservice.application.out.GetAllSchoolPagesOutputPort;
import com.example.schoolpagemanagementservice.domain.model.SchoolPage;
import com.example.schoolpagemanagementservice.framework.out.jpa.SchoolPageJpaRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Component;

@RequiredArgsConstructor
@Component
public class GetAllSchoolPagesAdapter implements GetAllSchoolPagesOutputPort {

    private final SchoolPageJpaRepository schoolPageJpaRepository;

    @Override
    public Page<SchoolPage> getSchoolPages(Pageable pageable) {
        return schoolPageJpaRepository.findAll(pageable);
    }
}
