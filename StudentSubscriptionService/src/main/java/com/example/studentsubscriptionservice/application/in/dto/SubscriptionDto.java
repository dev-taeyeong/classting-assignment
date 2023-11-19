package com.example.studentsubscriptionservice.application.in.dto;

import com.example.studentsubscriptionservice.domain.model.Subscription;

public record SubscriptionDto(

        Long id,
        Long studentId,
        Long schoolPageId) {

    public static SubscriptionDto fromDomainModel(Subscription domainModel) {
        return new SubscriptionDto(
                domainModel.getId(),
                domainModel.getStudentId(),
                domainModel.getSchoolPageId());
    }
}
