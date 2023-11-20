package com.example.studentsubscriptionservice.framework.out;

import com.example.studentsubscriptionservice.application.out.GetSubscribersOutputPort;
import com.example.studentsubscriptionservice.domain.model.Subscription;
import com.example.studentsubscriptionservice.framework.out.jpa.SubscriptionJpaRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.List;

@RequiredArgsConstructor
@Component
public class GetSubscribersAdapter implements GetSubscribersOutputPort {

    private final SubscriptionJpaRepository subscriptionJpaRepository;

    @Override
    public List<Subscription> getSubscribersBySchoolPageId(long schoolPageId) {
        return subscriptionJpaRepository.findBySchoolPageId(schoolPageId);
    }
}
