package com.example.schoolnewspublishingservice.application.in;

import com.example.schoolnewspublishingservice.application.in.dto.CreateNewsPostCommand;
import com.example.schoolnewspublishingservice.application.in.dto.NewsPostDto;
import com.example.schoolnewspublishingservice.application.out.CreateNewsPostOutputPort;
import com.example.schoolnewspublishingservice.application.out.EventOutputPort;
import com.example.schoolnewspublishingservice.application.out.SchoolPagePermissionOutputPort;
import com.example.schoolnewspublishingservice.application.usecase.CreateNewsPostUseCase;
import com.example.schoolnewspublishingservice.domain.model.NewsPost;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@RequiredArgsConstructor
@Transactional
@Service
public class CreateNewsPostInputPort implements CreateNewsPostUseCase {

    private final SchoolPagePermissionOutputPort schoolPagePermissionOutputPort;
    private final CreateNewsPostOutputPort createNewsPostOutputPort;
    private final EventOutputPort eventOutputPort;

    @Override
    public NewsPostDto createNewsPost(CreateNewsPostCommand command) {
        checkPermission(command.administratorId(), command.schoolPageId());

        NewsPost newsPost = NewsPost.createNewsPost(command.schoolPageId(), command.title(), command.content());
        createNewsPostOutputPort.save(newsPost);

        eventOutputPort.occurSchoolNewsPublishedMessage(newsPost.createSchoolNewsPublishedEvent());
        return NewsPostDto.fromDomainModel(newsPost);
    }

    private void checkPermission(long administratorId, long schoolPageId) {
        boolean hasPermission = schoolPagePermissionOutputPort.checkPermission(administratorId, schoolPageId);
        if (!hasPermission) {
            throw new IllegalArgumentException("권한이 없습니다.");
        }
    }
}
