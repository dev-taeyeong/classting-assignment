package com.example.studentsubscriptionservice.framework.out;

import com.example.studentsubscriptionservice.application.out.UnsubscribeSchoolPageOutputPort;
import com.example.studentsubscriptionservice.domain.model.Subscription;
import com.example.studentsubscriptionservice.framework.out.jpa.SubscriptionJpaRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.Optional;

@RequiredArgsConstructor
@Component
public class UnsubscribeSchoolPageAdapter implements UnsubscribeSchoolPageOutputPort {

    private final SubscriptionJpaRepository subscriptionJpaRepository;

    @Override
    public Optional<Subscription> fetchByStudentIdAndSchoolPageId(Long studentId, Long schoolPageId) {
        return subscriptionJpaRepository.findByStudentIdAndSchoolPageId(studentId, schoolPageId);
    }

    @Override
    public void delete(Subscription subscription) {
        subscriptionJpaRepository.delete(subscription);
    }
}
