package com.example.schoolnewspublishingservice.application.usecase;

import com.example.schoolnewspublishingservice.application.in.dto.CreateNewsPostCommand;
import com.example.schoolnewspublishingservice.application.in.dto.NewsPostDto;

public interface CreateNewsPostUseCase {

    NewsPostDto createNewsPost(CreateNewsPostCommand command);
}
