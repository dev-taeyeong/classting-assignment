package com.example.studentsubscriptionservice.application.out;

import com.example.studentsubscriptionservice.domain.model.Subscription;

import java.util.List;

public interface SubscriptionOutputPort {

    boolean existsByStudentIdAndSchoolPageId(Long studentId, Long schoolPageId);
}
