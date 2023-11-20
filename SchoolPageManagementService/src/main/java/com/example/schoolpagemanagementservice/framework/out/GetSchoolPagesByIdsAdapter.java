package com.example.schoolpagemanagementservice.framework.out;

import com.example.schoolpagemanagementservice.application.out.GetSchoolPagesByIdsOutputPort;
import com.example.schoolpagemanagementservice.domain.model.SchoolPage;
import com.example.schoolpagemanagementservice.framework.out.jpa.SchoolPageJpaRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.List;

@RequiredArgsConstructor
@Component
public class GetSchoolPagesByIdsAdapter implements GetSchoolPagesByIdsOutputPort {

    private final SchoolPageJpaRepository schoolPageJpaRepository;

    @Override
    public List<SchoolPage> fetchByIds(List<Long> schoolPageIds) {
        return schoolPageJpaRepository.findAllById(schoolPageIds);
    }
}
