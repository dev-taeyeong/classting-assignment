package com.example.schoolnewspublishingservice.application.usecase;

import com.example.schoolnewspublishingservice.application.in.dto.NewsPostDto;

import java.util.List;

public interface GetNewsPostsByIdsUseCase {

    List<NewsPostDto> getNewsPostsByIds(List<String> newsPostIds);
}
