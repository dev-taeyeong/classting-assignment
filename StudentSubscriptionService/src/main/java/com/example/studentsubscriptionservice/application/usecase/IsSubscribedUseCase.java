package com.example.studentsubscriptionservice.application.usecase;

public interface IsSubscribedUseCase {

    Boolean isSubscribed(Long studentId, Long schoolPageId);
}
