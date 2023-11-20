package com.example.schoolpagemanagementservice.application.usecase;

import com.example.schoolpagemanagementservice.application.in.dto.SchoolPageDto;

import java.util.List;

public interface GetSchoolPagesByIdsUseCase {

    List<SchoolPageDto> getSchoolPagesByIds(List<Long> schoolPageIds);
}
