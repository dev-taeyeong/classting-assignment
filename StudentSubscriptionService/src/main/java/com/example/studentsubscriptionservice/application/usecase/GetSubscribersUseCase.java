package com.example.studentsubscriptionservice.application.usecase;

import com.example.studentsubscriptionservice.application.in.query.GetSubscribersQuery;

import java.util.List;

public interface GetSubscribersUseCase {

    List<Long> getSubscribers(GetSubscribersQuery query);
}
