package com.example.studentsubscriptionservice.application.out;

import com.example.studentsubscriptionservice.domain.model.Subscription;

public interface SubscribeSchoolPageOutputPort {

    Subscription save(Subscription subscription);
}
