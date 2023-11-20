package com.example.studentsubscriptionservice.application.usecase;

import com.example.studentsubscriptionservice.application.in.dto.SchoolPageDto;

import java.util.List;

public interface GetSubscriptionSchoolPagesByStudentIdUseCase {

    List<SchoolPageDto> getAllSubscriptionSchoolPagesByStudentId(long studentId);
}
