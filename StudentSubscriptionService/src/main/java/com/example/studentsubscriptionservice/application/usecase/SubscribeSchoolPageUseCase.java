package com.example.studentsubscriptionservice.application.usecase;

import com.example.studentsubscriptionservice.application.in.command.SubscribeSchoolPageCommand;
import com.example.studentsubscriptionservice.application.in.dto.SubscriptionDto;
import com.example.studentsubscriptionservice.domain.model.Subscription;

public interface SubscribeSchoolPageUseCase {

    SubscriptionDto subscribeSchoolPage(SubscribeSchoolPageCommand command);
}
