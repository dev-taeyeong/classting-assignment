package com.example.studentsubscriptionservice.application.out;

import com.example.studentsubscriptionservice.application.in.dto.SchoolPageDto;

import java.util.List;

public interface SchoolPageOutputPort {

    List<SchoolPageDto> getSchoolPageDatasByIds(List<Long> schoolPageIds);
}
