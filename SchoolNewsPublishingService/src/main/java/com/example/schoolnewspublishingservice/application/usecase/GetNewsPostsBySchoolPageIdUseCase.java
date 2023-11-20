package com.example.schoolnewspublishingservice.application.usecase;

import com.example.schoolnewspublishingservice.application.in.dto.NewsPostDto;

import java.util.List;

public interface GetNewsPostsBySchoolPageIdUseCase {

    List<NewsPostDto> getNewsPostsBySchoolPageId(Long schoolPageId, Long studentId);
}
