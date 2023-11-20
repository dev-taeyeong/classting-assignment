package com.example.schoolnewspublishingservice.application.out;

public interface SubscriptionOutputPort {

    boolean check(Long schoolPageId, Long studentId);
}
