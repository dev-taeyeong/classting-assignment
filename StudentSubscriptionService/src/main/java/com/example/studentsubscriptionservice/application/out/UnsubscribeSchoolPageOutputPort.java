package com.example.studentsubscriptionservice.application.out;

import com.example.studentsubscriptionservice.domain.model.Subscription;

import java.util.Optional;

public interface UnsubscribeSchoolPageOutputPort {

    Optional<Subscription> fetchByStudentIdAndSchoolPageId(Long studentId, Long schoolPageId);
    void delete(Subscription subscription);
}
