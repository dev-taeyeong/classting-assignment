package com.example.schoolnewspublishingservice.application.in;

import com.example.schoolnewspublishingservice.application.in.dto.NewsPostDto;
import com.example.schoolnewspublishingservice.application.out.GetAllNewsPostsOutputPort;
import com.example.schoolnewspublishingservice.application.usecase.GetAllNewsPostsUseCase;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@RequiredArgsConstructor
@Transactional(readOnly = true)
@Service
public class GetAllNewsPostsInputPort implements GetAllNewsPostsUseCase {

    private final GetAllNewsPostsOutputPort getAllNewsPostsOutputPort;

    @Override
    public Page<NewsPostDto> getAllNewsPost(Pageable pageable) {
        return getAllNewsPostsOutputPort.fetchAll(pageable)
                .map(NewsPostDto::fromDomainModel);
    }
}
