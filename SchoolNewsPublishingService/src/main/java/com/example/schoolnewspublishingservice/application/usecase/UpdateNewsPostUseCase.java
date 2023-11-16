package com.example.schoolnewspublishingservice.application.usecase;

import com.example.schoolnewspublishingservice.application.in.dto.NewsPostDto;
import com.example.schoolnewspublishingservice.application.in.dto.UpdateNewsPostCommand;

public interface UpdateNewsPostUseCase {

    NewsPostDto updateNewsPost(UpdateNewsPostCommand command);
}
