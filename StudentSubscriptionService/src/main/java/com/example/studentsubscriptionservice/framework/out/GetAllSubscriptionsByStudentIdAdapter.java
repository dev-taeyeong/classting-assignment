package com.example.studentsubscriptionservice.framework.out;

import com.example.studentsubscriptionservice.application.out.GetAllSubscriptionsByStudentIdOutputPort;
import com.example.studentsubscriptionservice.domain.model.Subscription;
import com.example.studentsubscriptionservice.framework.out.jpa.SubscriptionJpaRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.List;

@RequiredArgsConstructor
@Component
public class GetAllSubscriptionsByStudentIdAdapter implements GetAllSubscriptionsByStudentIdOutputPort {

    private final SubscriptionJpaRepository subscriptionJpaRepository;

    @Override
    public List<Subscription> fetchByStudentId(long studentId) {
        return subscriptionJpaRepository.findByStudentId(studentId);
    }
}
