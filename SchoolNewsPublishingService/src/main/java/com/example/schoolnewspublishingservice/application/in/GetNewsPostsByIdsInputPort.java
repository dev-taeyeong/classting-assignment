package com.example.schoolnewspublishingservice.application.in;

import com.example.schoolnewspublishingservice.application.in.dto.NewsPostDto;
import com.example.schoolnewspublishingservice.application.out.GetNewsPostsByIdsOutputPort;
import com.example.schoolnewspublishingservice.application.usecase.GetNewsPostsByIdsUseCase;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@RequiredArgsConstructor
@Transactional(readOnly = true)
@Service
public class GetNewsPostsByIdsInputPort implements GetNewsPostsByIdsUseCase {

    private final GetNewsPostsByIdsOutputPort getNewsPostsByIdsOutputPort;

    @Override
    public List<NewsPostDto> getNewsPostsByIds(List<String> newsPostIds) {
        return getNewsPostsByIdsOutputPort.fetchNewsPostsByIds(newsPostIds).stream()
                .map(NewsPostDto::fromDomainModel)
                .toList();
    }
}
