package com.example.schoolnewspublishingservice.application.in.dto;

import com.example.schoolnewspublishingservice.domain.model.NewsPost;

public record NewsPostDto(

        String id,
        long schoolPageId,
        String title,
        String content
) {

    public static NewsPostDto fromDomainModel(NewsPost domainModel) {
        return new NewsPostDto(
                domainModel.getId(),
                domainModel.getSchoolPageId(),
                domainModel.getTitle(),
                domainModel.getContent());
    }
}
