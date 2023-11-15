package com.example.schoolpagemanagementservice.application.out;

import com.example.schoolpagemanagementservice.domain.model.SchoolPage;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface GetAllSchoolPagesOutputPort {

    Page<SchoolPage> getSchoolPages(Pageable pageable);
}
