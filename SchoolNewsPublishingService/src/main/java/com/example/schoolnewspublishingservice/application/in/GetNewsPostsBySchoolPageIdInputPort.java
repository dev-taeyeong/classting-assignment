package com.example.schoolnewspublishingservice.application.in;

import com.example.schoolnewspublishingservice.application.in.dto.NewsPostDto;
import com.example.schoolnewspublishingservice.application.out.GetNewsPostsBySchoolPageIdOutputPort;
import com.example.schoolnewspublishingservice.application.out.SubscriptionOutputPort;
import com.example.schoolnewspublishingservice.application.usecase.GetNewsPostsBySchoolPageIdUseCase;
import com.example.schoolnewspublishingservice.domain.model.NewsPost;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@RequiredArgsConstructor
@Transactional(readOnly = true)
@Service
public class GetNewsPostsBySchoolPageIdInputPort implements GetNewsPostsBySchoolPageIdUseCase {

    private final SubscriptionOutputPort subscriptionOutputPort;
    private final GetNewsPostsBySchoolPageIdOutputPort getNewsPostsBySchoolPageIdOutputPort;

    @Override
    public List<NewsPostDto> getNewsPostsBySchoolPageId(Long schoolPageId, Long studentId) {
        boolean isSubscribed = subscriptionOutputPort.check(schoolPageId, studentId);
        if (!isSubscribed) {
            throw new IllegalArgumentException("잘못된 접근입니다.");
        }

        return getNewsPostsBySchoolPageIdOutputPort.fetchBySchoolPageId(schoolPageId).stream()
                .map(NewsPostDto::fromDomainModel)
                .toList();
    }
}
