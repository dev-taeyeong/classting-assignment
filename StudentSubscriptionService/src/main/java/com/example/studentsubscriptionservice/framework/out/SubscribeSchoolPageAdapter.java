package com.example.studentsubscriptionservice.framework.out;

import com.example.studentsubscriptionservice.application.out.SubscribeSchoolPageOutputPort;
import com.example.studentsubscriptionservice.domain.model.Subscription;
import com.example.studentsubscriptionservice.framework.out.jpa.SubscriptionJpaRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@RequiredArgsConstructor
@Component
public class SubscribeSchoolPageAdapter implements SubscribeSchoolPageOutputPort {

    private final SubscriptionJpaRepository subscriptionJpaRepository;

    @Override
    public Subscription save(Subscription subscription) {
        return subscriptionJpaRepository.save(subscription);
    }
}
