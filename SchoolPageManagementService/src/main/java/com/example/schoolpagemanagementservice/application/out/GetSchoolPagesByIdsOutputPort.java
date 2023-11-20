package com.example.schoolpagemanagementservice.application.out;

import com.example.schoolpagemanagementservice.domain.model.SchoolPage;

import java.util.List;

public interface GetSchoolPagesByIdsOutputPort {

    List<SchoolPage> fetchByIds(List<Long> schoolPageIds);
}
