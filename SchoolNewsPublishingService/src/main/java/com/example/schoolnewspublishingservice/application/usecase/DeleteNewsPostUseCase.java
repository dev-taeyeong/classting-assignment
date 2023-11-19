package com.example.schoolnewspublishingservice.application.usecase;

import com.example.schoolnewspublishingservice.application.in.dto.DeleteNewsPostCommand;

public interface DeleteNewsPostUseCase {

    String deleteNewsPost(DeleteNewsPostCommand command);
}
