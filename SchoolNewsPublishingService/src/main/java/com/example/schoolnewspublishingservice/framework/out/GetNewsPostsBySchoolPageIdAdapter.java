package com.example.schoolnewspublishingservice.framework.out;

import com.example.schoolnewspublishingservice.application.out.GetNewsPostsBySchoolPageIdOutputPort;
import com.example.schoolnewspublishingservice.domain.model.NewsPost;
import com.example.schoolnewspublishingservice.framework.out.mongo.NewsPostMongoRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.List;

@RequiredArgsConstructor
@Component
public class GetNewsPostsBySchoolPageIdAdapter implements GetNewsPostsBySchoolPageIdOutputPort {

    private final NewsPostMongoRepository newsPostMongoRepository;

    @Override
    public List<NewsPost> fetchBySchoolPageId(Long schoolPageId) {
        return newsPostMongoRepository.findBySchoolPageId(schoolPageId);
    }
}
