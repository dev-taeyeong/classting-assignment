package com.example.studentsubscriptionservice.application.in;

import com.example.studentsubscriptionservice.application.in.command.SubscribeSchoolPageCommand;
import com.example.studentsubscriptionservice.application.in.dto.SubscriptionDto;
import com.example.studentsubscriptionservice.application.out.SubscribeSchoolPageOutputPort;
import com.example.studentsubscriptionservice.application.usecase.SubscribeSchoolPageUseCase;
import com.example.studentsubscriptionservice.domain.model.Subscription;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@RequiredArgsConstructor
@Transactional
@Service
public class SubscribeSchoolPageInputPort implements SubscribeSchoolPageUseCase {

    private final SubscribeSchoolPageOutputPort subscribeSchoolPageOutputPort;

    @Override
    public SubscriptionDto subscribeSchoolPage(SubscribeSchoolPageCommand command) {
        Subscription subscription = Subscription.createSubscription(command.studentId(), command.schoolPageId());
        subscribeSchoolPageOutputPort.save(subscription);

        return SubscriptionDto.fromDomainModel(subscription);
    }
}
