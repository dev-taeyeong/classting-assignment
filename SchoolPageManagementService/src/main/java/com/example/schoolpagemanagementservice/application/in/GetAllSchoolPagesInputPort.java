package com.example.schoolpagemanagementservice.application.in;

import com.example.schoolpagemanagementservice.application.in.dto.SchoolPageDto;
import com.example.schoolpagemanagementservice.application.out.GetAllSchoolPagesOutputPort;
import com.example.schoolpagemanagementservice.application.usecase.GetAllSchoolPagesUseCase;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@RequiredArgsConstructor
@Transactional(readOnly = true)
@Service
public class GetAllSchoolPagesInputPort implements GetAllSchoolPagesUseCase {

    private final GetAllSchoolPagesOutputPort getAllSchoolPagesOutputPort;

    @Override
    public Page<SchoolPageDto> getSchoolPages(Pageable pageable) {
        return getAllSchoolPagesOutputPort.getSchoolPages(pageable)
                .map(SchoolPageDto::fromDomainModel);
    }
}
