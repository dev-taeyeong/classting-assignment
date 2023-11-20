package com.example.schoolpagemanagementservice.application.in;

import com.example.schoolpagemanagementservice.application.in.dto.SchoolPageDto;
import com.example.schoolpagemanagementservice.application.out.GetSchoolPagesByIdsOutputPort;
import com.example.schoolpagemanagementservice.application.usecase.GetSchoolPagesByIdsUseCase;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@RequiredArgsConstructor
@Transactional(readOnly = true)
@Service
public class GetSchoolPagesByIdsInputPort implements GetSchoolPagesByIdsUseCase {

    private final GetSchoolPagesByIdsOutputPort getSchoolPagesByIdsOutputPort;

    @Override
    public List<SchoolPageDto> getSchoolPagesByIds(List<Long> schoolPageIds) {
        return getSchoolPagesByIdsOutputPort.fetchByIds(schoolPageIds).stream()
                .map(SchoolPageDto::fromDomainModel)
                .toList();
    }
}
