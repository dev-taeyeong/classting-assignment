package com.example.schoolnewspublishingservice.application.in;

import com.example.schoolnewspublishingservice.application.in.dto.CreateNewsPostCommand;
import com.example.schoolnewspublishingservice.application.in.dto.NewsPostDto;
import com.example.schoolnewspublishingservice.application.out.CreateNewsPostOutputPort;
import com.example.schoolnewspublishingservice.application.usecase.CreateNewsPostUseCase;
import com.example.schoolnewspublishingservice.domain.model.NewsPost;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@RequiredArgsConstructor
@Transactional
@Service
public class CreateNewsPostInputPort implements CreateNewsPostUseCase {

    private final CreateNewsPostOutputPort createNewsPostOutputPort;

    @Override
    public NewsPostDto createNewsPost(CreateNewsPostCommand command) {
        NewsPost newsPost = NewsPost.createNewsPost(command.schoolPageId(), command.title(), command.content());
        NewsPost savedNewsPost = createNewsPostOutputPort.save(newsPost);
        return NewsPostDto.fromDomainModel(savedNewsPost);
    }
}
