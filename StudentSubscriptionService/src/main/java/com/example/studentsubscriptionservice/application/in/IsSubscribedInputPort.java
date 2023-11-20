package com.example.studentsubscriptionservice.application.in;

import com.example.studentsubscriptionservice.application.out.SubscriptionOutputPort;
import com.example.studentsubscriptionservice.application.usecase.IsSubscribedUseCase;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@RequiredArgsConstructor
@Transactional(readOnly = true)
@Service
public class IsSubscribedInputPort implements IsSubscribedUseCase {

    private final SubscriptionOutputPort subscriptionOutputPort;

    @Override
    public Boolean isSubscribed(Long studentId, Long schoolPageId) {
        return subscriptionOutputPort.existsByStudentIdAndSchoolPageId(studentId, schoolPageId);
    }
}
