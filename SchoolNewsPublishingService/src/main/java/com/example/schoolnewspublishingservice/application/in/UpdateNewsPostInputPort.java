package com.example.schoolnewspublishingservice.application.in;

import com.example.schoolnewspublishingservice.application.in.dto.NewsPostDto;
import com.example.schoolnewspublishingservice.application.in.dto.UpdateNewsPostCommand;
import com.example.schoolnewspublishingservice.application.out.UpdateNewsPostOutputPort;
import com.example.schoolnewspublishingservice.application.usecase.UpdateNewsPostUseCase;
import com.example.schoolnewspublishingservice.domain.model.NewsPost;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.NoSuchElementException;

@RequiredArgsConstructor
@Transactional
@Service
public class UpdateNewsPostInputPort implements UpdateNewsPostUseCase {

    private final UpdateNewsPostOutputPort updateNewsPostOutputPort;

    @Override
    public NewsPostDto updateNewsPost(UpdateNewsPostCommand command) {
        NewsPost newsPost = updateNewsPostOutputPort.fetchNewsPostById(command.newsPostId())
                .orElseThrow(() -> new NoSuchElementException("존재하지 않는 소식입니다."));
        newsPost.update(command.newTitle(), command.newContent());
        updateNewsPostOutputPort.save(newsPost);
        return NewsPostDto.fromDomainModel(newsPost);
    }
}
