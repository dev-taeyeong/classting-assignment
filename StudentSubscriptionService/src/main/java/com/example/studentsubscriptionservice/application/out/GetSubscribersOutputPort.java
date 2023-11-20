package com.example.studentsubscriptionservice.application.out;

import com.example.studentsubscriptionservice.domain.model.Subscription;

import java.util.List;

public interface GetSubscribersOutputPort {

    List<Subscription> getSubscribersBySchoolPageId(long schoolPageId);
}
