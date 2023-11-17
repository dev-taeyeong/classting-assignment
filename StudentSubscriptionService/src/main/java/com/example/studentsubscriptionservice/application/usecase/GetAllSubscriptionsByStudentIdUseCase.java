package com.example.studentsubscriptionservice.application.usecase;

import com.example.studentsubscriptionservice.application.in.dto.SubscriptionDto;

import java.util.List;

public interface GetAllSubscriptionsByStudentIdUseCase {

    List<SubscriptionDto> getAllSubscriptionsByStudentId(long studentId);
}
