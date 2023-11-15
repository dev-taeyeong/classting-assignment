package com.example.schoolpagemanagementservice.application.usecase;

import com.example.schoolpagemanagementservice.application.in.dto.SchoolPageDto;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface GetAllSchoolPagesUseCase {

    Page<SchoolPageDto> getSchoolPages(Pageable pageable);
}
