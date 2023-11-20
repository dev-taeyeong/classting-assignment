package com.example.studentsubscriptionservice.framework.out;

import com.example.studentsubscriptionservice.application.out.SubscriptionOutputPort;
import com.example.studentsubscriptionservice.domain.model.Subscription;
import com.example.studentsubscriptionservice.framework.out.jpa.SubscriptionJpaRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.List;

@RequiredArgsConstructor
@Component
public class SubscriptionAdapter implements SubscriptionOutputPort {

    private final SubscriptionJpaRepository subscriptionJpaRepository;

    @Override
    public boolean existsByStudentIdAndSchoolPageId(Long studentId, Long schoolPageId) {
        return subscriptionJpaRepository.existsByStudentIdAndSchoolPageId(studentId, schoolPageId);
    }
}
