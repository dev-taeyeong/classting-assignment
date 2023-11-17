package com.example.studentsubscriptionservice.application.in;

import com.example.studentsubscriptionservice.application.in.dto.SubscriptionDto;
import com.example.studentsubscriptionservice.application.out.GetAllSubscriptionsByStudentIdOutputPort;
import com.example.studentsubscriptionservice.application.usecase.GetAllSubscriptionsByStudentIdUseCase;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@RequiredArgsConstructor
@Transactional(readOnly = true)
@Service
public class GetAllSubscriptionsByStudentIdInputPort implements GetAllSubscriptionsByStudentIdUseCase {

    private final GetAllSubscriptionsByStudentIdOutputPort getAllSubscriptionsByStudentIdOutputPort;

    @Override
    public List<SubscriptionDto> getAllSubscriptionsByStudentId(long studentId) {
        return getAllSubscriptionsByStudentIdOutputPort.fetchByStudentId(studentId).stream()
                .map(SubscriptionDto::fromDomainModel)
                .toList();
    }
}
